package com.an_it.ocr

import java.io.{PrintWriter, File}
import xml.NodeSeq
import collection.mutable.ListBuffer

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

class Page( val pageNumber: Int,
            val coordinates: ((Int, Int),(Int, Int)),
            val lines: IndexedSeq[Line],
            val fontFeatures: Option[List[Symbol]] = None) extends
Element  {

  var imagePath : Option[String] = None
  var baseDistance : Int = 0

  def words : IndexedSeq[Word] =
    lines map (_.words.toStream) reduceLeftOption(_ ++ _) getOrElse(Seq.empty[Word])toIndexedSeq

  def toText = lines map (_.toText) mkString("\n")

  def imageCSS(imgPath: String, w: Double,  h: Double) =
        "position:relative; top:0px, left:0px; background-image: url("  + imgPath + "); height:" + h + "px;" + "width:" + w + "px;"


  
  def toHTML(zoom: Double = 1, w: Double, h: Double, image: Option[String] = imagePath)  = image match {
    case Some(img) =>
      <div class="OCRPage" style={ imageCSS(img,w,h) }>
          { lines.map(_.toHTML(zoom)).foldLeft(NodeSeq.Empty)(_ ++ _) }
        </div>
    case None =>
      <div class="OCRPage" style={toCSS(1).replaceFirst("absolute","relative")}>
          { lines.map(_.toHTML(zoom)).foldLeft(NodeSeq.Empty)(_ ++ _) }
      </div>
  }

  def toHTML : NodeSeq = toHTML(1,width,height,imagePath)


  val enclosingPageNumber = pageNumber
}

object Page {
  val pageNumberExtractor = """[^;]+;ppageno\s*(\d+)""".r

 def fromHTML( html : xml.NodeSeq ) : Page = {
   val pageHTML = (html \ "body" \ "div" ).head
   new Page( extractPageNumber(pageHTML), Element.extractCoordinates(pageHTML), linesFromHOCR(pageHTML))
 }

 def extractPageNumber(html: xml.NodeSeq) : Int =  (html \ "@title").text match {
   case pageNumberExtractor(pageNumber) => pageNumber.toInt
   case other => throw new Exception (other + " is no valid title string for a page" )
  }

 def linesFromHOCR(html: xml.NodeSeq) : IndexedSeq[Line] = (html \ "div" \ "p" \ "span") map (Line.fromHOCR(_, extractPageNumber(html))) toIndexedSeq

}
