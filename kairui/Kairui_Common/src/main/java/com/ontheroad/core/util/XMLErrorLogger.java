package com.ontheroad.core.util;


import org.slf4j.Logger;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import javax.xml.transform.ErrorListener;
import javax.xml.transform.TransformerException;
import javax.xml.stream.Location;
import javax.xml.stream.XMLReporter;

public final class XMLErrorLogger implements ErrorHandler,ErrorListener,XMLReporter {

  private final Logger log;

  public XMLErrorLogger(Logger log) {
    this.log = log;
  }

  // ErrorHandler

  @Override
  public void warning(SAXParseException e) {
    log.warn("XML parse warning in \""+e.getSystemId()+"\", line "+e.getLineNumber()+", column "+e.getColumnNumber()+": "+e.getMessage());
  }

  @Override
  public void error(SAXParseException e) throws SAXException {
    throw e;
  }

  @Override
  public void fatalError(SAXParseException e) throws SAXException {
    throw e;
  }

  // ErrorListener

  @Override
  public void warning(TransformerException e) {
    log.warn(e.getMessageAndLocation());
  }

  @Override
  public void error(TransformerException e) throws TransformerException {
    throw e;
  }

  @Override
  public void fatalError(TransformerException e) throws TransformerException {
    throw e;
  }

  // XMLReporter

  @Override
  public void report(String message, String errorType, Object relatedInformation, Location loc) {
    final StringBuilder sb = new StringBuilder("XML parser reported ").append(errorType);
    if (loc !=  null) {
      sb.append(" in \"").append(loc.getSystemId()).append("\", line ")
        .append(loc.getLineNumber()).append(", column ").append(loc.getColumnNumber());
    }
    log.warn(sb.append(": ").append(message).toString());
  }

}
