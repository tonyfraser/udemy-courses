import org.apache.spark.sql.{Dataset, Row, SparkSession}
import org.apache.spark.ml.regression.{LinearRegression, LinearRegressionModel}
import org.apache.log4j._

object MLIntro {

  def main(args: Array[String]): Unit = {
    Logger.getLogger("org").setLevel(Level.ERROR)
  //  Logger.getLogger("com").setLevel(Level.ERROR)

    def sparkSession: SparkSession = {
      SparkSession
        .builder()
        .appName(s"co-util-stand-alone-application")
        .master("local[4]")
        .getOrCreate()
    }

    implicit val spark = sparkSession
    import spark.implicits._
//    val d1 = Seq((1, "one"), (2, "two")).toDF("id", "phrase_1")
//    val d2 = Seq("uno","dos").toDF("phrase_2")
//    val d1r = d1.rdd
//    val d2r = d2.rdd
//    val nr = d1
//
//    val d1a = d1.collect()
//    val d2b = d2.collect()
//
//    d1a.map(k, v =>

    )




    // new BinaryLogisticRegressionClassifier().run
    // new Linear().run
    // new DocExample().run
    // new TitanicLogisticRegression().run
    // new ModelValidation().run
    //new KMeansClustering().run
    // new KMeansClustering().testCluster
    // new CancerPCA().run

  }


}