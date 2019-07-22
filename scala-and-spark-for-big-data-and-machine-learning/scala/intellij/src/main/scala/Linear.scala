import org.apache.spark.ml.regression.{LinearRegression, LinearRegressionModel, LinearRegressionTrainingSummary}
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.sql.functions._
import org.apache.spark.sql.{DataFrame, Dataset, Row}

class Linear {

  object files {
    val ecomm = "./src/main/resources/Clean-Ecommerce.csv"
    val housing = "./src/main/resources/Clean-USA-Housing.csv"
  }

  def run(implicit spark: SparkSession): Unit = {
    import spark.implicits
    object c {

      //    |-- Avg Area Income: double (nullable = true)
      //    |-- Avg Area House Age: double (nullable = true)
      //    |-- Avg Area Number of Rooms: double (nullable = true)
      //    |-- Avg Area Number of Bedrooms: double (nullable = true)
      //    |-- Area Population: double (nullable = true)
      //    |-- label: double (nullable = true)
      val label   = "label"
      val aai     = "Avg Area Income"
      val aaha    = "Avg Area House Age"
      val aanor   = "Avg Area Number of Rooms"
      val aanob   = "Avg Area Number of Bedrooms"
      val ap      = "Area Population"
      val order   = Seq(label, aai, aaha, aanor, aanob, ap)
      val features = "features"
    }
    //select(fieldOrder.map(col): _*).

    val housing: DataFrame = spark.read
        .option("header", true)
        .option("inferschema", true).csv(files.housing)
        .withColumnRenamed("Price", "label")
        .select(c.order.map(col):_*)

    val cols: Array[String] = housing.columns.tail
    println("here")

    val assembler = new VectorAssembler()
        .setInputCols(cols)
        .setOutputCol("features")

    val trainingData = assembler.transform(housing)
        .select(c.label, c.features)
//    transform.printSchema
//    transform.show(5, false)

    val lr = new LinearRegression()

//      .setMaxIter(10)
//      .setRegParam(0.3)
//      .setElasticNetParam(0.8)

    val lrModel: LinearRegressionModel = lr.fit(trainingData)


    // Print the coefficients and intercept for linear regression
    println(s"Coefficients: ${lrModel.coefficients} Intercept: ${lrModel.intercept}")

    // Summarize the model over the training set and print out some metrics
    val trainingSummary: LinearRegressionTrainingSummary = lrModel.summary

    println(s"numIterations: ${trainingSummary.totalIterations}")
    println(s"objectiveHistory: ${trainingSummary.objectiveHistory.toList}")
    trainingSummary.residuals.show()
    println(s"RMSE: ${trainingSummary.rootMeanSquaredError}")
    println(s"r2: ${trainingSummary.r2}")






  }
}
