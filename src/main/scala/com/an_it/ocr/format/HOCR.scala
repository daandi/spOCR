package com.an_it.ocr.format

import java.io.File
import com.an_it.ocr._
import com.an_it.HTMLParser
import xml.NodeSeq

/**
 * AN-iT
 * User: Andreas Neumann
 * Email: andreas.neumann@an-it.com
 * Date: 25.09.12
 * Time: 11:04
 */
object HOCR {

  val coordinatesExtractor = """bbox (\d+) (\d+) (\d+) (\d+).*""".r
  val styleExtractor = """([^:]+):([^;]+);""".r

  def extractOCRClass(html: NodeSeq) =  Symbol( (html \ "@class" ).text)

  def extractCoordinates(html: NodeSeq) : Coordinates = {
    (html \ "@title").text match {
      case coordinatesExtractor(x1,y1,x2,y2) => ( (x1.toInt, y1.toInt ),(x2.toInt, y2.toInt) )
      case e => throw new Exception("No valid coordiantes string in" + e)
    }
  }

  val pagePathNumberExtractor = """[^_]+_(?:0)*(\d+).*""".r
  val pageNumberExtractor = """[^;]+;ppageno\s*(\d+)""".r

  def fromFolder(path: String)  =  new Document(pagesFromFolder(path))
  def fromFolderWithImage(path: String, imageFolder: String) =
    docWithImages(new Document(pagesFromFolder(path)), imageFolder)

  //TODO
  def docWithImages(doc: Document, imageFolder: String) = {
    val imageFiles = new File(imageFolder).listFiles.filter(_.toString.endsWith(".jpg"))
    imageFiles.par foreach { imageFile =>
      val pageNumber = getPageNumberFromImagePath(imageFile.toString).toInt
      try {
        //doc.getPage( pageNumber ).imagePath = Some(imageFile.toString)
      }
      catch {
        case e: Throwable => println("something went wrong while adding image to pageFromXML" + e  )
      }
    }
    doc
  }

  def getPageNumberFromImagePath(imagePath : String) : Int = imagePath match {
    case pagePathNumberExtractor(pageNumber) =>  pageNumber.toInt
    case malformed => throw new Error("Konnte Bilddatei: " + malformed +" keine Seite zuordnen: ");
  }

  def pagesFromFolder(folderPath: String) : IndexedSeq[Page]= {
    val files = new File(folderPath).listFiles
    files map (pageFromFile(_))
  }


  def pageFromFile(file : File) : Page = pageFromHTML( new HTMLParser().fromFile(file) )

  def pageFromHTML( html : NodeSeq ) : Page = {
    val pageHTML = (html \ "body" \ "div" ).head
    new Page( extractPageNumber(pageHTML), extractCoordinates(pageHTML), blocksFromHTML(pageHTML))
  }

  def extractPageNumber(html: xml.NodeSeq) : Int =  (html \ "@title").text match {
    case pageNumberExtractor(pageNumber) => pageNumber.toInt
    case other => throw new Exception (other + " is no valid title string for a pageFromXML" )
  }

  def blocksFromHTML(html: NodeSeq) : Blocks = (html \ "div") map blockFromHTML toIndexedSeq
  def blockFromHTML(html: NodeSeq) : Block = Block(
    extractCoordinates(html),
    linesFromHTML(html)
  )


  def linesFromHTML(html: NodeSeq) : Lines = (html \ "p" \ "span") map lineFromHTML toIndexedSeq
  def lineFromHTML(html : NodeSeq) = Line(
    extractCoordinates(html),
    buildWordSeq(html)
  )

  def isOCRWord(html : NodeSeq) = extractOCRClass(html) == 'ocrx_word

  def buildWordSeq(html: NodeSeq) : Words =
    (html \ "span") filter isOCRWord  map {w => new Word(extractCoordinates(w),w text) } toIndexedSeq

  def wordfromHTML( html : xml.Node,  enclosingPageNumber: Int = 0) =
    new Word(extractCoordinates(html), html.text, Some(enclosingPageNumber))



}
