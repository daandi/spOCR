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
  "Element" should {

    "have features" in new tE{
      testElement.getAllFeatures.toList shouldEqual List()
    }
    "have a featuresDataAttribute Method" in new tE{
      testElement.featuresDataAttribute shouldEqual ""
      testElement.addFeature( 'test)
      testElement.addFeature('aTest)
      testElement.featuresDataAttribute shouldEqual "test,aTest"
    }
    "a method to add features" in new tE{
      testElement.addFeatures(List('a,'b))
      testElement.addFeature('c)
      testElement.getAllFeatures.toList shouldEqual List('a,'b,'c)
    }
  }

  "Element companion object" should {
    "have a method to extract font-style from hocr" in {
       Element.extractFontFeatures("""font-size:8pt;font-family:"Times New Roman";font-style:"bold italic";"""") shouldEqual Some( List(Symbol("8pt"), 'TimesNewRoman, 'bold, 'italic))
    }
    "also from html" in {
      Element.fontFeaturesFromHTML(<span class="ocrx_word" title="bbox 1216 1245 1354 1269" style="font-size:8pt;font-family:'Times New Roman';font-style:bold" >Schiste</span>) shouldEqual Some(List(Symbol("8pt"), 'TimesNewRoman))
    }
  }
}

trait tE extends org.specs2.specification.Scope {
  val testElement = new Element{
        def toHTML(zoom:Double) = <html></html>
        val coordinates = ((1,2),(3,4))
        val enclosingPage = 0
        val enclosingPageNumber = 0
        val fontFeatures = None
  }
}