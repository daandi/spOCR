package com.an_it.ocr

/**
 * AN-iT
 * Andreas Neumann
 * andreas.neumann@an-it.com
 * http://www.an-it.com
 * Date: 10.11.11
 * Time: 20:46
 */

import org.specs2.mutable.Specification
import org.specs2.specification.Scope

class BoundingBoxSpec extends Specification{
  "Bounding Box coordinates" should {
    "have a top coordiante" in new testBoxes{
      box1.top.should_==(1)
    }
    "have a left coordiante" in new testBoxes{
      box1.left.should_==(2)
    }
    "have a right coordiante" in new testBoxes{
      box1.right.should_==(10)
    }
    "have a bottom coordiante" in new testBoxes{
      box1.bottom.should_==(5)
    }
    "have a width" in new testBoxes {
      bigBox.width.should_==( 100 )
    }
    "have a height" in new testBoxes {
      bigBox.height.should_==(50)
    }
    "has a toCoordinates method" in new testBoxes {
      box1.coordinates should_== ((2,1),(10,5))
    }
  }

  "BoundingBoxes in relations to each other" should  {
    "give leftDistanceTo" in new testBoxes {
      box2.leftDistanceTo(box1).should_==(2)
    }
    "give rightDistanceTo" in new testBoxes {
      box1.rightDistanceTo(box2).should_==(2)
    }
    "give topDistanceTo" in new testBoxes {
      box2.topDistanceTo(box1).should_==(3)
    }
    "give bottomDistanceTo" in new testBoxes {
      box1.bottomDistanceTo(box2).should_==(3)
    }

    "Test weather a box encloses another Box" in new testBoxes {
      bigBox encloses box1 should beTrue
    }
    "Test weather a box is encloed by another box" in new testBoxes {
      box2 enclosed_by bigBox should beTrue
    }
    "Enclosing is idempotent" in new testBoxes {
      bigBox enclosed_by bigBox should beTrue
    }
  }

  "BoundingBox output methods" should {
    "have a toString method displaying coordinates" in {
       new BoundingBox { val coordinates = ((2,1),(10,5)) }.coordinatesToString shouldEqual "((2,1),(10,5))"

    }
    "have a toCSS method with possibility to add a zoom factor for displaying boxes on scaled images" in {
      new BoundingBox { val coordinates = ((0,0),(100,50)) }.toCSS(1) shouldEqual "position:absolute; top:0px; left:0px; height:50px; width:100px;"
      new BoundingBox { val coordinates = ((0,0),(100,50)) }.toCSS(0.5) shouldEqual "position:absolute; top:0px; left:0px; height:25px; width:50px;"
    }
  }
}

trait testBoxes extends Scope{
  val box1 = new BoundingBox { val coordinates = ((2,1),(10,5)) }
  val box2 = new BoundingBox { val coordinates = ((12,8),(18,14)) }
  val bigBox = new BoundingBox { val coordinates = ((0,0),(100,50)) }

}