
name := "intellij"
version := "0.1"
scalaVersion := "2.11.0" //<- needs to be 2.11 to use google sheets

libraryDependencies ++= Seq(
  "org.apache.spark"  %% "spark-sql"                 % "2.4.3",
  "org.apache.spark"  %% "spark-mllib"               % "2.4.3",
  "com.github.potix2" %% "spark-google-spreadsheets" % "0.6.2"
)