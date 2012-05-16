package com.an_it

import org.specs2.mutable.Specification
import org.specs2.specification.Scope

/**
 * AN-iT
 * 
 * User: Andreas Neumann
 * Mail: andreas.neumann@an-it.com
 * URL: http://www.an-it.com
 * Date: 31.10.11
 * Time: 20:27
 * Package: com.an_it.parser
 */

class HTMLParserTest extends Specification {

  "The HTMLParser" should {
    "allow to parse WebPages as XML when an URL is given" in new initParser {
      val html = parser.fromURL("http://www.an-it.com")
      (html \\ "h1" ).text.must_==("Andreas Neuman IT - AN-iT")
    }
    "parse HTML in Files as if working with XML" in new initParser {
      val file =  getClass.getResource("/test.html").getFile
      val html = parser.fromFile( file )
      (html \\ "div" \\ "@title").head.text must_== "bbox 0 0 1326 1326;ppageno 33"
    }
  }

}

trait initParser extends Scope {
  lazy val parser = new HTMLParser
}