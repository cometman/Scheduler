package com.imedical.box.accountTree;

import java.util.ArrayList;
import java.util.List;

public class FoldersVO {
	private List<FolderVO> folder = new ArrayList<FolderVO>();

	public void addFolder(FolderVO f) {
		folder.add(f);
	}

	public List<FolderVO> getFolders() {
		return folder;
	}

}
