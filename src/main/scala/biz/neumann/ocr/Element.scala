package biz.neumann.ocr

/**
 * AN-iT
 * Andreas Neumann
 * andreas@neumann.biz
 * http://www.an-it.com
 * Date: 10.11.11
 * Time: 10:56
 */

trait Element extends BoundingBox {
  // coordinates for BoundingBox Trait are given in constructor
  val enclosingPageNumber : Option[Int]
  def normalizedLeft(base: Int) = left - base
}

