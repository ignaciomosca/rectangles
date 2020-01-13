package io.nuvalence

import io.nuvalence.AdjacencyType.AdjacencyType

/**
 *
 * @param x positive value of a point in an x-axis
 * @param y positive value of a point in an y-axis
 */
case class Point(x: Int, y: Int) {
  override def toString = s"($x,$y)"
}

object AdjacencyType extends Enumeration {
  type AdjacencyType = Value
  val Proper, Subline, Partial, None = Value
}

/**
 * @param upperLeft  upper left point of a rectangle
 * @param lowerRight lower right point of a rectangle
 *                   A rectangle gets represented as two points (upper left point and lower right)
 *                   which would display in the following fashion:
 * @upperLeft--------------------------------+
 * |                                         |
 * |                                         |
 * |                                         |
 * |                                         |
 * |                                         |
 * +-------------------------------@lowerRight
 *
 **/
case class Rectangle(upperLeft: Point, lowerRight: Point) {
  //Set of points of the lower side of a rectangle
  def lowerSide = (upperLeft.x to lowerRight.x).map(x => Point(x, lowerRight.y)).toSet

  //Set of points of the upper side of a rectangle
  def upperSide = (upperLeft.x to lowerRight.x).map(x => Point(x, upperLeft.y)).toSet

  //Set of points of the left side of a rectangle
  def leftSide = (lowerRight.y to upperLeft.y).map(y => Point(upperLeft.x, y)).toSet

  //Set of points of the right side of a rectangle
  def rightSide = (lowerRight.y to upperLeft.y).map(y => Point(lowerRight.x, y)).toSet

  def sides = List(upperSide, lowerSide, leftSide, rightSide)

  //returns all the points of a rectangle
  def points: Set[Point] = {
    (for {
      x <- upperLeft.x to lowerRight.x
      y <- lowerRight.y to upperLeft.y
    } yield {
      Point(x, y)
    }).toSet
  }
}

object Rectangle {

  /**
   * @returns true if Rectangle r2 is contained in Rectangle r1
   */
  def contains(r1: Rectangle, r2: Rectangle): Boolean = r2.points.subsetOf(r1.points)

  /**
   * @returns true if Rectangle r1 intersects with Rectangle r2
   *          note: if a rectangle contains another one we consider that a special case of intersection
   *
   */
  def intersect(r1: Rectangle, r2: Rectangle): Set[Point] = {
    (for {
      s1 <- r1.sides
      s2 <- r2.sides
    } yield s1.intersect(s2)).toSet.flatten
  }

  /**
   * @returns true if rectangle 1 and rectangle 2 are adjacent
   */
  def adjacency(r1: Rectangle, r2: Rectangle): AdjacencyType = {
    val result = for {
      s1 <- r1.sides
      s2 <- r2.sides
    } yield {
      if (s1 == s2) {
        AdjacencyType.Proper
      } else if (subline(s1, s2)) {
        AdjacencyType.Subline
      } else if (partial(s1, s2)) {
        AdjacencyType.Partial
      } else {
        AdjacencyType.None
      }
    }
    result.sortBy(_.id).filterNot(_ == AdjacencyType.None).headOption.getOrElse(AdjacencyType.None)
  }

  private def partial(s1: Set[Point], s2: Set[Point]): Boolean = {
    val intersection = s1.intersect(s2)
    if (intersection.nonEmpty) {
      intersection.size != s1.size && intersection.size != s2.size
    } else {
      false
    }
  }

  private def subline(sideA: Set[Point], sideB: Set[Point]): Boolean = (sideA.size < sideB.size && sideA.subsetOf(sideB)) || (sideB.size < sideA.size && sideB.subsetOf(sideA))

}
