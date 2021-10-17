package fr.htc.spark.main;

import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SparkSession;

import fr.htc.spark.config.SparkConfig;

public class RunIt {

	public static void main(String[] args) {

		SparkSession sparkSession = SparkConfig.getSparkSession();
		System.out.println(sparkSession.toString());

		SparkContext sparkContext = SparkConfig.getSparkContext();
		System.out.println(sparkContext);

		JavaSparkContext javaSparkContext = SparkConfig.getJavaSparkContext();
		System.out.println(javaSparkContext);

	}

}
