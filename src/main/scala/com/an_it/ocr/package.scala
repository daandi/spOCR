package com.an_it

/**
 * Smarchive GmbH
 * User: Andreas Neumann
 * Email: a.neumann@smarchive.de
 * Date: 25.09.12
 * Time: 10:38
 */
package object ocr {

  implicit class Pages(in: IndexedSeq[Page]) extends IndexedSeq[Page]{

    val pages = in.sortWith( _.pageNumber < _.pageNumber )

    override def apply(idx: Int) = pages(idx)

    override def length = pages.length
  }



  type Blocks = IndexedSeq[Block]
  type Lines = IndexedSeq[Line]
  type Words = IndexedSeq[Word]
  type Coordinates = ((Int,Int),(Int,Int))


}
