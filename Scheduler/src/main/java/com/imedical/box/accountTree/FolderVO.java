package com.imedical.box.accountTree;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

public class FolderVO {
	@XStreamAlias("id")
	@XStreamAsAttribute
	private String id;
	@XStreamAlias("name")
	@XStreamAsAttribute
	private String name;
	@XStreamAlias("shared")
	@XStreamAsAttribute
	private String shared;
	private List<TagVO> tags = new ArrayList<TagVO>();
	private List<FileVO> files = new ArrayList<FileVO>();
	private List<FolderVO> folders = new ArrayList<FolderVO>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShared() {
		return shared;
	}

	public void setShared(String shared) {
		this.shared = shared;
	}

	public void addTag(TagVO tag) {
		tags.add(tag);
	}

	public void addFile(FileVO file) {
		files.add(file);
	}

	public void addFolders(FolderVO f) {
		folders.add(f);
	}

	public List<FolderVO> getFolders() {
		return folders;
	}

	public List<FileVO> getFiles() {
		return files;
	}

}
