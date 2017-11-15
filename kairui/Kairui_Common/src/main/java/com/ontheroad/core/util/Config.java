package com.ontheroad.core.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.Assert;
import org.w3c.dom.*;
import org.xml.sax.InputSource;

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;

/**
 *
 */
public class Config {
	public static final Logger log = LoggerFactory.getLogger(Config.class);
	private static final XMLErrorLogger xmllog = new XMLErrorLogger(log);

	static final XPathFactory xpathFactory = XPathFactory.newInstance();
	private Document doc;
	private String prefix;

	public Config(InputStream is) {
		this(is, null);
	}
	
	public Config(String xml){
		this(xml,null);
	}
	
	public Config(String xml, String prefix){
		try {
			javax.xml.parsers.DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			final DocumentBuilder db = dbf.newDocumentBuilder();
			db.setErrorHandler(xmllog);
			this.prefix = prefix;
			doc = db.parse(new InputSource(new StringReader(xml)));
		} catch (Exception e) {
			log.error("init config err", e);
		}
	}

	public Config(InputStream is, String prefix) {
		try {
			javax.xml.parsers.DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			final DocumentBuilder db = dbf.newDocumentBuilder();
			db.setErrorHandler(xmllog);
			this.prefix = prefix;
			doc = db.parse(is);
		} catch (Exception e) {
			log.error("init config err", e);
		}
	}

	public Document getDocument() {
		return doc;
	}
	
	/**
	 * 获取根节点
	 * @return
	 */
	public Node getRootNode(){
		return doc.getFirstChild();
	}
	
	/**
	 * 获取节点下的第一个子节点
	 * @param ele
	 * @return
	 */
	public Element getFirstChild(Node ele) {
		Assert.notNull(ele, "Node must not be null");
		NodeList nl = ele.getChildNodes();
		for (int i = 0; i < nl.getLength(); i++) {
			Node item = nl.item(i);
			if (item instanceof Element) {
				return (Element) item;
			}
		}
		return null;
	}

	public XPath getXPath() {
		return xpathFactory.newXPath();
	}

	public Object evaluate(String path, QName type) {
		XPath xpath = xpathFactory.newXPath();
		try {
			return xpath.evaluate(normalize(path), doc, type);
		} catch (XPathExpressionException e) {
			throw new RuntimeException("Error in xpath:" + path, e);
		}
	}

	public Node getNode(String path, boolean errifMissing) {
		return getNode(path, doc, errifMissing);
	}

	public Node getNode(String path, Document doc, boolean errIfMissing) {
		try {
			NodeList nodes = getNodeList(path, errIfMissing);
			if (nodes == null || 0 == nodes.getLength()) {
				if (errIfMissing) {
					throw new RuntimeException(" missing " + path);
				} else {
					log.debug(" missing optional " + path);
					return null;
				}
			}
			if (1 < nodes.getLength()) {
				throw new Exception(" contains more than one value for config path: "+ path);
			}
			Node nd = nodes.item(0);
			log.trace(path + "=" + nd);
			return nd;

		} catch (Exception e) {
			throw new RuntimeException("Error in xpath:" + path, e);
		}
	}

	public NodeList getNodeList(String path, boolean errIfMissing) {
		XPath xpath = xpathFactory.newXPath();
		try {
			NodeList nodeList = (NodeList) xpath.evaluate(normalize(path), doc,XPathConstants.NODESET);
			if (null == nodeList) {
				if (errIfMissing) {
					throw new RuntimeException(" missing " + path);
				} else {
					log.debug(" missing optional " + path);
					return null;
				}
			}
			log.trace(path + "=" + nodeList);
			return nodeList;

		} catch (Exception e) {
			throw new RuntimeException("Error in xpath:" + path, e);
		}
	}
	
	/**
	 * 获取节点的值
	 * @param path
	 * @param errIfMissing,没有找到节点是否报错
	 * @return
	 */
	public String getVal(String path, boolean errIfMissing) {
		Node nd = getNode(path, errIfMissing);
		if (nd == null)
			return null;

		String txt = DOMUtil.getText(nd);
		log.debug(path + '=' + txt);
		return txt;
	}

	private String normalize(String path) {
		return (prefix == null || path.startsWith("/")) ? path : prefix + path;
	}

	public String get(String path) {
		return getVal(path, true);
	}

	public String get(String path, String def) {
		String val = getVal(path, false);
		if (val == null || val.length() == 0) {
			return def;
		}
		return val;
	}

	public int getInt(String path) {
		return Integer.parseInt(getVal(path, true));
	}

	public int getInt(String path, int def) {
		String val = getVal(path, false);
		return val != null ? Integer.parseInt(val) : def;
	}

	public boolean getBool(String path) {
		return Boolean.parseBoolean(getVal(path, true));
	}

	public boolean getBool(String path, boolean def) {
		String val = getVal(path, false);
		return val != null ? Boolean.parseBoolean(val) : def;
	}

	public float getFloat(String path) {
		return Float.parseFloat(getVal(path, true));
	}

	public float getFloat(String path, float def) {
		String val = getVal(path, false);
		return val != null ? Float.parseFloat(val) : def;
	}

	public double getDouble(String path) {
		return Double.parseDouble(getVal(path, true));
	}

	public double getDouble(String path, double def) {
		String val = getVal(path, false);
		return val != null ? Double.parseDouble(val) : def;
	}

	public static void main(String args[]) throws IOException {
		Config config = new Config(
				new ClassPathResource("ngboss_config.xml").getInputStream(),
				null);
		System.out.println(config.get("//resetPwd", "none"));
		NodeList nodeList = config.getNodeList("//fields/field", false);
		System.out.println(nodeList.getLength());
		Node node = null;
		for (int i = 0; i < nodeList.getLength(); i++) {
			node = nodeList.item(i);
			// System.out.println(node+";"+node.getAttributes());
			NamedNodeMap attributes = node.getAttributes();
			for (int j = 0; j < attributes.getLength(); j++) {
				System.out.println(attributes.item(j).getNodeName() + ";"
						+ attributes.item(j).getNodeValue());

			}
		}

	}
}
