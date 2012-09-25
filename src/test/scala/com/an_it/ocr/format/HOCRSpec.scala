package com.an_it.ocr.format

import org.specs2.mutable.Specification

/**
 * Smarchive GmbH
 * User: Andreas Neumann
 * Email: a.neumann@smarchive.de
 * Date: 25.09.12
 * Time: 11:04
 */
class HOCRSpec extends Specification{

  "HOCR object" should {
    "extract PageNumber from imageFilePath" in {
      HOCR.getPageNumberFromImagePath("blibblub_000001.jpg") shouldEqual 1
      HOCR.getPageNumberFromImagePath("blibblub_000025.png") shouldEqual 25
    }
  }

}
