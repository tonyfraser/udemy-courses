import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.sql.SparkSession

class KMeansClustering {

  object files {
    val kmeans = "./src/main/resources/sample_kmeans_data.txt"
    val wholesaleCustomer = "./src/main/resources/wholesale_customer_data.csv"
  }

  def runExercise(implicit spark: SparkSession): Unit = {
    import spark.implicits._
    import org.apache.spark.ml.clustering.KMeans
    val customer = spark.read.option("inferschema", "true")
      .option("header", "true")
      .csv(files.wholesaleCustomer)
      //    +-------+------+-----+----+-------+------+----------------+----------+
      //    |Channel|Region|Fresh|Milk|Grocery|Frozen|Detergents_Paper|Delicassen|
      //    +-------+------+-----+----+-------+------+----------------+----------+
      //    |2      |3     |12669|9656|7561   |214   |2674            |1338      |
      //    |2      |3     |7057 |9810|9568   |1762  |3293            |1776      |
      //    |2      |3     |6353 |8808|7684   |2405  |3516            |7844      |
      //    |1      |3     |13265|1196|4221   |6404  |507             |1788      |
      //    |2      |3     |22615|5410|7198   |3915  |1777            |5185      |
      //    +-------+------+-----+----+-------+------+----------------+----------+
      // 1)	FRESH: annual spending (m.u.) on fresh products (Continuous);
      // 2)	MILK: annual spending (m.u.) on milk products (Continuous);
      // 3)	GROCERY: annual spending (m.u.)on grocery products (Continuous);
      // 4)	FROZEN: annual spending (m.u.)on frozen products (Continuous)
      // 5)	DETERGENTS_PAPER: annual spending (m.u.) on detergents and paper products (Continuous)
      // 6)	DELICATESSEN: annual spending (m.u.)on and delicatessen products (Continuous);
      // 7)	CHANNEL: customers Channel - Horeca (Hotel/Restaurant/Cafe) or Retail channel (Nominal)
      // 8)	REGION: customers Region- Lisnon, Oporto or Other (Nominal)
      .drop("Channel", "Region")
      .na.drop

    val assembler = new VectorAssembler()
      .setInputCols(customer.columns)
      .setOutputCol("features")

    val training_data = assembler.transform(customer)

    val kmeans = new KMeans().setK(3).setSeed(1L)
      //2 = 1.1321752852090984E11
      //3 = 8.095172370767671E10
    val model = kmeans.fit(training_data)

    val WSSSE = model.computeCost(training_data)
    println(s"Within Set Sum of Squared Errors = $WSSSE")

    // Shows the result.
    println("Cluster Centers: ")
    model.clusterCenters.foreach(println)
  }

  def run(implicit spark: SparkSession): Unit = {
    import spark.implicits
    import org.apache.spark.ml.clustering.KMeans

    // Loads data.
    val dataset = spark.read
      .option("header", "true")
      .option("inferSchema", "true")
      .format("libsvm")
      .load(files.kmeans)

    // Trains a k-means model.
    val kmeans = new KMeans().setK(2).setSeed(1L)
    val model = kmeans.fit(dataset)

    // Evaluate clustering by computing Within Set Sum of Squared Errors.
    val WSSSE = model.computeCost(dataset)
    println(s"Within Set Sum of Squared Errors = $WSSSE")

    // Shows the result.
    println("Cluster Centers: ")
    model.clusterCenters.foreach(println)
  }

}
