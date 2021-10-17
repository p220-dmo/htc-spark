package fr.htc.spark.main;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import fr.htc.spark.config.SparkConfig;

public class RunIt {

	/**
	 * Question N° 2 : Charger le fichier des ventes (sales.csv) dans une RDD de
	 * type String
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		JavaSparkContext jsc = SparkConfig.getJavaSparkContext();
		JavaRDD<String> salesAsStringRDD = jsc.textFile("data/sales.csv");
		// Afficher 10 éléments du RDD
		salesAsStringRDD.take(10).stream().forEach(line -> System.out.println(line));

	}

}
