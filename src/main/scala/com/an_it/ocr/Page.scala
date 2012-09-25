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
            val coordinates: Coordinates,
            val blocks: Blocks,
            val imgPath : Option[String] = None
            ) extends Element  {

  def words : Words  = blocks flatMap( _ words)
  def lines : Lines = blocks flatMap (_ lines)

  def toText = lines map (_.toText) mkString("\n")

  def imageCSS(imgPath: String, w: Double,  h: Double) =
        "position:relative; top:0px, left:0px; background-image: url("  + imgPath + "); height:" + h + "px;" + "width:" + w + "px;"


  
  def toHTML(zoom: Double = 1, w: Double, h: Double, image: Option[String] = imgPath)  = image match {
    case Some(img) =>
      <div class="OCRPage" style={ imageCSS(img,w,h) }>
          { lines.map(_.toHTML(zoom)).foldLeft(NodeSeq.Empty)(_ ++ _) }
        </div>
    case None =>
      <div class="OCRPage" style={toCSS(1).replaceFirst("absolute","relative")}>
          { lines.map(_.toHTML(zoom)).foldLeft(NodeSeq.Empty)(_ ++ _) }
      </div>
  }

  def toHTML : NodeSeq = toHTML(1,width,height,imgPath)


   val enclosingPageNumber = Some(pageNumber)
}
