package com.an_it.ocr

import org.specs2.mutable.Specification

/**
 * AN-iT
 * Andreas Neumann
 * andreas.neumann@an-it.com
 * http://www.an-it.com
 * Date: 29.11.11
 * Time: 08:38
 */

class ElementSpec extends Specification {

  trait tE extends org.specs2.specification.Scope {
    val testElement = new Element{
          def toHTML(zoom:Double) = <html></html>
          val coordinates = ((1,2),(3,4))
          val enclosingPage = 0
          val enclosingPageNumber = 0
          val fontFeatures = None
    }
  }
}