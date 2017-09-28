package com.ontheroad.core.util;

 
import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 */
public class DOMUtil{

	public static Map<String,String> toMap(NamedNodeMap attrs) {
	    return toMapExcept(attrs);
	  }

	  public static Map<String,String> toMapExcept(NamedNodeMap attrs, String... exclusions) {
	    Map<String,String> args = new HashMap<String,String>();
	    outer: for (int j=0; j<attrs.getLength(); j++) {
	      Node attr = attrs.item(j);
	      String attrName = attr.getNodeName();
	      for (String ex : exclusions)
	        if (ex.equals(attrName)) continue outer;
	      String val = attr.getNodeValue();
	      args.put(attrName, val);
	    }
	    return args;
	  }

	  public static Node getChild(Node node, String name) {
	    if (!node.hasChildNodes()) return null;
	    NodeList lst = node.getChildNodes();
	    if (lst == null) return null;
	    for (int i=0; i<lst.getLength(); i++) {
	      Node child = lst.item(i);
	      if (name.equals(child.getNodeName())) return child;
	    }
	    return null;
	  }

	  public static String getAttr(NamedNodeMap attrs, String name) {
	    return getAttr(attrs,name,null);
	  }

	  public static String getAttr(Node nd, String name) {
	    return getAttr(nd.getAttributes(), name);
	  }

	  public static String getAttr(NamedNodeMap attrs, String name, String missing_err) {
	    Node attr = attrs==null? null : attrs.getNamedItem(name);
	    if (attr==null) {
	      if (missing_err==null) return null;
	      throw new RuntimeException(missing_err + ": missing mandatory attribute '" + name + "'");
	    }
	    String val = attr.getNodeValue();
	    return val;
	  }

	  public static String getAttr(Node node, String name, String missing_err) {
	    return getAttr(node.getAttributes(), name, missing_err);
	  }
	  
  public static String getText(Node nd) {

    short type = nd.getNodeType();

    // for most node types, we can defer to the recursive helper method,
    // but when asked for the text of these types, we must return null
    // (Not the empty string)
    switch (type) {

    case Node.DOCUMENT_NODE: /* fall through */
    case Node.DOCUMENT_TYPE_NODE: /* fall through */
    case Node.NOTATION_NODE: /* fall through */
      return null;
    }

    StringBuilder sb = new StringBuilder();
    getText(nd, sb);
    return sb.toString().trim();
  }

  /** @see #getText(Node) */
  private static void getText(Node nd, StringBuilder buf) {

    short type = nd.getNodeType();

    switch (type) {

    case Node.ELEMENT_NODE: /* fall through */
    case Node.ENTITY_NODE: /* fall through */
    case Node.ENTITY_REFERENCE_NODE: /* fall through */
    case Node.DOCUMENT_FRAGMENT_NODE:
      NodeList childs = nd.getChildNodes();
      for (int i = 0; i < childs.getLength(); i++) {
        Node child = childs.item(i);
        short childType = child.getNodeType();
        if (childType != Node.COMMENT_NODE &&
            childType != Node.PROCESSING_INSTRUCTION_NODE) {
          getText(child, buf);
        }
      }
      break;

    case Node.ATTRIBUTE_NODE: /* fall through */
      /* Putting Attribute nodes in this section does not exactly
         match the definition of how textContent should behave
         according to the DOM Level-3 Core documentation - which
         specifies that the Attr's children should have their
         textContent concated (Attr's can have a single child which
         is either Text node or an EntityReference).  In practice,
         DOM implementations do not seem to use child nodes of
         Attributes, storing the "text" directly as the nodeValue.
         Fortunately, the DOM Spec indicates that when Attr.nodeValue
         is read, it should return the nodeValue from the child Node,
         so this approach should work both for strict implementations,
         and implementations actually encountered.
      */
    case Node.TEXT_NODE: /* fall through */
    case Node.CDATA_SECTION_NODE: /* fall through */
    case Node.COMMENT_NODE: /* fall through */
    case Node.PROCESSING_INSTRUCTION_NODE: /* fall through */
      buf.append(nd.getNodeValue());
      break;

    case Node.DOCUMENT_NODE: /* fall through */
    case Node.DOCUMENT_TYPE_NODE: /* fall through */
    case Node.NOTATION_NODE: /* fall through */
    default:
      /* :NOOP: */

    }
  }

     



}
