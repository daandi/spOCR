package com.an_it.ocr

import xml.NodeSeq

/**
 * AN-iT
 * Andreas Neumann
 * andreas.neumann@an-it.com
 * http://www.an-it.com
 * Date: 22.11.11
 * Time: 12:34
 */

case class Word(
                 coordinates: ((Int,Int),(Int, Int)),
                 text : String,
                 val enclosingPageNumber: Int = 0,
                 val fontFeatures: Option[List[Symbol]] = None) extends Element {
  var nextWordDistance : Option[Int]  = None

  def toHTML(zoom: Double = 1) : NodeSeq =
    <span class={"OCRWord"} data-text={text} data-features={featuresDataAttribute} style={ toCSS(zoom) }>{}</span>
}

object Word {

  def fromHOCR( html : xml.Node,  enclosingPageNumber: Int = 0) =
    new Word(Element.extractCoordinates(html), html.text, enclosingPageNumber, Element.fontFeaturesFromHTML(html))

  //def fromAbbyyXML( xml : xml.Node, enclosingPageNumber: Int = 0 ) : Word = Word

}