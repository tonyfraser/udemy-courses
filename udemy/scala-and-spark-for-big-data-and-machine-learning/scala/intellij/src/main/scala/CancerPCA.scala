import org.apache.log4j._
import org.apache.spark.sql.SparkSession
import org.apache.spark.ml.feature.PCA
import org.apache.spark.ml.linalg.Vectors

class CancerPCA {

  def run(implicit spark: SparkSession): Unit = {
    val housing = "./src/main/resources/USA-Housing.csv"
    import spark.implicits._
    Logger.getLogger(("org")).setLevel(Level.ERROR)
    println("running the cancer PCA (principle component analysis -- AKA most significant variables")
    val raw_data = spark.read.option("header", "true")
      .option("inferschema", "true")
      .csv("./src/main/resources/cancer_data.csv")
    raw_data.show(5, false)
    import org.apache.spark.ml.feature.{PCA,StandardScaler,VectorAssembler}
    import org.apache.spark.ml.linalg.Vectors

    val colnames: Array[String] = raw_data.columns.tail

    val assembler = new VectorAssembler().setInputCols(colnames).setOutputCol("features")
    val output = assembler.transform(raw_data).select($"features")
    val scaler = (new StandardScaler()
      .setInputCol("features")
      .setOutputCol("scaledFeatures")
      .setWithStd(true)
      .setWithMean(false))

    val scalerModel = scaler.fit(output)
    val scaledData = scalerModel.transform(output)
    val pca = (new PCA()
      .setInputCol("scaledFeatures")
      .setOutputCol("pcaFeatures")
      .setK(4)
      .fit(scaledData))
    val pcaDF = pca.transform(scaledData)
    val result = pcaDF.select("pcaFeatures")
    println("number of results:" + result.count)
    result.show(false)
    result.head(1)

    //+---+------------+------------+--------------+---------+---------------+----------------+--------------+-------------------+-------------+----------------------+------------+-------------+---------------+----------+----------------+-----------------+---------------+--------------------+--------------+-----------------------+------------+-------------+---------------+----------+----------------+-----------------+---------------+--------------------+--------------+-----------------------+
    //|id | mean radius|mean texture|mean perimeter|mean area|mean smoothness|mean compactness|mean concavity|mean concave points|mean symmetry|mean fractal dimension|radius error|texture error|perimeter error|area error|smoothness error|compactness error|concavity error|concave points error|symmetry error|fractal dimension error|worst radius|worst texture|worst perimeter|worst area|worst smoothness|worst compactness|worst concavity|worst concave points|worst symmetry|worst fractal dimension|
    //+---+------------+------------+--------------+---------+---------------+----------------+--------------+-------------------+-------------+----------------------+------------+-------------+---------------+----------+----------------+-----------------+---------------+--------------------+--------------+-----------------------+------------+-------------+---------------+----------+----------------+-----------------+---------------+--------------------+--------------+-----------------------+
    //|0  |17.99       |10.38       |122.8         |1001.0   |0.1184         |0.2776          |0.3001        |0.1471             |0.2419       |0.07871               |1.095       |0.9053       |8.589          |153.4     |0.006399        |0.04904          |0.05373        |0.01587             |0.03003       |0.006193               |25.38       |17.33        |184.6          |2019.0    |0.1622          |0.6656           |0.7119         |0.2654              |0.4601        |0.1189                 |
    //|1  |20.57       |17.77       |132.9         |1326.0   |0.08474        |0.07864         |0.0869        |0.07017            |0.1812       |0.05667               |0.5435      |0.7339       |3.398          |74.08     |0.005225        |0.01308          |0.0186         |0.0134              |0.01389       |0.003532               |24.99       |23.41        |158.8          |1956.0    |0.1238          |0.1866           |0.2416         |0.186               |0.275         |0.08902                |
    //|2  |19.69       |21.25       |130.0         |1203.0   |0.1096         |0.1599          |0.1974        |0.1279             |0.2069       |0.05999               |0.7456      |0.7869       |4.585          |94.03     |0.00615         |0.04006          |0.03832        |0.02058             |0.0225        |0.004571               |23.57       |25.53        |152.5          |1709.0    |0.1444          |0.4245           |0.4504         |0.243               |0.3613        |0.08758                |
    //|3  |11.42       |20.38       |77.58         |386.1    |0.1425         |0.2839          |0.2414        |0.1052             |0.2597       |0.09744               |0.4956      |1.156        |3.445          |27.23     |0.00911         |0.07458          |0.05661        |0.01867             |0.05963       |0.009208               |14.91       |26.5         |98.87          |567.7     |0.2098          |0.8663           |0.6869         |0.2575              |0.6638        |0.173                  |
    //|4  |20.29       |14.34       |135.1         |1297.0   |0.1003         |0.1328          |0.198         |0.1043             |0.1809       |0.05883               |0.7572      |0.7813       |5.438          |94.44     |0.01149         |0.02461          |0.05688        |0.01885             |0.01756       |0.005115               |22.54       |16.67        |152.2          |1575.0    |0.1374          |0.205            |0.4            |0.1625              |0.2364        |0.07678                |
    //+---+------------+------------+--------------+---------+---------------+----------------+--------------+-------------------+-------------+----------------------+------------+-------------+---------------+----------+----------------+-----------------+---------------+--------------------+--------------+-----------------------+------------+-------------+---------------+----------+----------------+-----------------+---------------+--------------------+--------------+-----------------------+

    //         - radius (mean of distances from center to points on the perimeter)
    //         - texture (standard deviation of gray-scale values)
    //         - perimeter
    //         - area
    //         - smoothness (local variation in radius lengths)
    //         - compactness (perimeter^2 / area - 1.0)
    //         - concavity (severity of concave portions of the contour)
    //         - concave points (number of concave portions of the contour)
    //         - symmetry
    //         - fractal dimension ("coastline approximation" - 1)
    //
    //         The mean, standard error, and "worst" or largest (mean of the three
    //         largest values) of these features were computed for each image,
    //         resulting in 30 features.  For instance, field 3 is Mean Radius, field
    //         13 is Radius SE, field 23 is Worst Radius.

  }

}
