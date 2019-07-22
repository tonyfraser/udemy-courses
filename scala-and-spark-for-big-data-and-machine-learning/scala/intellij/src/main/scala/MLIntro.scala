import org.apache.spark.sql.{Dataset, Row, SparkSession}
import org.apache.spark.ml.regression.{LinearRegression, LinearRegressionModel}
import org.apache.log4j._

object MLIntro {

  def main(args: Array[String]): Unit = {
    Logger.getLogger("org").setLevel(Level.ERROR)
    Logger.getLogger("com").setLevel(Level.ERROR)
    def sparkSession: SparkSession = {
      SparkSession
        .builder()
        .appName(s"co-util-stand-alone-application")
        .master("local[4]")
        .getOrCreate()
    }

    implicit val spark = sparkSession


   // new BinaryLogisticRegressionClassifier().run
   // new Linear().run
   // new DocExample().run
   new TitanicLogisticRegression().run





  }


}