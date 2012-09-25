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

trait Element extends BoundingBox {
  // coordinates for BoundingBox Trait are given in constructor
  val enclosingPageNumber : Option[Int]
  def normalizedLeft(base: Int) = left - base
}

