package com.imedical.common;

import com.imedical.box.accountTree.FileVO;
import com.imedical.box.accountTree.FolderVO;

public class TestFolderVO {

	public static FolderVO folderVO = new FolderVO();
	private static TestFolderVO _instance = new TestFolderVO();

	private TestFolderVO() {
		
	}

	public TestFolderVO getInstance() {
		return _instance;
	}

}
