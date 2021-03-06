package biz.neumann.ocr

import format.HOCR
import org.specs2.mutable.Specification
import org.specs2.specification.Scope

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

class DocumentSpec extends Specification {
  args.report(showtimes = true )

  "A Document" should {
   val d = HOCR.fromFolder(getClass.getResource("/doc/hocr/").getFile)
   "add all Pages from Files in a given folder" in {
     HOCR.fromFolder(getClass.getResource("/doc/hocr/").getFile).length shouldEqual  2
   }
   "access pageFromXML by applying an int to Document" in {
     pending
     //d(0).pageNumber shouldEqual  5
   }
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
