package com.an_it.ocr.format

import com.an_it.ocr._
import xml.NodeSeq


object AbbyyXML extends OCRFormat{

  def document(xml: NodeSeq) = new Document(
     (xml \\ "page").toIndexedSeq map page
  )

  def line(xml: NodeSeq) = null

  def page(xml: NodeSeq) : Page = null

  def word(xml: NodeSeq) = null

}
