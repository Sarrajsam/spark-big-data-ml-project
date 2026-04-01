import org.apache.spark.sql.SparkSession
import org.apache.spark.ml.fpm.FPGrowth

object FPGrowthApp {
  def main(args: Array[String]): Unit = {

    val inputPath =
      if (args.nonEmpty) args(0) else "data/Bakery.csv"

    val spark = SparkSession.builder()
      .appName("FPGrowth")
      .master("local[*]")
      .getOrCreate()

    try {
      val dataFrame = spark.read
        .option("header", "true")
        .csv(inputPath)

      val keyValuePairs = dataFrame.rdd.map(row => (row.getString(0), Set(row.getString(1))))
      val collectedKeyValuePairs = keyValuePairs.reduceByKey(_ ++ _)
      val itemsToList = collectedKeyValuePairs.map { case (id, items) => (id, items.toList) }

      import spark.implicits._
      val transactionsFrame = itemsToList.toDF("id", "items")

      val fpGrowth = new FPGrowth()
        .setItemsCol("items")
        .setMinSupport(0.05)
        .setMinConfidence(0.5)

      val model = fpGrowth.fit(transactionsFrame)

      println("Frequent Itemsets:")
      model.freqItemsets.show(false)

      println("Association Rules:")
      model.associationRules.show(false)

    } catch {
      case e: Exception =>
        println(s"Error running FP-Growth: ${e.getMessage}")
    } finally {
      spark.stop()
    }
  }
}