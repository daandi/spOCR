package com.an_it.ocr.format

import xml.NodeSeq
import com.an_it.ocr.{Word, Line, Page, Document}

/**
 * Smarchive GmbH
 * User: Andreas Neumann
 * Email: a.neumann@smarchive.de
 * Date: 20.09.12
 * Time: 18:10
 */
trait OCRFormat {
  def document(xml: NodeSeq) : Document

  def page(xml: NodeSeq) : Page

  def line(xml: NodeSeq) : Line

  def word(xml: NodeSeq) : Word

}
