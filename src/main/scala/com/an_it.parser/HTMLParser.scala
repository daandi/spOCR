package com.an_it

/**
 * AN-iT
 *
 * User: Andreas Neumann
 * Mail: andreas.neumann@an-it.com
 * URL: http://www.an-it.com
 * Date: 31.10.11
 * Time: 19:52
 * Package: com.an_it.parser
 */

import xml.NodeSeq
import java.net.URL
import java.io.{FileInputStream, File, InputStream}

class HTMLParser {

  /* Quelle: http://www.hars.de/2009/01/html-as-xml-in-scala.html*/
  val parserFactory = new org.ccil.cowan.tagsoup.jaxp.SAXFactoryImpl
  val parser = parserFactory.newSAXParser()
  val adapter = new scala.xml.parsing.NoBindingFactoryAdapter

  def fromFile(file : File) : NodeSeq = fromStream( new FileInputStream( file ) )
  def fromFile(file : String) : NodeSeq = fromStream( new FileInputStream( new File(file) ) )

  def fromURL(url : String) : NodeSeq = fromStream( new URL(url).openStream() )

  def fromStream(stream : InputStream ) : NodeSeq = {
      val HTMLFile = new org.xml.sax.InputSource(stream )
      HTMLFile.setEncoding("UTF-8") // Force UTF-8
      adapter.loadXML(HTMLFile, parser)
  }

}