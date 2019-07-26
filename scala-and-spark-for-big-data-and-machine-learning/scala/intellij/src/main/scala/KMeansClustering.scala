import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.sql.SparkSession

class KMeansClustering {

  object files {
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

    println("the larger the error, it wasn't the right fit of a cluster")

    def kmeans(i: Int): Double =  {
      val kmeans = new KMeans().setK(i).setSeed(1L)
      val model = kmeans.fit(training_data)
      model.computeCost(training_data)
    }

    import org.apache.spark.sql.functions._
    val kmeansUdf = udf(kmeans(_: Int))

    val df = (2 to 16).toDF("cluster_count")
      .withColumn("WSSE (Within Set Sum of Squared Errors)", kmeansUdf($"cluster_count"))

    df.show(18, false)

    //+-------------+---------------------------------------+
    //|cluster_count|WSSE (Within Set Sum of Squared Errors)|
    //+-------------+---------------------------------------+
    //|2            |1.1321752852090984E11                  |
    //|3            |8.095172370767671E10                   |
    //|4            |6.485574002870931E10                   |
    //|5            |5.893330181756374E10                   |
    //|6            |4.831143188586025E10                   |
    //|7            |4.570266077798425E10                   |
    //|8            |4.021516055339824E10                   |
    //|9            |3.442762468078519E10                   |
    //|10           |3.1063984718043636E10                  |
    //|11           |2.9722384588475933E10                  |
    //|12           |2.7747907825725933E10                  |
    //|13           |2.9453999441512062E10                  |
    //|14           |2.5602666002003128E10                  |
    //|15           |2.408897587106168E10                   |
    //|16           |2.2192390512907776E10                  |
    //+-------------+---------------------------------------+
  }
}
