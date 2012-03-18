package com.imedical.box.accountTree;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("files")
public class FileListVO {
	@XStreamImplicit
	private List<FileVO> files;

	public FileListVO() {
		files = new ArrayList<FileVO>();
	}

	public void add(FileVO f) {
		files.add(f);
	}

	public List<FileVO> getFiles() {
		return files;
	}
}
