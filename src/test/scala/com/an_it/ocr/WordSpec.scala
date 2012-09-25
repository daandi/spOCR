package com.an_it.ocr

import format.HOCR
import org.specs2.mutable.Specification
import org.specs2.specification.Scope
/**
 * AN-iT
 * Andreas Neumann
 * andreas.neumann@an-it.com
 * http://www.an-it.com
 * Date: 22.11.11
 * Time: 13:31
 */

class WordSpec extends Specification {
  "Creates a wordFromXML" in {
    val word = new Word(((1,2),(3,4)), "test", Some(0))
    "Word" should  {
      "have a getter for text" in {
        word.text shouldEqual  "test"
      }
      "have Coordinates getter" in {
        word.coordinates shouldEqual ((1,2),(3,4))
      }
    }
    "from HTML" in {
      HOCR.wordfromHTML(<span class='doesnt_matter' title="bbox 1 2 3 4">test</span>, 0) shouldEqual new Word(((1,2),(3,4)), "test",Some(0))
    }

    trait ew extends Scope{
      val exampleWord = new Word(((1,2),(3,4)), "test", Some(0))
    }
  }

}

