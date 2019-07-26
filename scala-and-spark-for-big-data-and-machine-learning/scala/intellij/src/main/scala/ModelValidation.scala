import org.apache.spark.sql.SparkSession
import org.apache.spark.ml.evaluation.RegressionEvaluator
import org.apache.spark.ml.regression.LinearRegression
import org.apache.spark.ml.tuning.{ParamGridBuilder, TrainValidationSplit}
import org.apache.log4j._
import org.apache.spark.sql.types.DoubleType


class ModelValidation {

  object files {

    val housing = "./src/main/resources/USA-Housing.csv"
  }

  def run(implicit spark: SparkSession): Unit = {
    import spark.implicits._
    Logger.getLogger(("org")).setLevel(Level.ERROR)
    val data = spark.read
      .option("header", true)
      .option("inferschema", true)
      .csv(files.housing)

      .withColumnRenamed("Price", "label")
      .select("label", "Avg Area Income", "Avg Area House Age",
        "Avg Area Number of Rooms", "Avg Area Number of Bedrooms", "Area Population")
      .na.drop()
      .filter($"Avg Area house Age".geq(1))
      .withColumn("Avg Area Income", $"Avg Area Income".cast(DoubleType))
      .withColumn("Avg Area House Age", $"Avg Area House Age".cast(DoubleType))

    data.printSchema
    data.show(5, false)
    import org.apache.spark.ml.feature.VectorAssembler
    import org.apache.spark.ml.linalg.Vector


    val assembler = new VectorAssembler()
      .setInputCols(data.columns.filter(_ != "label"))
      .setOutputCol("features")
    val output = assembler.transform(data).select("label", "features")

    //training and test data
    val Array(training, test) = output
      .select("label", "features")
      .randomSplit((Array(.7, .3)))

    //buld the moddel
    val lr = new LinearRegression()

    //build the param grid
   val param = new ParamGridBuilder().addGrid(lr.regParam, Array(10000000, 0.00001)).build()

    //val train/split (hold out)
    val trainValidationSplit = new TrainValidationSplit()
      .setEstimator(lr)
      .setEvaluator(new RegressionEvaluator().setMetricName("r2")) //default is RMSE
      .setEstimatorParamMaps(param)
      .setTrainRatio(.8) // now this is the new model

    val model = trainValidationSplit.fit(training)
    model.transform(test)
      .select("label", "features", "prediction")
      .show(10, false)

    //RMSE
    //println(model.validationMetrics.foreach({println}))
    //102428.34152179054
    //102214.01110856915

    //r2
    //println(model.validationMetrics.foreach({println})) //R2 = .917, .918
    //because both of these models fit, they're almost exactly the sanem.
    //To adjust, go up to the params and increase/decrease the inputs
    //0.917826812403582
    //0.9183647903874959

    println(model.validationMetrics.foreach({println})) //R2#2
    //0.061504605111726174
    //0.9180561958926021
  }

}
