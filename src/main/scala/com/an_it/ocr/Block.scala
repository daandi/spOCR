package com.an_it.ocr

/**
 * Smarchive GmbH
 * User: Andreas Neumann
 * Email: a.neumann@smarchive.de
 * Date: 25.09.12
 * Time: 10:35
 */
case class Block(
  coordinates: Coordinates,
  paragraphs: Paragraphs,
  enclosingPageNumber : Option[Int] = None
 ) extends Element{

  def lines : Lines = paragraphs flatMap (_.lines)
  def words : Words = lines flatMap(_.words)

}
