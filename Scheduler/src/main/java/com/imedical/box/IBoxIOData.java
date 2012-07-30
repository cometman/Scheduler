package com.imedical.box;

import java.io.File;

import com.imedical.Scheduler.data.ProviderVO;
import com.imedical.box.accountTree.FolderVO;
import com.imedical.model.ProviderModel;

public interface IBoxIOData {

	/**
	 * Retrieves a file from Box.net based on the file id. URL format:
	 * https://www.box.net/api/1.0/download/&ltauth_token&gt/&ltfile_id&gt
	 * 
	 * @param fileID
	 *            File id of the file to download
	 * @param provider
	 *            The provider to download the file from.
	 * @return A downloaded file from Box.net
	 */
	public File retrieveFile(String fileID, ProviderVO provider);

	/**
	 * Uploads a file to Box.net. URL format:
	 * https://upload.box.net/api/1.0/upload/&ltauth token&gt/&ltfolder id&gt
	 * 
	 * @param fileID
	 *            The file to upload
	 * @param provider
	 *            The provider who to upload the file to
	 * @param folderID TODO
	 * @return TODO
	 * 
	 * 
	 * 
	 */
	public String uploadDataFile(File fileID, ProviderVO provider, String folderID);

	/**
	 * This method is used to get a tree representing all of the user's files
	 * and folders.
	 * 
	 * Usage of the optional 'onelevel' parameter is highly recommended as
	 * extremely large accounts involving substantial amounts of data may cause
	 * the request to time out if the entire tree is pulled.
	 * 
	 * @param folderID
	 *            The ID of the root folder from which the tree begins. If this
	 *            value is "0", the user's full account tree is returned.
	 * @param provider
	 *            The provider to get the account tree from.
	 */
	public String getAccountTreeXML(long folderID, ProviderVO provider);

	/**
	 * Creates an FolderVO object with the passed in xml string. The nozip
	 * parameter is added so that we don't receive a compressed xml string. This
	 * will be changed later.
	 * 
	 * @param xmlString
	 * @return FolderVO object
	 */
	public FolderVO createAccoutTreePOJO(String xmlString);

	/**
	 * Get a file id by the specified folder name. For now, folders will contain
	 * only one file in each...this will change
	 * 
	 * @param folderName
	 *            Name of the folder.
	 * @return Id of the file in the specified folder
	 */
	public String getFileIDByFolderName(FolderVO acctFolder, String folderName);
	
	/**
	 * Used to rename the patients.xml file to the appropriate name
	 * @param model
	 */
	public void renameUploadedPatientsBoxFile(ProviderModel model);

}
