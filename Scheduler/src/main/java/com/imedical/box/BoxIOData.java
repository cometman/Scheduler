package com.imedical.box;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.rpc.holders.ByteArrayHolder;
import javax.xml.rpc.holders.StringHolder;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;

import boxnet.holders.SOAPFileInfoHolder;

import com.imedical.Scheduler.data.ProviderVO;
import com.imedical.box.accountTree.FolderVO;
import com.imedical.box.accountTree.XStreamHelper;
import com.imedical.common.LogUtil;

public class BoxIOData implements IBoxIOData {

	private InputStream reader;
	private File fileRetrieved;
	private FileOutputStream writer;
	private URL retrieveUrl;
	private XStreamHelper xstreamHelper;
	private URL uploadURL;

	public File retrieveFile(String fileid, ProviderVO provider) {
		String fileDownloadRequest = "https://www.box.net/api/1.0/download"
				+ "/" + provider.getAuth_key() + "/" + fileid;
		try {
			/*
			 * Get any needed data about the downloaded file (e.g. file name)
			 */
			StringHolder status = new StringHolder();
			Long idLong = Long.valueOf(fileid);
			System.out.println(fileDownloadRequest);
			SOAPFileInfoHolder fileInfo = new SOAPFileInfoHolder();
			CreateBoxSession.proxy.get_file_info(Box_Finals.API_KEY,
					provider.getAuth_key(), idLong, status, fileInfo);

			/*
			 * Open a URL to the file to download
			 */

			retrieveUrl = new URL(fileDownloadRequest);
			retrieveUrl.openConnection();
			reader = retrieveUrl.openStream();

			/*
			 * Write the download to file
			 */
			fileRetrieved = new File(fileInfo.value.getFile_name());
			writer = new FileOutputStream(fileRetrieved);
			/*
			 * Check to see if the file actually has content inside it before we
			 * try to read out the contents
			 */
			if (fileInfo.value.getSize() > 0) {
				byte[] buffer = new byte[2048];
				int bytesRead = 0;
				while ((bytesRead = reader.read(buffer)) > 0) {
					writer.write(buffer, 0, bytesRead);
					buffer = new byte[2048];
				}
			}
			writer.close();

		} catch (MalformedURLException e) {
			LogUtil.writeMessage("Bad URL for file download! [" + retrieveUrl
					+ "]");
			LogUtil.closeWriter();
			e.printStackTrace();
		} catch (IOException e) {
			LogUtil.writeMessage("IOException for the URL [" + retrieveUrl
					+ "]");
			LogUtil.closeWriter();
			e.printStackTrace();
		}

		return fileRetrieved;
	}

	public String uploadDataFile(File file, ProviderVO provider, String folderID) {
		String fileUploadRequest = "https://upload.box.net/api/1.0/upload/"
				+ provider.getAuth_key() + "/" + folderID;
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(fileUploadRequest);

		MultipartEntity mpEntity = new MultipartEntity();
		mpEntity.addPart("file", new FileBody(file));

		httppost.setEntity(mpEntity);
		HttpResponse response = null;
		try {
			response = httpclient.execute(httppost);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(response);

		return response.toString();
	}

	public URLConnection createRemoteConnection(URL url) {
		URLConnection connection = null;
		try {
			connection = url.openConnection();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

	public String getAccountTreeXML(long folderID, ProviderVO provider) {
		String authToken = provider.getAuth_key();
		/*
		 * Set additional parameters here if needed. We add the parameter
		 * "nozip" to indicate don't compress for the time being..
		 */
		String[] additionalParams = { "nozip" };
		StringHolder status = new StringHolder();
		ByteArrayHolder xmlTree = new ByteArrayHolder();

		try {
			CreateBoxSession.proxy.get_account_tree(Box_Finals.API_KEY,
					authToken, folderID, additionalParams, status, xmlTree);
		} catch (RemoteException e) {
			LogUtil.writeMessage("Problem getting account tree information!"
					+ e.getStackTrace());
			e.printStackTrace();
		} finally {

			String decodedString = null;
			try {

				byte[] decodedStringBytes = new String(xmlTree.value, "UTF-8")
						.getBytes();
				decodedString = new String(decodedStringBytes);
				return decodedString;
			} catch (UnsupportedEncodingException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		}
		return null;
	}

	public File writeByteArrayToXMLFile(byte[] ba) throws IOException {
		File file = new File("C:/Testing/accountTree.xml");
		file.createNewFile();
		net.iharder.Base64.OutputStream fos = null;
		try {
			fos = new net.iharder.Base64.OutputStream(
					new FileOutputStream(file));

			int length = ba.length;
			fos.write(ba, 0, length);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			LogUtil.writeMessage("Unable to convert byte array to byte[]");
			LogUtil.closeWriter();
			e.printStackTrace();
		} finally {
			fos.close();
		}

		return file;
	}

	@Override
	public FolderVO createAccoutTreePOJO(String xmlString) {
		xstreamHelper = new XStreamHelper(xmlString);
		xstreamHelper.prepareAccountXMLStream();
		FolderVO accountTree = xstreamHelper.getAccountTree();
		if (accountTree != null) {
			return accountTree;
		} else {
			// TODO error handling
			return null;
		}
	}

	@Override
	public String getFileIDByFolderName(FolderVO acctFolder, String folderName) {
		if (acctFolder == null || folderName == null) {
			LogUtil.writeMessage("The account folder or folder name is null!");
			// TODO handle errors here!
		} else {
			String fileId = null;
			for (FolderVO folder : acctFolder.getFolders()) {
				if (folder.getName().equals(folderName)) {
					fileId = folder.getFiles().get(0).getId();
				}
			}
			if (fileId != null && !fileId.isEmpty()) {
				return fileId;
			} else {
				LogUtil.writeMessage("The returned fileID was empty!");
			}
		}
		return null;

	}
}
