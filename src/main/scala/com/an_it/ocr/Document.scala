package com.an_it.ocr

/**
 * AN-iT
 * 
 * User: Andreas Neumann
 * Mail: andreas.neumann@an-it.com
 * URL: http://www.an-it.com
 * Date: 13.11.11
 * Time: 11:06
 * Package: com.an_it.ocr
 */


import com.an_it.HTMLParser
import java.io.File
import collection.parallel.mutable.ParArray

class Document( val pages : IndexedSeq[Page] )
  extends IndexedSeq[Page] {

  lazy val pageNumberMapping : Map[Int,Page] = pages map  {page => Map(page.pageNumber -> page)}  reduce  (_ ++ _)

  def length = pages.size
  def apply(idx: Int) : Page = pages.apply(idx)
  def getPage(pageNumber : Int)  = pageNumberMapping(pageNumber)

  def lines : IndexedSeq[Line] = pages map(_.lines) reduceLeft (_ ++ _)
  def words : IndexedSeq[Word] = lines.par.map(_.words).reduce(_ ++ _)

  def toHTML = pages map {_.toHTML } reduce (_ ++ _)

  def createPagesHash( pages : ParArray[Page] ) : Map[Int,Page] =
      (Map[Int, Page]() /: pages) { case (partialPageHash,page) => partialPageHash ++ Map(page.pageNumber -> page) }

}

object Document {

  val pageNumberExtractor = """[^_]+_(?:0)*(\d+).*""".r
  
  def fromFolder(path: String)  =  new Document(pagesFromFolder(path))
  def fromFolderWithImage(path: String, imageFolder: String) =
    docWithImages(new Document(pagesFromFolder(path)), imageFolder)

  def docWithImages(doc: Document, imageFolder: String) = {
    val imageFiles = new File(imageFolder).listFiles.filter(_.toString.endsWith(".jpg"))
    imageFiles.par foreach { imageFile =>
      val pageNumber = getPageNumberFromImagePath(imageFile.toString).toInt
      try {
        doc.getPage( pageNumber ).imagePath = Some(imageFile.toString)
      }
      catch {
        case e => println("something went wrong while adding image to page" + e  )
      }
    }
    doc
  }

  def getPageNumberFromImagePath(imagePath : String) : Int = imagePath match {
        case pageNumberExtractor(pageNumber) =>  pageNumber.toInt
        case malformed => throw new Error("Konnte Bilddatei: " + malformed +" keine Seite zuordnen: ");
  }

  def pagesFromFolder(folderPath: String) : IndexedSeq[Page]= {
    val files = new File(folderPath).listFiles
    files map (pageFromFile(_))
  }


  def pageFromFile(file : File) : Page = Page fromHTML( new HTMLParser().fromFile(file) )

  
  implicit def stringToFile(in: String) : File = new File(in)

}