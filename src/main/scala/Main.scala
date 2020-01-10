import scala.io.StdIn

object Main extends App {

  def makeRectangle() = {

    println("Define the x component for the upper left side of your triangle")
    val upperLeftX = StdIn.readInt()

    println("Define the y component for the upper left side of your triangle")
    val upperLeftY = StdIn.readInt()

    println("Define the x component for the lower right side of your triangle")
    val lowerRightX = StdIn.readInt()

    println("Define the y component for the lower right side of your triangle")
    val lowerRightY = StdIn.readInt()

    Rectangle(Point(upperLeftX, upperLeftY), Point(lowerRightX, lowerRightY))

  }

  def displayMenu() = {
    println("---Menu---")
    println("1 - Rectangle 1 Contains Rectangle 2")
    println("2 - Rectangle 2 Contains Rectangle 1")
    println("3 - Rectangle 1 and Rectangle 2 Intersect")
    println("4 - Rectangles 1 and 2 are Adjacent")
    println("Press any other key to Exit")
  }

  def show(answer: Boolean) = if (answer) "YES" else "NO"

  println("---Rectangles---")

  println("Make Rectangle 1")
  val r1 = makeRectangle()
  println("Make Rectangle 2")
  val r2 = makeRectangle()

  displayMenu()

  var option = StdIn.readInt()

  while (List(1,2,3,4).contains(option)) {
    option match {
      case 1 => println(show(Rectangle.contains(r1, r2)))
      case 2 => println(show(Rectangle.contains(r2, r1)))
      case 3 => println(show(Rectangle.intersect(r1, r2)))
      case 4 => println(show(Rectangle.adjacency(r1, r2)))
      case _ => println("BYE!")
    }
    displayMenu()
    option = StdIn.readInt()
  }


}