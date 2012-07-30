package com.imedical.box;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import com.imedical.Scheduler.data.ProviderVO;

public class UploadFile {
	private static final String CONTENT_TYPE = "Content-Type";

	private static final String CHARSET = "UTF-8";

	// Line separator required by multipart/form-data.
	private static final String CRLF = "\r\n";

	// Just generate some unique random value.
	private String boundary = Long.toHexString(System.currentTimeMillis());
	private static final String MULTI_PART_FORM = "multipart/form-data; boundary=";
	private URLConnection connection;
	private String url = "https://upload.box.net/api/1.0/upload/";
	private PrintWriter writer = null;
	private File file;
	private ProviderVO provider;
	private String folder;

	public UploadFile(File file, ProviderVO provider, String folder) {
		if (file != null && provider != null && folder != null) {
			this.file = file;
			this.provider = provider;
			this.folder = folder;
		}
	}

	public void startUpload() {
		try {
			setupConnection();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sendFile();
	}

	public void setupConnection() throws MalformedURLException, IOException {
		url += provider.getAuth_key() + "/" + folder;
		connection = new URL(url).openConnection();
		connection.setDoOutput(true);
		connection.setRequestProperty(CONTENT_TYPE, MULTI_PART_FORM + boundary);
	}

	public void sendFile() {
		try {
			OutputStream output = connection.getOutputStream();
			writer = new PrintWriter(new OutputStreamWriter(output, CHARSET),
					true);
			writer.append("--" + boundary).append(CRLF);
			writer.append("Content-Disposition: form-data; name=\"file1\"")
					.append(CRLF);
			writer.append(
					"Content-Type: "
							+ URLConnection.guessContentTypeFromName(file
									.getName())).append(CRLF);
			System.out.println(URLConnection.guessContentTypeFromName(file
									.getName()));
			writer.append(CRLF).flush();
			InputStream input = null;

			try {
				input = new FileInputStream(file);
				byte[] buffer = new byte[1024];
				for (int length = 0; (length = input.read(buffer)) > 0;) {
					output.write(buffer, 0, length);
				}
				output.flush();
			} finally {
				if (input != null)
					try {
						input.close();
					} catch (IOException e) {
						e.printStackTrace();
						// TODO HANDLE EXCEPTION
					}
			}
			writer.append(CRLF).flush();
			writer.append("--" + boundary + "--").append(CRLF);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (writer != null)
				writer.close();
		}
	}
}
