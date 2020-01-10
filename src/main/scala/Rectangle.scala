
case class Point(x: Int, y: Int)

/**
 *  A rectangle gets represented as two points (upper left point and lower right)
 *  which would display in the following fashion
 * @upperLeft--------------------------------+
 * |                                         |
 * |                                         |
 * |                                         |
 * |                                         |
 * |                                         |
 * +-------------------------------@lowerRight
 *
 * */

case class Rectangle(upperLeft: Point, lowerRight: Point) {
  def upperSide = upperLeft.x to lowerRight.x

  def lowerSide = upperLeft.x to lowerRight.x

  def leftSide = upperLeft.y to lowerRight.y

  def rightSide = upperLeft.y to lowerRight.y

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
  def contains(r1: Rectangle, r2: Rectangle): Boolean = r2.points.subsetOf(r1.points)

  def intersect(r1: Rectangle, r2: Rectangle): Boolean = {
    val r1Intersection = r1.points.intersect(r2.points)
    val r2Intersection = r2.points.intersect(r1.points)
    (r1Intersection.nonEmpty && r1Intersection.size < r1.points.size) || (r2Intersection.nonEmpty && r2Intersection.size < r2.points.size)
  }

  def adjacency(r1: Rectangle, r2: Rectangle): Boolean = {
    r1.upperSide.toSet.subsetOf(r2.lowerSide.toSet) || r2.upperSide.toSet.subsetOf(r1.lowerSide.toSet) ||
      r1.lowerSide.toSet.subsetOf(r2.upperSide.toSet) || r2.lowerSide.toSet.subsetOf(r1.upperSide.toSet) ||
      r1.leftSide.toSet.subsetOf(r2.rightSide.toSet) || r2.leftSide.toSet.subsetOf(r1.rightSide.toSet) ||
      r1.rightSide.toSet.subsetOf(r2.leftSide.toSet) || r2.rightSide.toSet.subsetOf(r1.leftSide.toSet)
  }
}
