package com.an_it.ocr.converters

import com.an_it.ocr.{Word, Page, Document}
import xml.{Node, XML, NodeSeq}

/**
 * AN-iT
 * Andreas Neumann
 * andreas.neumann@an-it.com
 * http://www.an-it.com
 * Date: 24.04.12
 * Time: 10:41
 */

class DocXML(doc: Document) {

  def toXML : Node = <document>{doc.pages map(pageToXML) seq}</document>


  def pageToXML(p: Page) : Node =
  <page imageFile={p.imagePath.getOrElse("")} sourceFile="">
    {(p.words zipWithIndex).map {case (word,idx) => toTokenXML(word,idx,p)} }
  </page>

  def toTokenXML(w: Word, idx: Int, p: Page ) : Node =
    <token token_id={idx toString} isNormal="false">
 		<wOCR>{w.text}</wOCR>
 		<wOCR_lc>{w.text toLowerCase}</wOCR_lc>
 		<wCorr></wCorr>
    {abbyCoordsXML(coordsToAbbyCoords(w.coordinates, p))}
 		<abbyy_suspicious value="false"/>
 		<groundtruth verified="false">
  	   		<classified>unknown</classified>
  	  		<wOrig></wOrig>
  	   		<wOrig_lc></wOrig_lc>
  	   		<baseWord></baseWord>
  	   		<histTrace></histTrace>
  	   		<ocrTrace></ocrTrace>
 		</groundtruth>
 	</token>

  def coordsToAbbyCoords(hOCRCoords: ((Int,Int),(Int,Int)), p: Page) = {
    val ((leftDistance,topDistance),(hOCRRight,hOCRbottom)) = hOCRCoords
    val ((_,_),(pageRight, pageBottom)) = p.coordinates

    ((leftDistance,topDistance),(pageRight - hOCRRight, pageBottom - hOCRbottom))
  }

  def abbyCoordsXML(coords: ((Int,Int),(Int,Int))) : Node =
      <coord l={coords._1._1 toString} t={coords._1._2 toString} r={coords._2._1 toString} b={coords._2._2 toString}/>

  def toFile(path: String) {
    XML.save(path,toXML,"UTF-8")
  }

}
