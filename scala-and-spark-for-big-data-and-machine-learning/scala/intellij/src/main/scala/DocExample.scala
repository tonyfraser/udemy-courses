
import org.apache.spark.ml.regression.{LinearRegression, LinearRegressionModel}
import org.apache.spark.sql.SparkSession

// new DocExample().run

class DocExample {

  def run(implicit spark: SparkSession): Unit = {
    val sampLRTab = "sample linear regression"
    val lgSamp = "./src/main/resources/sample_linear_regression_data.txt"
    val lgSampDf = spark.read.format("libsvm").load(lgSamp)
    lgSampDf.printSchema
    lgSampDf.show(2, false)

    val lr = new LinearRegression()
      .setMaxIter(10)
      .setRegParam(0.3)
      .setElasticNetParam(0.8)

    // Fit the model
    val lrModel: LinearRegressionModel = lr.fit(lgSampDf)
    println(s"Coefficients: ${lrModel.coefficients} Intercept: ${lrModel.intercept}")
    println("debug me")

    println("lg Sample Size: " + lgSampDf.count)

    // Print the coefficients and intercept for linear regression
    println(s"Coefficients: ${lrModel.coefficients} Intercept: ${lrModel.intercept}")

    // Summarize the model over the training set and print out some metrics
    val trainingSummary = lrModel.summary
    println(s"numIterations: ${trainingSummary.totalIterations}")
    println(s"objectiveHistory: ${trainingSummary.objectiveHistory.toList}")
    trainingSummary.residuals.show()
    println(s"RMSE: ${trainingSummary.rootMeanSquaredError}")
    println(s"r2: ${trainingSummary.r2}")
  }

}
