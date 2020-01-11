package io.nuvalence

/**
 *
 * @param x positive value of a point in an x-axis
 * @param y positive value of a point in an y-axis
 */
case class Point(x: Int, y: Int)

/**
 * @param upperLeft  upper left point of a rectangle
 * @param lowerRight lower right point of a rectangle
 * A rectangle gets represented as two points (upper left point and lower right)
 *  which would display in the following fashion:
 *
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
  //Set of points of the upper side of a rectangle
  def upperSide = (upperLeft.x to lowerRight.x).toSet

  //Set of points of the lower side of a rectangle
  def lowerSide = (upperLeft.x to lowerRight.x).toSet

  //Set of points of the left side of a rectangle
  def leftSide = (upperLeft.y to lowerRight.y).toSet

  //Set of points of the right side of a rectangle
  def rightSide = (upperLeft.y to lowerRight.y).toSet

  //returns all the points of a rectangle
  def points: Set[Point] = {
    (for {
      x <- upperLeft.x to lowerRight.x
      y <- upperLeft.y to lowerRight.y
    } yield {
      Point(x, y)
    }).toSet
  }
}

object Rectangle {

  /**
   * @returns true if io.nuvalence.Rectangle r2 is contained in io.nuvalence.Rectangle r1
   */
  def contains(r1: Rectangle, r2: Rectangle): Boolean = r2.points.subsetOf(r1.points)

  /**
   * @returns true if io.nuvalence.Rectangle r2 is contained in io.nuvalence.Rectangle r1
   *          note: if a rectangle contains another one we consider that a special case of intersection
   *
   */
  def intersect(r1: Rectangle, r2: Rectangle): Boolean = {
    val r1Intersection = r1.points.intersect(r2.points)
    val r2Intersection = r2.points.intersect(r1.points)
    r1Intersection.nonEmpty || r2Intersection.nonEmpty
  }

  /**
   * @returns true if rectangle 1 and rectangle 2 are adjacent
   */
  def adjacency(r1: Rectangle, r2: Rectangle): Boolean = {
    r1.upperSide.subsetOf(r2.lowerSide) || r2.upperSide.subsetOf(r1.lowerSide) ||
      r1.lowerSide.subsetOf(r2.upperSide) || r2.lowerSide.subsetOf(r1.upperSide) ||
      r1.leftSide.subsetOf(r2.rightSide) || r2.leftSide.subsetOf(r1.rightSide) ||
      r1.rightSide.subsetOf(r2.leftSide) || r2.rightSide.subsetOf(r1.leftSide)
  }
}
