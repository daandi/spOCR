package com.an_it.ocr

import org.specs2.specification.Scope
import com.an_it.HTMLParser
import org.specs2.mutable.Specification
import java.io.PrintWriter

/**
 * AN-iT
 * 
 * User: Andreas Neumann
 * Mail: andreas.neumann@an-it.com
 * URL: http://www.an-it.com
 * Date: 13.11.11
 * Time: 11:07
 * Package: com.an_it.ocr
 */

class PageSpec extends Specification {
  "Page" should {
    "be created from HTML" in new parsedText{
      Page.fromHTML(testHTML) shouldNotEqual null
    }
    "have a line-iterator" in  new parsedText {
      examplePage.lines.toList(5).toText shouldEqual "Athlai. Dee Herr zerreißet oder zerbricht. Einer von den"
    }
    "have a word-iterator" in  new parsedText {
      examplePage.words.map(_.text).toList(77) shouldEqual "Betters"
    }
  }

  "Helper methods" should  {
    "extract Page Number from title string" in {
      Page.extractPageNumber(<div class='ocr_page' title='bbox 0 0 1326 1326;ppageno 33'></div>) shouldEqual 33
    }
  }

  "Iterator methods" should  {
    "iterate lines" in new parsedText{
      examplePage.lines.size shouldEqual 43
    }
    "iterate words" in new parsedText{
      examplePage.words.size shouldEqual 346
    }
  }

  "Display methods" should  {
    "Give Text as String" in new parsedText{
      examplePage.toText.length shouldEqual  2131
    }
    "build an html" in new parsedText {
      (examplePage.toHTML \\ "span" ).filter(span => (span \\ "@class").text == "OCRLine").size shouldEqual 43
      (examplePage.toHTML \\ "span").filter(span => (span \\ "@class").text == "OCRWord").size shouldEqual 346
    }
    "given an imagePath toHTML should create an HTML on an image" in new parsedText with temporaryHTMLView{
      examplePage.imagePath = Some(getClass.getResource("/test.jpg").getPath)
      savePageToFile(examplePage.toHTML,"test.html")
      pending
    }
    "given an imagePath toHTML should create an HTML on an image with image Propotions inferred" in new parsedText with temporaryHTMLView{
      examplePage.imagePath = Some(getClass.getResource("/test.jpg").getPath)
      savePageToFile(examplePage.toHTML ,"test_inferred.html")
      pending
    }
  }

  trait parsedText extends Scope {
    val parser = new HTMLParser
    val testHTML = parser.fromFile( this.getClass.getResource("/test.html").getFile )
    val examplePage = Page.fromHTML(testHTML)
  }

}

