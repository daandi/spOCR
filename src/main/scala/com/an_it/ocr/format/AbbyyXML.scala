package com.an_it.ocr.format

import com.an_it.ocr._
import xml.NodeSeq


object AbbyyXML {

  def documentFromFile(pathToFile: String) = documentFromXML(xml.XML.load(pathToFile))

  def documentFromXML(xml: NodeSeq) = new Document(
     (xml \\ "page").toIndexedSeq map pageFromXML
  )

  def pageFromXML(xml: NodeSeq) : Page = {
    val pageCoords = pageCoordiantesFromXML(xml \\ "page")
    val blocks : Blocks =  (xml \\ "block").toIndexedSeq map blockFromXML
    Page(
      0,
     pageCoords,
     blocks
    )
  }

  def blockFromXML(xml: NodeSeq) : Block = Block(
      extractCoordinatesFromXML(xml),
      (xml \\ "line").toIndexedSeq map lineFromXML
    )


  def lineFromXML(xml: NodeSeq) = Line(
    extractCoordinatesFromXML(xml),
    wordsFromChars( List.empty[Option[Word]],List.empty[NodeSeq],(xml \\ "charParams") toList).flatten toIndexedSeq
  )

  def wordsFromChars(words: List[Option[Word]] , wordChars: List[NodeSeq], chars: List[NodeSeq]) : List[Option[Word]] = chars match {
    case Nil => wordFromXML(wordChars) :: words reverse
    case c :: rest if c.text == " " => wordsFromChars( wordFromXML(wordChars) :: words, List.empty[NodeSeq], rest )
    case c :: rest => wordsFromChars(words, c :: wordChars, rest)
  }


  def wordFromXML(chars: List[NodeSeq]) : Option[Word] = chars match {
    case Nil => None
    case nodes => {
      val left = (nodes.last \ "@l").text.toInt
      val right = (nodes.head \ "@r").text.toInt
      val top = (nodes.last \ "@t").text.toInt
      val bottom = (nodes.last \ "@b").text.toInt


      Some(Word(((left,top),(right,bottom)), nodes.reverse.foldLeft("")(_ + _.text) ))
    }
  }

  def pageNumber(xml: NodeSeq) : Int =  0

  def extractCoordinatesFromXML(xml: NodeSeq) = {
    val leftDistance = (xml \ "@l").text.toInt
    val topDistance = (xml \ "@t").text.toInt

    val rightDistance = (xml \ "@r").text.toInt
    val bottomDistance = (xml \ "@b").text.toInt

    ((leftDistance,topDistance),(rightDistance,bottomDistance))


  }

  def normalize(coords: Coordinates, pageCoords: Coordinates) : Coordinates = {
    val ((leftDistance,topDistance),(rightDistance,bottomDistance)) = coords
    val (_, (pageWidth, pageHeight)) =  pageCoords

    val right = leftDistance + pageWidth - rightDistance - leftDistance
    val bottom = topDistance+ pageHeight - bottomDistance - topDistance

    ((leftDistance, topDistance),(right, bottom))
  }



  def pageCoordiantesFromXML(xml: NodeSeq) = {
    val width = xml \ "@width" text
    val height = xml \ "@height" text

    ((0,0),(width toInt,height toInt))
  }


  //l="4362" t="422" r="4418" b="487"
}
