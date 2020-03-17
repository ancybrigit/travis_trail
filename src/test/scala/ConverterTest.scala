import org.apache.spark.sql.{DataFrame, Row, SparkSession}
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}
import org.scalatest.FunSuite
//import spark.implicits._
class ConverterTest  extends FunSuite  {



  test(".snakecaseify: downcases uppercase letters") {


    assert(Converter.snakecaseify("HeLlO") === "hello")
  }

    test("converts spaces to underscores") {
      assert(Converter.snakecaseify("Hi There") === "hi_there")


  }

  test(".snakeCaseColumns:snake_cases the column names of a DataFrame") {

    val spark: SparkSession = {
      SparkSession
        .builder()
        .master("local")
        .appName("spark test example")
        .getOrCreate()
    }

      val someData = Seq(
       Row("funny"),
       Row("joke")
      )
    val someSchema= List(
      StructField("a_b_c", StringType, true)

    )

      val sourceDF=spark.createDataFrame(
        spark.sparkContext.parallelize(someData),
        StructType(someSchema))
     val expectedDF=sourceDF
      val actualDF = Converter.snakeCaseColumns(sourceDF)



      assert(expectedDF ===expectedDF)

    }
}
