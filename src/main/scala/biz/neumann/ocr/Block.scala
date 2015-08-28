package biz.neumann.ocr

/**
 * Smarchive GmbH
 * User: Andreas Neumann
 * Email: a.neumann@smarchive.de
 * Date: 25.09.12
 * Time: 10:35
 */
case class Block(
  coordinates: Coordinates,
  lines: Lines,
  enclosingPageNumber : Option[Int] = None
 ) extends Element{
  def words : Words = lines flatMap (_.words)

}
