package com.an_it.ocr.format

import org.specs2.mutable.Specification
import xml.XML


class AbbyyXMLSpec extends Specification {
  "AbbyyXML" should {

    val xml = XML.load( getClass.getResourceAsStream("/abbyy_xml_example.xml"))

    "construct a Document from given XML" in {
      AbbyyXML.documentFromXML(xml) mustEqual ""
    }
    "construct a Page from given XML" in {
      AbbyyXML.pageFromXML( xml \\ "page" head) mustEqual ""
    }
    "construct a Line from given XML" in {
      AbbyyXML.lineFromXML( xml \\ "line" head).toText mustEqual """§a        Entste h ung der M a L e r i e."""
    }
    "get coordinates from XML Seq" in {
      AbbyyXML.extractCoordinatesFromXML(
        <line baseline="505" l="1155" t="427" r="2535" b="518"></line>
      ) mustEqual ((1155,427),(2535,518))

    }

  }


}
