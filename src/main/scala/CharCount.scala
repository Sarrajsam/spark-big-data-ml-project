import scala.io.Source

object CharCount {
  def main(args: Array[String]): Unit = {

    // Use input file from argument or default file
    val filePath = if (args.nonEmpty) args(0) else "data/input.txt"

    try {
      val source = Source.fromFile(filePath)

      // Map to store character counts
      var charCountMap: Map[Char, Int] = Map()

      for (line <- source.getLines()) {
        for (char <- line.toLowerCase if !char.isWhitespace) {
          charCountMap = charCountMap.updated(
            char,
            charCountMap.getOrElse(char, 0) + 1
          )
        }
      }

      source.close()

      println("Character Counts:")
      charCountMap.toSeq.sortBy(_._1).foreach {
        case (char, count) => println(s"$char -> $count")
      }

    } catch {
      case e: Exception =>
        println(s"Error reading file: ${e.getMessage}")
    }
  }
}
