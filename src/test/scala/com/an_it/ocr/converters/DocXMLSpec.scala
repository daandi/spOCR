package com.an_it.ocr.converters

import org.specs2.mutable.Specification
import com.an_it.ocr.{Line, Page, Word, Document}

/**
 * AN-iT
 * Andreas Neumann
 * andreas.neumann@an-it.com
 * http://www.an-it.com
 * Date: 24.04.12
 * Time: 11:08
 */

class DocXMLSpec extends Specification{
  val folder = getClass.getResource("/doc/hocr").getFile
  println(folder)
  val doc =  Document.fromFolder(folder)

  val converter = new DocXML(doc)

  "DocXML" should {
    "Convert an Word to a NodeSeq" in {
      val w = Word(((1,2),(3,4)),"test")
      converter.toTokenXML(w,1,new Page(1,((0,0),(10,20)),IndexedSeq.empty[Line])) must beEqualToIgnoringSpace(
        <token token_id="1" isNormal="false">
          <wOCR>test</wOCR>
          <wOCR_lc>test</wOCR_lc>
          <wCorr></wCorr>
          <coord r="7" t="2" b="16" l="1"></coord>
          <abbyy_suspicious value="false"></abbyy_suspicious>
          <groundtruth verified="false">
            <classified>unknown</classified>
            <wOrig></wOrig>
            <wOrig_lc></wOrig_lc>
            <baseWord></baseWord>
            <histTrace></histTrace>
            <ocrTrace></ocrTrace>
          </groundtruth>
        </token>)
    }
    "convert a pageFromXML to a NodeSeq wit all pages" in {
      val p = doc.getPage(5)
      (converter.pageToXML(p) \\ "token").toSeq.size must_== p.words.size
    }
    "convert doc to docXML" in {
      (converter.toXML \\ "pageFromXML").toSeq.size must_== doc.pages.size
    }
    "save xml to file" in {
      converter.toFile("docXMLTest.xml")
      pending
    }
  }

}
