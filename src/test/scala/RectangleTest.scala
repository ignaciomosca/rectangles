import org.scalatest.FunSuite

class RectangleTest extends FunSuite {

    test("Rectangle Containment") {
      val r1 = Rectangle(Point(0,0),Point(2,0),Point(0,2),Point(2,2)) 
      val r2 = Rectangle(Point(0,0),Point(1,0),Point(0,1),Point(1,1)) 
      assert(Rectangle.contains(r1, r2))
    }

    test("Rectangle Intersection") {
      val r1 = Rectangle(Point(0,0),Point(2,0),Point(0,2),Point(2,2)) 
      val r2 = Rectangle(Point(1,0),Point(1,3),Point(0,1),Point(1,3)) 
      assert(Rectangle.intersect(r1, r2))
    }

    test("Rectangle Adjacency") {
        val r1 = Rectangle(Point(0,0),Point(2,0),Point(0,2),Point(2,2)) 
        val r2 = Rectangle(Point(2,0),Point(2,2),Point(4,2),Point(4,4)) 
        assert(Rectangle.adjacency(r1, r2))
    }
    
}