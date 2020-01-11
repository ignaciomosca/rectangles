package io.nuvalence

import java.io.IOException

import zio.console.{Console, getStrLn, putStrLn}
import zio.{App, IO, ZIO}

object Main extends App {

  private def inputNumber(number: String) = ZIO
    .effect(number.toInt)
    .filterOrFail(_ >= 0)(new IOException("Invalid input"))

  private def inputOption(number: String): ZIO[Any, NumberFormatException, Int] = ZIO
    .effect(number.toInt)
    .refineToOrDie[NumberFormatException]

  private def getInput(message: String): ZIO[Console, IOException, Int] = (for {
    _ <- putStrLn(message)
    input <- getStrLn.orDie
    number <- inputNumber(input)
  } yield number) orElse (putStrLn("Invalid Input") *> getInput(message))

  private def inputMenuOption(message: String): ZIO[Console, IOException, Int] = (for {
    _ <- putStrLn(message)
    input <- getStrLn.orDie
    number <- inputOption(input)
  } yield number) orElse (putStrLn("Invalid Option") *> getInput(message))

  private def displayMenu(r1: Rectangle, r2: Rectangle) = (for {
    _ <- putStrLn("---Menu---")
    _ <- putStrLn("1 - Rectangle 1 Contains Rectangle 2")
    _ <- putStrLn("2 - Rectangle 2 Contains Rectangle 1")
    _ <- putStrLn("3 - Rectangle 1 and Rectangle 2 Intersect")
    _ <- putStrLn("4 - Rectangles 1 and 2 are Adjacent")
    option <- inputMenuOption("Press any other number to Exit")
    _ <- processOption(option, r1, r2)
  } yield ())

  private def makeRectangle(): ZIO[Console, IOException, Rectangle] = (for {
    upperLeftX <- getInput("Define the x component for the upper left side of your rectangle")
    upperLeftY <- getInput("Define the y component for the upper left side of your rectangle")
    lowerRightX <- getInput("Define the x component for the lower right side of your rectangle")
    lowerRightY <- getInput("Define the y component for the lower right side of your rectangle")
  } yield Rectangle(Point(upperLeftX, upperLeftY), Point(lowerRightX, lowerRightY)))

  def show(answer: Boolean) = if (answer) "Yes" else "No"

  def processOption(option: Int, r1: Rectangle, r2: Rectangle): ZIO[Console, IOException, Unit] = {
    option match {
      case 1 => putStrLn(show(Rectangle.contains(r1, r2))) *> displayMenu(r1, r2)
      case 2 => putStrLn(show(Rectangle.contains(r2, r1))) *> displayMenu(r1, r2)
      case 3 => putStrLn(Rectangle.intersect(r1, r2).map(_.toString).toString()) *> displayMenu(r1, r2)
      case 4 => putStrLn(Rectangle.adjacency(r1, r2).toString) *> displayMenu(r1, r2)
      case _ => putStrLn("BYE!")
    }
  }

  def run(args: List[String]) = (for {
    _ <- putStrLn("---Rectangles---")
    _ <- putStrLn("Make Rectangle #1")
    r1 <- makeRectangle()
    _ <- putStrLn("Make Rectangle #2")
    r2 <- makeRectangle()
    _ <- displayMenu(r1, r2)
  } yield ()).run *> IO.succeed(0)
}
