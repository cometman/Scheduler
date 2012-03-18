package com.imedical.box.accountTree;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("tags")
public class TagListVO {
	@XStreamImplicit
	private List<TagVO> tags;

	public TagListVO() {
		tags = new ArrayList<TagVO>();
	}

	public void add(TagVO tag) {
		this.tags.add(tag);
	}

	public List<TagVO> getTags() {
		return tags;
	}
}
