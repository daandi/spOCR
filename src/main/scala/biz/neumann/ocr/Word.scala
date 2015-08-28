package biz.neumann.ocr

import xml.NodeSeq

/**
 * AN-iT
 * Andreas Neumann
 * andreas@neumann.biz
 * http://www.an-it.com
 * Date: 22.11.11
 * Time: 12:34
 */

case class Word(
                 coordinates: Coordinates,
                 text : String,
                 val enclosingPageNumber: Option[Int] = None) extends Element {

  def toHTML(zoom: Double = 1) : NodeSeq =
    <span class={"OCRWord"} data-text={text} style={ toCSS(zoom) }>{}</span>
}
