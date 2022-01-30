import org.apache.spark.sql.{DataFrame, Dataset, Row, SparkSession}
import java.io.File

import com.github.potix2.spark.google.spreadsheets.SparkSpreadsheetService.SparkSpreadsheetContext
import org.apache.parquet.format.StringType

object GSheet {
  object GConfig {
    val account = "tf-udemy-projects@tonyfraser123456.iam.gserviceaccount.com"
    val keypath = "./src/main/resources/tonyfraser123456-18b05aa15342.p12"
    val defaultDocId = "1jyI5kEBvHhm4ALKi4h5MpwMYYmLxjb9uLQ160YpwccM"
  }

  def readGoogleSheet(tab: String, docId: String = GConfig.defaultDocId, inferSchema: Boolean = true)(implicit spark: SparkSession): Dataset[Row] = {
    import GConfig._

    spark.read.
      option("inferschema", true).
      format("com.github.potix2.spark.google.spreadsheets").
      option("serviceAccountId", account).
      option("credentialPath", keypath).
      load(s"${docId}/${tab}")
  }

  def saveGoogleSheet(tab: String, df: Dataset[Row], docId: String = GConfig.defaultDocId): Unit = {
    import GConfig._
   // deleteGoogleSheetTabTab(docId, tab)
    url(docId)

    df
      .write
      .format("com.github.potix2.spark.google.spreadsheets")
      .option("serviceAccountId", account)
      .option("credentialPath", keypath)
      .save(s"${docId}/${tab}")

  }

  def deleteGoogleSheetTabTab(tab: String, docId: String = GConfig.defaultDocId): Unit = {
    import GConfig._

      SparkSpreadsheetContext(account, new File(keypath)).
      findSpreadsheet(docId).
      deleteWorksheet(tab)
  }
  def url(docId: String = GConfig.defaultDocId): Unit = {
    println(s"Google Sheet URL: https://docs.google.com/spreadsheets/d/${docId}/edit")

  }

}
