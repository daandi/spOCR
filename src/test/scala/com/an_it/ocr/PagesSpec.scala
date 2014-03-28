package com.an_it.ocr

import org.specs2.Specification

/**
 * User: Andreas Neumann
 * Date: 28.03.14
 * Time: 12:58
 */
class PagesSpec extends Specification{def is =s2"""
Pages
  The implicit Wrapper  wraps a Sequence of pages to guarantee that pages are ordered by page number      $ordering

"""

  def ordering = {
    val ps = Vector(
      Page(pageNumber = 10, coordinates= ((0,0),(0,0)), blocks=  Vector()),
      Page(pageNumber = 11, coordinates= ((0,0),(0,0)), blocks=  Vector()),
      Page(pageNumber = 7, coordinates= ((0,0),(0,0)), blocks=  Vector()),
      Page(pageNumber = 150, coordinates= ((0,0),(0,0)), blocks=  Vector())
    )

    ps.pages.map(_.pageNumber) mustEqual Vector(7,10,11,150)
  }

}
