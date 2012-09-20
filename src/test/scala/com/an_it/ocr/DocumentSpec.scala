package com.an_it.ocr

import org.specs2.mutable.Specification
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
   val d = Document.fromFolder(getClass.getResource("/doc/hocr/").getFile)
   "add all Pages from Files in a given folder" in {
     Document.fromFolder(getClass.getResource("/doc/hocr/").getFile).length shouldEqual  2
   }
   "access pageFromXML by applying an int to Document" in {
     d(0).pageNumber shouldEqual  5
   }
   "have a method to get Page by pageNumber" in {
     d.getPage(8).pageNumber shouldEqual 8
   }
  }
  "Iterating over a documentFromXML" should {
     val exampleDoc = Document.fromFolder(getClass.getResource("/doc/hocr/").getFile)
     "per pageFromXML" in {
        val pageNumbers = exampleDoc map (p => p.pageNumber)
        pageNumbers.head shouldEqual 5
        pageNumbers.last shouldEqual 8
     }
     "changing values in an iteration should persist" in {
       exampleDoc.words foreach (_.nextWordDistance = Some(0))
       exampleDoc.words(0).nextWordDistance shouldEqual Some(0)
       exampleDoc.words foreach  (_.nextWordDistance = Some(42))
       exampleDoc.words(0).nextWordDistance shouldEqual Some(42)
     }
     "per lineFromXML" in {
       exampleDoc.lines.size shouldEqual  78
     }
     "per wordFromXML" in {
       exampleDoc.words.size shouldEqual 725
     }

   }

  "Document object" should {
    "extract PageNumber from imageFilePath" in {
      Document.getPageNumberFromImagePath("blibblub_000001.jpg") shouldEqual 1
      Document.getPageNumberFromImagePath("blibblub_000025.png") shouldEqual 25
    }
  }
}
