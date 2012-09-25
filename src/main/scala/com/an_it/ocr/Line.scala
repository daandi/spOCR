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
                 val enclosingPageNumber: Option[Int] = None,
                 val fontFeatures: Option[List[Symbol]] = None
                 ) extends Element with IndexedSeq[Word]{
  def toText: String = words.map(_.text).mkString(" ")

  def toHTML(zoom: Double = 1) : NodeSeq =
    <span class= {"OCRLine"} style={toCSS(zoom)}></span> ++
      words flatMap(_.toHTML).reduce(_ ++ _)

  def length = words.length

  def apply(idx : Int) = words.apply(idx)
}
