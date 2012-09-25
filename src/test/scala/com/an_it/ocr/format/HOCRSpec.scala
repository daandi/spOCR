package com.an_it.ocr.format

import org.specs2.mutable.Specification
import com.an_it.ocr.{Word, Line, Block}

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

  "HOCR Block" should {
    "be created frtom HTML" in {
      val html =
        <div class="ocrx_block" title="bbox 0 111 1472 2270">
          <p class="ocr_par"  title="bbox 174 4 916 38" align="Justified" leftIndent="100" startIndent="1900" lineSpacing="1056">
            <span class="ocr_line" baseline="249"  title="bbox 264 216 1462 256">
              <span class="ocrx_word" title="bbox 264 216 333 248">Den</span>
                <span class="ocrx_word" title="bbox 356 216 402 248">20.</span>
                <span class="ocrx_word" title="bbox 426 216 620 249">Novembris</span>
              </span>
            </p>
          </div>



          HOCR.blockFromHTML(html).words.size mustEqual 3
    }
  }



}
