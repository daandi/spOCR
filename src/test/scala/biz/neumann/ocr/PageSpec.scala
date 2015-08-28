package biz.neumann.ocr

import format.HOCR
import org.specs2.specification.Scope
import biz.neumann.HTMLParser
import org.specs2.mutable.Specification
import java.io.PrintWriter

/**
 * AN-iT
 * 
 * User: Andreas Neumann
 * Mail: andreas@neumann.biz
 * URL: http://www.an-it.com
 * Date: 13.11.11
 * Time: 11:07
 * Package: biz.neumann.ocr
 */

class PageSpec extends Specification {
  "Page" should {
    "be created from HTML" in new parsedText{
      HOCR.pageFromHTML(testHTML) shouldNotEqual null
    }
    "have a lineFromXML-iterator" in  new parsedText {
      examplePage.lines.toList(5).toText shouldEqual "1621, do bin gen Memmingen zogen, im Namen Gottes, mit Maifter"
    }
    "have a wordFromXML-iterator" in  new parsedText {
      examplePage.words.map(_.text).toList(77) shouldEqual "und"
    }
  }

  "Helper methods" should  {
    "extract Page Number from title string" in {
      HOCR.extractPageNumber(<div class='ocr_page' title='bbox 0 0 1326 1326;ppageno 33'></div>) shouldEqual 33
    }
  }

  "Iterator methods" should  {
    "iterate lines" in new parsedText{
      examplePage.lines.size shouldEqual 45
    }
    "iterate words" in new parsedText{
      examplePage.words.size shouldEqual 415
    }
  }

  "Display methods" should  {
    "Give Text as String" in new parsedText{
      examplePage.toText.length shouldEqual  2443
    }
    "build an html" in new parsedText {
      (examplePage.toHTML \\ "span" ).filter(span => (span \\ "@class").text == "OCRLine").size shouldEqual 45
      (examplePage.toHTML \\ "span").filter(span => (span \\ "@class").text == "OCRWord").size shouldEqual 415
    }
    "given an imagePath toHTML should create an HTML on an image " in new parsedText with temporaryHTMLView{
      val img = getClass.getResource("/Seite_Tagebuch_H_C_Lang_08.jpg").getPath
      val f = examplePage.copy(imgPath= Some(img))
      savePageToFile(f.toHTML ,"test_abby.html")
      pending
    }
  }

  trait parsedText extends Scope {
    val parser = new HTMLParser
    val testHTML = parser.fromFile( this.getClass.getResource("/Seite_Tagebuch_H_C_Lang_08.html").getFile )
    val examplePage = HOCR.pageFromHTML(testHTML)
  }

}

