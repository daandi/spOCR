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
   val d = Document.fromFolder(getClass.getResource("/testdoc/hocr/").getFile)
   "add all Pages from Files in a given folder" in {
     Document.fromFolder(getClass.getResource("/testdoc/hocr/").getFile).length shouldEqual  272
   }
   "access page by applying an int to Document" in {
     d(59).pageNumber shouldEqual  60
   }
   "have a method to get Page by pageNumber" in {
     d.getPage(5).pageNumber shouldEqual 5
   }
  }
  "A document from real Path" should {
    Document.fromFolder("/Users/andi/Promotion/woerterbuecher/bsb10230264/hocr")
    pending
  }
  "Iterating over a document" should {
     val exampleDoc = Document.fromFolder(getClass.getResource("/testdoc/hocr/").getFile)
     "per page" in {
        val pageNumbers = exampleDoc map (p => p.pageNumber)
        pageNumbers.head shouldEqual 1
        pageNumbers.last shouldEqual 272
     }
     "changing values in an iteration should persist" in {
       exampleDoc.words foreach (_.nextWordDistance = Some(0))
       exampleDoc.words(0).nextWordDistance shouldEqual Some(0)
       exampleDoc.words foreach  (_.nextWordDistance = Some(42))
       exampleDoc.words(0).nextWordDistance shouldEqual Some(42)
     }
     "per line" in {
       exampleDoc.lines.size shouldEqual  11114
     }
     "per word" in {
       exampleDoc.words.size shouldEqual 99423
     }

   }

  "Document object" should {
    "extract PageNumber from imageFilePath" in {
      Document.getPageNumberFromImagePath("blibblub_000001.jpg") shouldEqual 1
      Document.getPageNumberFromImagePath("blibblub_000025.png") shouldEqual 25
    }
  }
}
