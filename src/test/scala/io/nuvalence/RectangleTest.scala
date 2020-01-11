package io.nuvalence

import org.scalatest.FunSuite

class RectangleTest extends FunSuite {

  //(0,2)---------+
  //|             |
  //|             |
  //(0,1)---+     |
  //|       |     |
  //|       |     |
  //|       |     |
  //+-------(1,0)-(2,0)
  test("Rectangle Containment") {
    val r1 = Rectangle(Point(0, 2), Point(2, 0))
    val r2 = Rectangle(Point(0, 1), Point(1, 0))
    assert(Rectangle.contains(r1, r2))
    assert(!Rectangle.contains(r2, r1))
  }

  //(0,2)--------+
  //|            |
  //|            |
  //|     (1,1)----------+
  //|     |      |       |
  //|     |      |       |
  //|     |      |       |
  //+-----+------(2,0)---(3,0)
  test("Rectangle Intersection") {
    val r1 = Rectangle(Point(0, 2), Point(2, 0))
    val r2 = Rectangle(Point(1, 1), Point(3, 0))
    assert(Rectangle.intersect(r1, r2) == Set(Point(2, 0), Point(1, 0), Point(2, 1)))
  }

  //(0,2)------(2,2)------+
  //|          |          |
  //|          |          |
  //|          |          |
  //|          |          |
  //|          |          |
  //+----------(2,0)------(4,0)
  test("Rectangle Adjacency Proper") {
    val r1 = Rectangle(Point(0, 2), Point(2, 0))
    val r2 = Rectangle(Point(2, 2), Point(4, 0))
    assert(Rectangle.adjacency(r1, r2) == AdjacencyType.Proper)
  }

  //(0,2)--------+
  //|            |
  //|            |
  //|            (2,1)---+
  //|            |       |
  //|            |       |
  //|            |       |
  //+------------(2,0)---(3,0)
  test("Rectangle Adjacency Subline") {
    val r1 = Rectangle(Point(0, 2), Point(2, 0))
    val r2 = Rectangle(Point(2, 1), Point(3, 0))
    assert(Rectangle.adjacency(r1, r2) == AdjacencyType.Subline)
  }

  //         (2,3)------+
  //           |        |
  //(0,2)      |        |
  //  +--------+        |
  //  |        |        |
  //  |        +--------+(4,1)
  //  |        |
  //  |        |
  //  +--------+
  //            (2,0)
  test("Rectangle Adjacency Partial") {
    val r1 = Rectangle(Point(0, 2), Point(2, 0))
    val r2 = Rectangle(Point(2, 3), Point(4, 1))
    assert(Rectangle.adjacency(r1, r2) == AdjacencyType.Partial)
  }

  test("Separated Rectangles") {
    val r1 = Rectangle(Point(0, 2), Point(2, 0))
    val r2 = Rectangle(Point(3, 3), Point(6, 0))
    assert(!Rectangle.contains(r1, r2))
    assert(!Rectangle.contains(r2, r1))
    assert(Rectangle.intersect(r1, r2) == Set())
    assert(Rectangle.adjacency(r1, r2) == AdjacencyType.None)
  }


}