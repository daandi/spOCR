package biz.neumann.ocr

import format.HOCR
import org.specs2.mutable.{SpecificationWithJUnit, Specification}

/**
 * AN-iT
 * Andreas Neumann
 * andreas@neumann.biz
 * http://www.an-it.com
 * Date: 22.11.11
 * Time: 13:44
 */

class LineSpec extends Specification{
  "Line" should  {
   "be created from HTML" in {
      HOCR.lineFromHTML(<span class="line" title="bbox 1 2 3 4"></span>) shouldEqual  new Line( ((1,2),(3,4)), IndexedSeq[Word]() )
    }
    "be created from HTML with subElements" in {
      HOCR.lineFromHTML(
      <span class="line" title="bbox 1 2 3 4">
        <span class="ocrx_word" title="bbox 1 2 3 4">ein</span>
        <span class="ocrx_word" title="bbox 2 3 4 5">test</span>
      </span>) shouldEqual
        new Line( ((1,2),(3,4)), IndexedSeq[Word](new Word( ((1,2),(3,4)) , "ein"), new Word(((2,3),(4,5)),"test")) )
    }
    "have an iterator for words within" in {
      val dummyCoord = ((0,0),(0,0))
      val line = Line(dummyCoord, IndexedSeq[Word]( Word(dummyCoord,"Hier"),Word(dummyCoord,"läuft"),Word(dummyCoord,"ein"),Word(dummyCoord,"Test")))
      line.map(_.text) shouldEqual  IndexedSeq("Hier","läuft","ein","Test")
    }
  }
  "Display methods" should {
    val exampleLine =HOCR.lineFromHTML(
      <span class='ocr_line' title='bbox 212 1537 1133 1578'>
        <span class='ocrx_word' title='bbox 212 1538 263 1565'>Ein</span>
        <span class='ocrx_word' title='bbox 283 1537 357 1565'>Leoit.</span>
        <span class='ocrx_word' title='bbox 377 1538 439 1569'>Esra</span>
        <span class='ocrx_word' title='bbox 458 1539 482 1570'>2,</span>
        <span class='ocrx_word' title='bbox 500 1540 549 1571'>42;</span>
        <span class='ocrx_word' title='bbox 568 1538 670 1571'>Nehem.</span>
      </span>)
    " return plain-Text" in {
      exampleLine.toText shouldEqual  "Ein Leoit. Esra 2, 42; Nehem."
    }
  }
}