package com.an_it.ocr

import format.HOCR
import org.specs2.mutable.{SpecificationWithJUnit, Specification}
import org.specs2.specification.Scope

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

class DocumentSpec extends Specification{
  args.report(showtimes = true )

  "A Document" should {
   val d = HOCR.fromFolder(getClass.getResource("/doc/hocr/").getFile)
   "add all Pages from Files in a given folder" in {
     HOCR.fromFolder(getClass.getResource("/doc/hocr/").getFile).length shouldEqual  2
   }
   "access pageFromXML by applying an int to Document" in {
     d(0).pageNumber shouldEqual  5
   }.pending
   "have a method to get Page by pageNumber" in {
     d.getPage(8).pageNumber shouldEqual 8
   }
  }
  "Iterating over a documentFromXML" should {
     val exampleDoc = HOCR.fromFolder(getClass.getResource("/doc/hocr/").getFile)
     "per pageFromXML" in {
        val pageNumbers = exampleDoc map (p => p.pageNumber)
        pageNumbers shouldEqual List(5,8)
     }

     "per lineFromXML" in {
       exampleDoc.lines.size shouldEqual  78
     }
     "per wordFromXML" in {
       exampleDoc.words.size shouldEqual 725
     }

   }


}
