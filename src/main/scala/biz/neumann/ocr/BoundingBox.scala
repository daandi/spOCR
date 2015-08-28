package biz.neumann.ocr

/**
 * AN-iT
 * Andreas Neumann
 * andreas@neumann.biz
 * http://www.an-it.com
 * Date: 10.11.11
 * Time: 10:56
 */

trait BoundingBox {
  val coordinates: ((Int, Int),(Int, Int))
  lazy val ((left, top),(right, bottom)) = coordinates // has to be be lazy!
  lazy val height =  bottom - top
  lazy val width = right - left

  def encloses(other: BoundingBox ) =  {
    left <= other.left &&
    right >= other.right &&
    top <= other.top &&
    bottom >= other.bottom
  }

  def enclosed_by(other: BoundingBox) = other.encloses(this)

  def leftOf(other: BoundingBox) = right < other.left
  def rightOff(other: BoundingBox) = left < other.right
  def leftDistanceTo(other: BoundingBox) = left - other.right
  def rightDistanceTo(other: BoundingBox) = other.leftDistanceTo(this)
  def topDistanceTo(other: BoundingBox) = top - other.bottom
  def bottomDistanceTo(other: BoundingBox) = other.topDistanceTo(this)

  def coordinatesToString = coordinates.toString()

  def toCSS(zoom: Double = 1) = "position:absolute; top:"+ (top * zoom).toInt + "px; left:" + (left * zoom).toInt +
                                  "px; height:" + (height * zoom).toInt + "px; width:" + (width * zoom).toInt + "px;"

}