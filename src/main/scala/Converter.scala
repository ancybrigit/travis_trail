

import org.apache.spark.sql.{DataFrame, Row, SparkSession}
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}
//import spark.implicits._

// converting uppercase to lower case
object Converter {

  def snakecaseify(s: String): String = {
    s.toLowerCase().replace(" ", "_")
  }

  def snakeCaseColumns(df: DataFrame): DataFrame = {
    df.columns.foldLeft(df) { (acc, cn) =>
      println(acc.getClass)
      println(cn)
      acc.withColumnRenamed(cn, snakecaseify(cn))

    }
  }
//  main function
  def main(args: Array[String]) {
    val spark: SparkSession = {
      SparkSession
        .builder()
        .master("local")
        .appName("spark test example")
        .getOrCreate()
    }
    val someData = Seq(
      Row("MIG"),
      Row("LUISA"),

    )
// create data frame
    val someSchema = List(
      StructField("NAME", StringType, true)
    )

    var sourceDF=spark.createDataFrame(
      spark.sparkContext.parallelize(someData),
      StructType(someSchema))
    def withGreeting()(df: DataFrame): DataFrame = {
      df.withColumn("greeting", lit("hello world"))
    }

    val NewDF = snakeCaseColumns(withGreeting()(sourceDF))
    NewDF.show()
  }
}
