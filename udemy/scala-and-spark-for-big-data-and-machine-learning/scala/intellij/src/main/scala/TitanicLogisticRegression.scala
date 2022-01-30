import org.apache.spark.sql.SparkSession
import org.apache.spark.ml.classification.LogisticRegression
import org.apache.spark.sql.functions.col
import org.apache.spark.ml.feature.{OneHotEncoder, StringIndexer, VectorAssembler, VectorIndexer}
import org.apache.spark.ml.linalg.Vectors
import org.apache.spark.ml.Pipeline
import org.apache.spark.mllib.evaluation.MulticlassMetrics
import org.apache.spark.sql.types.StringType

class TitanicLogisticRegression {

  // https://www.kaggle.com/c/titanic/data
  object cols {
    val label = "label"
    val passengerId = "PassengerId"
    val survived = "Survived" //0 = no, 1 = yes
    val passengerName = "Name"
    val passengerClass = "Pclass" //1 = first class, etc.
    val gender = "Sex"
    val genderIndex = "gender index"
    val genderVector = "gender index vector"
    val age = "Age"
    val siblingSpouse = "Sibsp"
    val parentsOrChildren = "Parch"
    val ticketNum = "Ticket"
    val fare = "Fare"
    val cabin = "Cabin"
    val portOfEmbarkation = "Embarked"
    val portOfEmbarkationIndex = "port of embarkation index"
    val portOfEmbarkationIndexVector = "port of embarkation index vector"
    val selectOrder = Seq(label, passengerClass, passengerName, gender, age,
      siblingSpouse, parentsOrChildren, fare, portOfEmbarkation)
  }

  def run(implicit spark: SparkSession): Unit = {
    import spark.implicits
    // import cols._
    val raw = spark.read
      .option("header", true)
      .option("inferschema", true)
      .csv("./src/main/resources/titanic.csv")

  //  raw.show(10, false)
  //  raw.printSchema

    val prepped = raw
      .withColumnRenamed(cols.survived, cols.label)
      .select(cols.selectOrder.map(col): _*)
      .na.drop()

//    prepped.show(10, false)
//
//    println("raw record count:" + raw.count())
//    println("prepped record count :" + prepped.count())

    //converting categorical strings into numerical values
    val genderIndexer = new StringIndexer()
      .setInputCol(cols.gender)
      .setOutputCol(cols.genderIndex)
    val embarkIndexer = new StringIndexer()
      .setInputCol(cols.portOfEmbarkation)
      .setOutputCol(cols.portOfEmbarkationIndex)

    //convert numerical values into one hot encodings. (zeros or ones)
    val genderEncoder = new OneHotEncoder()
      .setInputCol(cols.genderIndex)
      .setOutputCol(cols.genderVector)
    val embarkEncoder = new OneHotEncoder()
      .setInputCol(cols.portOfEmbarkationIndex)
      .setOutputCol(cols.portOfEmbarkationIndexVector)


    //put everything into label/features
    val assembler = new VectorAssembler()
      .setInputCols(Array(cols.passengerClass, cols.genderVector,
        cols.age, cols.siblingSpouse, cols.parentsOrChildren,
        cols.fare, cols.portOfEmbarkationIndexVector))
      .setOutputCol("features")

    //set up test and train, use a seed to make sure random is the same as the demo
    val Array(training, test) = prepped.randomSplit(Array(0.7, 0.3), seed = 12345)

    training.count()
    test.count()

    val logisticalRegression = new LogisticRegression()

    //Now we have to set up a pipeline to run all of it.

    val pipeline = new Pipeline()
      .setStages(Array(
        genderIndexer, embarkIndexer, genderEncoder, embarkEncoder, assembler, logisticalRegression
      ))
    val model = pipeline.fit(training)
    val results = model.transform(test)
    val resultsAsStrings = results.select(results.columns.map(c => col(c).cast(StringType)) : _*)
    GSheet.saveGoogleSheet("titanic results", resultsAsStrings)

    // Need to convert to RDD to get confusion matrix

    import spark.implicits._
    val predictionAndLabels = results.
      select($"prediction", $"label")
      .as[(Double, Double)]
      .rdd


    // Instantiate metrics object
    val metrics = new MulticlassMetrics(predictionAndLabels)

    // Confusion matrix
    println("Confusion matrix:")
    println(metrics.confusionMatrix)
    // model evaluation
    println("boo bear")

    //    results.show(10)
//    println("debug")
  }
}
