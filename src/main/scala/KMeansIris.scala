import org.apache.spark.sql.SparkSession
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.ml.clustering.KMeans

object KMeansIris {
  def main(args: Array[String]): Unit = {

    val inputPath =
      if (args.nonEmpty) args(0) else "data/iris.csv"

    val spark = SparkSession.builder()
      .appName("KMeansIris")
      .master("local[*]")
      .getOrCreate()

    try {
      val rawDf = spark.read
        .option("header", "true")
        .option("inferSchema", "true")
        .csv(inputPath)

      val df = rawDf.columns.foldLeft(rawDf) { (tempDf, colName) =>
        tempDf.withColumnRenamed(colName, colName.replace(".", "_"))
      }

      val featureColumns = df.columns.filterNot(_.toLowerCase == "species")

      val assembler = new VectorAssembler()
        .setInputCols(featureColumns)
        .setOutputCol("features")

      val assembledDf = assembler.transform(df)

      val kmeans = new KMeans()
        .setK(3)
        .setSeed(42)
        .setFeaturesCol("features")
        .setPredictionCol("cluster")

      val model = kmeans.fit(assembledDf)
      val clustered = model.transform(assembledDf)

      println("Cluster Centers:")
      model.clusterCenters.zipWithIndex.foreach {
        case (center, idx) => println(s"Cluster $idx center: $center")
      }

      clustered.select(featureColumns.map(colName => org.apache.spark.sql.functions.col(colName)) :+ org.apache.spark.sql.functions.col("cluster"): _*)
        .show(false)

    } catch {
      case e: Exception =>
        println(s"Error running K-Means clustering: ${e.getMessage}")
    } finally {
      spark.stop()
    }
  }
}
