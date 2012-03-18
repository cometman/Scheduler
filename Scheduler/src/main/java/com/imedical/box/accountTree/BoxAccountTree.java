package com.imedical.box.accountTree;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;

//@XStreamAlias("response")
public class BoxAccountTree {

	private String status;

//	@XStreamAlias("tree")
	private List<FolderVO> rootFolders = new ArrayList<FolderVO>();

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<FolderVO> getRootFolders() {
		return rootFolders;
	}

	public void setAccountTree(List<FolderVO> rootFolders) {
		this.rootFolders = rootFolders;
	}

	public void addRootFolder(FolderVO folder) {
		rootFolders.add(folder);
	}

}
