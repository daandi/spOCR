package biz.neumann

import org.specs2.mutable.Specification
import org.specs2.specification.Scope

/**
 * AN-iT
 * 
 * User: Andreas Neumann
 * Mail: andreas@neumann.biz
 * URL: http://www.an-it.com
 * Date: 31.10.11
 * Time: 20:27
 * Package: biz.neumann.parser
 */

class HTMLParserTest extends Specification {

  "The HTMLParser" should {
    "allow to parse WebPages as XML when an URL is given" in new initParser {

      (fromURL("http://www.an-it.com") \\ "h1" ).text.must_==("Andreas Neuman IT - AN-iT")
    }
    "parse HTML in Files as if working with XML" in new initParser {
      val file =  getClass.getResource("/Seite_Tagebuch_H_C_Lang_08.html").getFile
      val html = fromFile( file )
      (html \\ "div" \\ "@title").head.text must_== "bbox 0 0 1709 1709;ppageno 5"
    }
  }

  trait initParser extends HTMLParser with Scope

}

