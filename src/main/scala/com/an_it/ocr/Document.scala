package com.an_it.ocr

/**
 * AN-iT
 * 
 * User: Andreas Neumann
 * Mail: andreas.neumann@an-it.com
 * URL: http://www.an-it.com
 * Date: 13.11.11
 * Time: 11:06
 * Package: com.an_it.ocr
 */


import collection.parallel.mutable.ParArray

class Document( val pages : Pages )
  extends IndexedSeq[Page] {

  lazy val pageNumberMapping : Map[Int,Page] = pages map  (page => Map(page.pageNumber -> page)) reduce  (_ ++ _)

  def length = pages.size
  def apply(idx: Int) : Page = pages(idx)
  def getPage(pageNumber : Int)  = pageNumberMapping(pageNumber)

  def lines : IndexedSeq[Line] = pages flatMap (_.lines)
  def words : IndexedSeq[Word] = lines flatMap (_.words)

  def toHTML = pages flatMap (_.toHTML )

  def createPagesHash( pages : ParArray[Page] ) : Map[Int,Page] =
      (Map[Int, Page]() /: pages) { case (partialPageHash,page) => partialPageHash ++ Map(page.pageNumber -> page) }

}

