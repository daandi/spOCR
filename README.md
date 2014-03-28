spOCR
=====

A Library to work with OCR files enriched with positional information

Supported file-formats
=====
AbbyyXML - XML-Format produced by AbbyyFineReader 
hOCR - Google-Format for encoding scanned Documents in HTML

Usage
====

General Usage
===
```scala
val doc: Doucment
// Iterators
document pages
document lines
document words
```

AbbyyXML
===

```scala
package com.an_it.ocr.format

import org.specs2.mutable.Specification
import xml.XML
import com.an_it.ocr.temporaryHTMLView
import org.specs2.specification.Scope


class AbbyyXMLSpec extends Specification {
  "AbbyyXML" should {

    val xml = XML.load( getClass.getResourceAsStream("/abbyy_xml_example.xml"))

    "load a document from a file" in {
      AbbyyXML.documentFromFile(getClass.getResource("/abbyy_xml_example.xml").getFile) mustEqual AbbyyXML.documentFromXML(xml)
    }

    "construct a Document from given XML" in {
      AbbyyXML.documentFromXML(xml).pages map (_.toText.size) mustEqual IndexedSeq(2215)
    }
    "construct a Page from given XML" in {
      AbbyyXML.pageFromXML( xml \\ "page" head).toText mustEqual """§a Entste h ung der M a L e r i e.
                                                                   |und Empfindung seiner Wirkungen als Eigen-
                                                                   |thum gegeben char.
                                                                   |Aus dieser allmächtigen Quelle siud auch
                                                                   |die Kräfte genommen worden^ die der Vater
                                                                   |aller Geister/ der Macht des Mm- Milletts
                                                                   |verliehen hatte, und welche m ZWheit des
                                                                   |Willens, durch die WiMschast des Bösen mis-
                                                                   |Krauchte;^ Sie haben der in sie wirkenden Ur-
                                                                   |kraft widerstanden, und find von ihr geschie-
                                                                   |Den worden.
                                                                   |~- ■' ii>' ' ■ •
                                                                   |. Wenn wir Wchdenken wollen , auf was
                                                                   |Art zwei elastische/Krafte einander, widerstehen,
                                                                   |so finden wir,. daß es durch eine Ausdehnung
                                                                   |der einen / und durch die Verengerung der an-
                                                                   |dern geschehen müsset zumal wenn leztere die
                                                                   |erste in sich faßt. Aus diesen beiden Vermögen
                                                                   |besteht Mtch wirklich die WaUcitat, . das Urwe-,
                                                                   |fin im Mwegung. — Die zmn Widerstände
                                                                   |MMauchte elaWsche Waft^verwandeltL sich also
                                                                   |in eine mächtige Ausdehnung ihrer selbst undl
                                                                   |mußte durch ein aus der göttlichen Kraft aus»
                                                                   |Segangenes zusammendrückendes Gesaz von allen
                                                                   |'Seiten umschlossen Mrden» ;„: -%
                                                                   |? Dieß gab aber nur eine einige Form die
                                                                   |erste und größte — einen Kreis; — den Grund-
                                                                   |tß aller andern; Denn jede Form, «nd was
                                                                   |0 : wir
                                                                   |/Entstehung der Materle.
                                                                   |wir in der körperlichen Welt wahrnehmen, ist
                                                                   |mit krummen Linien umschlossen; es kann also
                                                                   |aus diesem nrayfanglichen Cirkel weder die 33aitu
                                                                   |negfaltigkeiL der. Formen und die Menge ihrer
                                                                   |B.estandcheile, noch die Vermischung vot? Wv
                                                                   |drigkeiten, so wir überall antreffen, unmittel¬
                                                                   |bar hergeleitet werden. Folglich muß etwas vors
                                                                   |gegangen seyn, wodurch Theilung und Zusanv
                                                                   |mensezung der expansiven und eo^preßrben KraM
                                                                   |entstanden ist. Ich stelle mir die Sache unter
                                                                   |folgenden Möglichkeiten vor:
                                                                   |Das natürlichste Mittel, eine Kraft zu ver-
                                                                   |mindern, ist Um Zertheilung. mft> glaube ich,
                                                                   |daß. die bessernde Weisheit Gottes Zuerst die vow
                                                                   |ihm geschiedne Kraft des WwerßMdes zerstört
                                                                   |und in kleine Kreise eingeschlosserrhabe; dadurch
                                                                   |sind die Elemente der Materie entstanden, welche
                                                                   |in. bestimmter Größe und Anzahl, undurchdring^
                                                                   |lich und unauflösbar sind.
                                                                   |Da die Vollkommenheit Gottes sich nicht
                                                                   |unmittelbar mit dem Bösen abgeben konnte, so
                                                                   |hat er vermuthlich Werkzeuge gebraucht, welche
                                                                   |eine verettgernde Kraft habest MßM, die ans-
                                                                   |treibende zn bestrette^
                                                                   |mm""".stripMargin
    }


    "construct a Line from given XML" in {
      AbbyyXML.lineFromXML( xml \\ "line" head).toText mustEqual """§a Entste h ung der M a L e r i e."""
    }
    "get coordinates from XML Seq" in {
      AbbyyXML.extractCoordinatesFromXML(
        <line baseline="505" l="1155" t="427" r="2535" b="518"></line>) mustEqual ((1155,427),(2535,518))

    }

    "given an imagePath toHTML should create an HTML on an image " in new temporaryHTMLView with exampleDoc{
      val img = getClass.getResource("/abbyy_xml_example.png").getFile
      savePageToFile(page.copy(imgPath = Some(img)).toHTML ,"test_abbyy_new.html")
      pending
    }



  }

  trait exampleDoc extends Scope {
    val doc = AbbyyXML.documentFromXML(XML.load( getClass.getResourceAsStream("/abbyy_xml_example.xml")))
    val page = doc.pages head
  }

}

```

For hOCR
===

```scala
"A Document" should {
   val d = HOCR.fromFolder(getClass.getResource("/doc/hocr/").getFile)
``

https://www.codeship.io/projects/cea760a0-987b-0131-a0a9-6efb656965ef/status
