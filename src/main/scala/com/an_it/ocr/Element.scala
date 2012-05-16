package com.an_it.ocr

import xml.NodeSeq
import util.parsing.json.{JSONArray, JSONObject}
import collection.mutable.{ListBuffer, MutableList, ArrayBuffer}


/**
 * AN-iT
 * Andreas Neumann
 * andreas.neumann@an-it.com
 * http://www.an-it.com
 * Date: 10.11.11
 * Time: 10:56
 */

abstract class Element extends BoundingBox {
  // coordinates for BoundingBox Trait are given in constructor

  val fontFeatures : Option[List[Symbol]]
  private val features =  ListBuffer.empty[Symbol]

  val enclosingPageNumber : Int

  def addFeature(feature: Symbol) {
    features += feature
  }
  def addFeatures(fs: List[Symbol]) {
    fs foreach (addFeature(_))
  }

  def getAllFeatures = features.toList  ::: fontFeatures.getOrElse(List.empty[Symbol])

  def normalizedLeft(base: Int) = left - base

  def featuresToCSS = features.distinct.map{ s:Symbol => s.name}.sorted.mkString("_")

  def featuresDataAttribute = features.toList map(_.name) mkString(",")
}

object Element {

  val coordinatesExtractor = """bbox (\d+) (\d+) (\d+) (\d+).*""".r
  val styleExtractor = """([^:]+):([^;]+);""".r

  def extractOCRClass(html: NodeSeq) =  Symbol( (html \ "@class" ).text)

  def extractCoordinates(html: NodeSeq) : ( (Int, Int), (Int, Int) )= {
    (html \ "@title").text match {
      case coordinatesExtractor(x1,y1,x2,y2) => ( (x1.toInt, y1.toInt ),(x2.toInt, y2.toInt) )
      case e => throw new Exception("No valid coordiantes string in" + e)
    }
  }

  def extractFontFeatures(cssStyles: String) : Option[List[Symbol]]= {
    val styleList = for {
      styleExtractor(n, v) <- (styleExtractor findAllIn cssStyles)
    } yield n match {
        case "font-style" => v.split(" ").toList map {styleSymbol(_)}
        case _ => List(styleSymbol(v))
      }

    styleList.flatten.toList match {
      case List() => None
      case styles => Some(styles)
    }

  }

  def styleSymbol(s: String) = Symbol("""\W""".r replaceAllIn (s, ""))


  def fontFeaturesFromHTML(html : NodeSeq) = extractFontFeatures( (html \ "@style").text )


}