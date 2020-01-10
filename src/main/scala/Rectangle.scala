case class Point(x: Int, y: Int)
case class Rectangle(upperLeft: Point, upperRight: Point, lowerLeft: Point, lowerRight: Point) {
    def points: Set[Point] = {
        val upperHalf = (for {
            upperSide <- upperLeft.x  to upperRight.x
            leftSide <- upperLeft.y to lowerLeft.y
        } yield {
            Point(upperSide, leftSide)
        }).toSet
        val lowerHalf = (for {
            lowerSide <- lowerLeft.x to lowerRight.x
            rightSide <- upperRight.y to lowerRight.y
        } yield {
            Point(lowerSide, rightSide)
        }).toSet
        upperHalf.union(lowerHalf)
    }
}

object Rectangle {
    def contains(r1: Rectangle, r2: Rectangle):Boolean = {
        r1.points.contains(r2.points) || r2.points.contains(r1.points)
    }
    def intersect(r1: Rectangle, r2: Rectangle):Boolean = {
        r1.points.intersect(r2.points).size < r1.points.size || r2.points.intersect(r1.points).size < r2.points.size 
    }
    def adjacency(r1: Rectangle, r2: Rectangle):Boolean = {
        false
    }
}
