package com.an_it.ocr

/**
 * Smarchive GmbH
 * User: Andreas Neumann
 * Email: a.neumann@smarchive.de
 * Date: 25.09.12
 * Time: 10:35
 */
case class Paragraph(
    coordinates: Coordinates,
    lines: Lines,
    enclosingPageNumber : Option[Int] = None
  ) extends Element{

  def words = lines flatMap(_.words)

}
