package com.imedical.box.accountTree;

import java.util.ArrayList;
import java.util.List;


public class RootFolderVO {
	List<FolderVO> rootFolders = new ArrayList<FolderVO>();

	public void addRootRolders(FolderVO f) {
		rootFolders.add(f);
	}

}
