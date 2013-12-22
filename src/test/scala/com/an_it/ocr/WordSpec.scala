package com.an_it.ocr

import format.HOCR
import org.specs2.Specification

/**
 * AN-iT
 * Andreas Neumann
 * andreas.neumann@an-it.com
 * http://www.an-it.com
 * Date: 22.11.11
 * Time: 13:31
 */

class WordSpec extends Specification {def is= s2"""
Creates a wordFromXML                                 ${ new Word(((1,2),(3,4)), "test", Some(0) ) must_== exampleWord }
has a getter for text                                 ${ exampleWord.text shouldEqual  "test" }
has Coordinates getter                                ${ exampleWord.coordinates shouldEqual ((1,2),(3,4))}
can be created from HTML                              $fromHOCR

"""

lazy val exampleWord = new Word(((1,2),(3,4)), "test", Some(0))
def fromHOCR = HOCR.wordfromHTML(<span class='doesnt_matter' title="bbox 1 2 3 4">test</span>, 0).
  shouldEqual( new Word(((1,2),(3,4)), "test",Some(0)))

}
