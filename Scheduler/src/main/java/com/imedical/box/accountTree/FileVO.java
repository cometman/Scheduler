package com.imedical.box.accountTree;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("file")
public class FileVO {
	@XStreamAlias("id")
	@XStreamAsAttribute
	private String id;
	@XStreamAlias("file_name")
	@XStreamAsAttribute
	private String file_name;
	@XStreamAlias("keyword")
	@XStreamAsAttribute
	private String keyword;
	@XStreamAlias("shared")
	@XStreamAsAttribute
	private String shared;
	@XStreamAlias("size")
	@XStreamAsAttribute
	private String size;
	@XStreamAlias("created")
	@XStreamAsAttribute
	private String created;
	@XStreamAlias("updated")
	@XStreamAsAttribute
	private String updated;

	private List<TagVO> tags = new ArrayList<TagVO>();

	// private TagListVO tags = new TagListVO();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFileName() {
		return file_name;
	}

	public void setFileName(String fileName) {
		this.file_name = fileName;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getShared() {
		return shared;
	}

	public void setShared(String shared) {
		this.shared = shared;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public String getUpdated() {
		return updated;
	}

	public void setUpdated(String updated) {
		this.updated = updated;
	}

	public void addTag(TagVO tag) {
		tags.add(tag);
	}

	public List<TagVO> getTags() {
		return tags;
	}

}
