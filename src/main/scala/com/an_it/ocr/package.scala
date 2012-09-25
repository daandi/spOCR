package com.an_it

/**
 * Smarchive GmbH
 * User: Andreas Neumann
 * Email: a.neumann@smarchive.de
 * Date: 25.09.12
 * Time: 10:38
 */
package object ocr {
  type Pages = IndexedSeq[Page]
  type Blocks = IndexedSeq[Block]
  type Lines = IndexedSeq[Line]
  type Words = IndexedSeq[Word]
  type Coordinates = ((Int,Int),(Int,Int))


}
