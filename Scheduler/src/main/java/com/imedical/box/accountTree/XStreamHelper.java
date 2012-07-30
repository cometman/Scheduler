package com.imedical.box.accountTree;

import com.imedical.common.LogUtil;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;

public class XStreamHelper {
	private static final XmlFriendlyNameCoder NAME_CODER = new XmlFriendlyNameCoder(
			"$", "_");
	private static XStream xstream = new XStream(new DomDriver("UTF-8", NAME_CODER));
	private String xml;

	public XStreamHelper() {

	}

	public XStreamHelper(String xml) {
		if (xml != null) {
			xstream.autodetectAnnotations(true);
			this.xml = xml;
		} else {
			LogUtil.writeMessage("XML for account tree is empty!");
			// TODO handle logic here..
		}

	}

	public void prepareAccountXMLStream() {
		xstream.alias("folder", FolderVO.class);
		xstream.alias("folders", FoldersVO.class);
		xstream.alias("file", FileVO.class);
		xstream.alias("files", FileListVO.class);
		xstream.alias("tag", TagVO.class);
		xstream.alias("tags", TagListVO.class);
	}

	public FolderVO getAccountTree() {
		FolderVO acctTree = (FolderVO) xstream.fromXML(xml);
		return acctTree;
	}

	public static XStream getXStream() {
		return xstream;
	}
}
