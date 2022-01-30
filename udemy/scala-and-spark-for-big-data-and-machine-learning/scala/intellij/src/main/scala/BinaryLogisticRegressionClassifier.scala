import org.apache.spark.ml.classification.{LogisticRegression, LogisticRegressionSummary, LogisticRegressionTrainingSummary}
import org.apache.spark.sql.{DataFrame, SparkSession}



class BinaryLogisticRegressionClassifier {
  object files {
    val libsvm = "./src/main/resources/sample_libsvm_data.txt"

  }

  def run(implicit spark: SparkSession): Unit = {
    import files._

    val training = spark.read.format("libsvm").load(files.libsvm)
    training.show(5, false)
    training.printSchema

    val lr = new LogisticRegression()  //< -- this is is a different regression, doesn't
                                       // have things like residuals and r^2
      .setMaxIter(10)
      .setRegParam(0.3)
      .setElasticNetParam(0.8)

    val lrModel = lr.fit(training)
    println(s"Coefficients: ${lrModel.coefficients} Intercept: ${lrModel.intercept}")
    println(s"Coefficients: ${lrModel.coefficients} Intercept: ${lrModel.intercept}")

    lrModel.summary

    // Summarize the model over the training set and print out some metrics
    val trainingSummary = lrModel.summary

    println(s"numIterations: ${trainingSummary.totalIterations}")
    println(s"objectiveHistory: ${trainingSummary.objectiveHistory.toList}")

    
  }

}
