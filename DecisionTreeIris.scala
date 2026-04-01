import org.apache.spark.sql.SparkSession
import org.apache.spark.ml.feature.{StringIndexer, VectorAssembler}
import org.apache.spark.ml.classification.DecisionTreeClassifier
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
import org.apache.spark.ml.Pipeline

object DecisionTreeIris {
  def main(args: Array[String]): Unit = {

    val inputPath =
      if (args.nonEmpty) args(0) else "data/iris.csv"

    val spark = SparkSession.builder()
      .appName("DecisionTreeIris")
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

      val labelColumn = if (df.columns.contains("species")) {
        "species"
      } else {
        df.columns.last
      }

      val featureColumns = df.columns.filter(_ != labelColumn)

      val assembler = new VectorAssembler()
        .setInputCols(featureColumns)
        .setOutputCol("features")

      val labelIndexer = new StringIndexer()
        .setInputCol(labelColumn)
        .setOutputCol("label")

      val classifier = new DecisionTreeClassifier()
        .setLabelCol("label")
        .setFeaturesCol("features")
        .setMaxDepth(3)

      val pipeline = new Pipeline()
        .setStages(Array(assembler, labelIndexer, classifier))

      val Array(trainingData, testData) = df.randomSplit(Array(0.7, 0.3), seed = 42)

      val model = pipeline.fit(trainingData)
      val predictions = model.transform(testData)

      val evaluator = new MulticlassClassificationEvaluator()
        .setLabelCol("label")
        .setPredictionCol("prediction")
        .setMetricName("accuracy")

      val accuracy = evaluator.evaluate(predictions)
      val testError = 1.0 - accuracy

      println(s"Accuracy: $accuracy")
      println(s"Test Error: $testError")

      predictions.select("features", labelColumn, "prediction").show(false)

    } catch {
      case e: Exception =>
        println(s"Error running Decision Tree classification: ${e.getMessage}")
    } finally {
      spark.stop()
    }
  }
}