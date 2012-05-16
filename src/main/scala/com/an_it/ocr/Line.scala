package com.an_it.ocr

import xml.NodeSeq

/**
 * AN-iT
 * Andreas Neumann
 * andreas.neumann@an-it.com
 * http://www.an-it.com
 * Date: 22.11.11
 * Time: 12:36
 */

case class Line(
                 coordinates: ((Int, Int),(Int, Int)),
                 val words: IndexedSeq[Word],
                 val enclosingPageNumber: Int = 0,
                 val fontFeatures: Option[List[Symbol]] = None
                 ) extends Element with IndexedSeq[Word]{
  def toText: String = words.map(_.text).mkString(" ")

  def toHTML(zoom: Double = 1) : NodeSeq =
    <span class= {"OCRLine"} data-features={featuresDataAttribute}  style={toCSS(zoom)}></span> ++
      words.map(_.toHTML(zoom)).reduce(_ ++ _)

  def length = words.length

  def apply(idx : Int) = words.apply(idx)
}

object Line {
  def fromHOCR(html : xml.Node, enclosingPageNumber: Int= 0) =  new Line(Element.extractCoordinates(html), buildWordSeq(html, enclosingPageNumber), enclosingPageNumber,Element.fontFeaturesFromHTML(html))

  def isOCRWord(html : xml.NodeSeq) = Element.extractOCRClass(html) == 'ocrx_word

  def buildWordSeq(html: xml.Node,  enclosingPageNumber: Int = 0) : IndexedSeq[Word] =
    html.child filter(isOCRWord(_)) map {child => new Word(Element.extractCoordinates(child),child.text, enclosingPageNumber, Element.fontFeaturesFromHTML(child))} toIndexedSeq

}