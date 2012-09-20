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
  def documentFromXML(xml: NodeSeq) : Document

  def pageFromXML(xml: NodeSeq) : Page

  def lineFromXML(xml: NodeSeq) : Line

  def wordFromXML(xml: NodeSeq) : Word

  def extractCoordinatesFromXML(xml : NodeSeq) : ((Int,Int),(Int,Int))

  def pageNumber(xml: NodeSeq) : Int

}
