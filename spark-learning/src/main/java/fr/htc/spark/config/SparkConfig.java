package fr.htc.spark.config;

import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SparkSession;

public class SparkConfig {

	/**
	 * 
	 * @return SparkSession
	 */
	public static SparkSession getSparkSession() {
		return SparkSession.builder()
				.appName("Sales KPI")
				.master("local[*]")
				.config("spark.sql.warehouse.dir", "warehouseLocation") // adding config parameters
				.getOrCreate();
	}

	/**
	 * 
	 * @return JavaSparkContext
	 */
	public static JavaSparkContext getJavaSparkContext() {
		return JavaSparkContext.fromSparkContext(getSparkSession().sparkContext());
	}

	/**
	 * 
	 * @return SparkContext
	 */
	public static SparkContext getSparkContext() {
		return getSparkSession().sparkContext();
	}

}
