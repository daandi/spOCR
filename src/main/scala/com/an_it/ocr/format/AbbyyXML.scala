package com.an_it.ocr.format

import com.an_it.ocr._
import xml.NodeSeq


object AbbyyXML {

  def documentFromXML(xml: NodeSeq) = new Document(
     (xml \\ "page").toIndexedSeq map pageFromXML
  )

  def pageFromXML(xml: NodeSeq) : Page = new Page(
    0,
    pageCoordiantesFromXML(xml \\ "page"),
    (xml \\ "line").toIndexedSeq map lineFromXML
  )

  def lineFromXML(xml: NodeSeq) = Line(
    extractCoordinatesFromXML(xml),
    wordsFromChars( List.empty[Word],List.empty[NodeSeq],(xml \\ "charParams") toList) toIndexedSeq
  )

  def wordsFromChars(words: List[Word] , wordChars: List[NodeSeq], chars: List[NodeSeq]) : List[Word] = chars match {
    case Nil => wordFromXML(wordChars) :: words reverse
    case c :: rest if c.text == " " => wordsFromChars( wordFromXML(wordChars) :: words, List.empty[NodeSeq], rest )
    case c :: rest => wordsFromChars(words, c :: wordChars, rest)
  }


  def wordFromXML(nodes: List[NodeSeq]) = {
    val coords = ((0,0),(0,0))
    Word(coords, nodes.reverse.foldLeft("")(_ + _.text) )
  }

  def pageNumber(xml: NodeSeq) : Int =  0


  // Normalisieren !
  def extractCoordinatesFromXML(xml: NodeSeq) = {
    val left = xml \ "@l" text
    val top = xml \ "@t" text

    val right = xml \ "@r" text
    val bottom = xml \ "@b" text

    ((left toInt, top toInt),(right toInt, bottom toInt))
  }

  def pageCoordiantesFromXML(xml: NodeSeq) = {
    val width = xml \ "@width" text
    val height = xml \ "@height" text

    ((0,0),(width toInt,height toInt))
  }


  //l="4362" t="422" r="4418" b="487"
}
