package com.an_it.ocr

import java.io.PrintWriter
import xml.NodeSeq

/**
 * AN-iT
 * Andreas Neumann
 * andreas.neumann@an-it.com
 * http://www.an-it.com
 * Date: 29.12.11
 * Time: 12:12
 */

trait temporaryHTMLView {
 
  def savePageToFile(html: NodeSeq,  f : String) {

   val htmlNodes = <html>
          <head><link href="test.css" rel="stylesheet" type="text/css"></link></head>
          <body>{ html }</body>
        </html>

   val pw = new PrintWriter(f)
   pw.write(htmlNodes toString)
   pw.close()
  }

}