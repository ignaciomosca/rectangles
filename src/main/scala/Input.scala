import scala.util.{Failure, Success, Try}

object Input {

  /** *
   *
   * @param function a function to be executed
   * @return validates an input
   */
  def getInput[a](function: => Try[a]): a =
    Iterator.continually(function).flatMap {
      case Success(t) => Some(t)
      case Failure(f) => println("Invalid Value. Please try again"); None
    }.toSeq.head

}
