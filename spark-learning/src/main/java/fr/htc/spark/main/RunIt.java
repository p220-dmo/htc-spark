package fr.htc.spark.main;

import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;

import fr.htc.spark.beans.Sale;
import fr.htc.spark.config.SparkConfig;
import scala.Tuple2;

public class RunIt {

	/**
	 * Exercice 4: Calculer le chiffre d'affaire par magasin
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		JavaSparkContext jsc = SparkConfig.getJavaSparkContext();
		JavaRDD<String> salesAsStringRDD = jsc.textFile("data/sales.csv");

		JavaRDD<Sale> salesAsObjects = salesAsStringRDD.map(csvLine -> Sale.parse(csvLine, ";"));

		// On peut le faire avec reduceByKey ou groupByKey. Cependant, il faut
		// privil�gier reduceByKey, car un calcul interm�diaire se fait au niveau de
		// chaque partition avant d'envoyer les r�sultats sur le r�seau. Ce qui fait
		// qu'on n'envoie qu'un tuple par cl� et par partition. Cons�quence, moins de
		// trafic r�seau !!!
		
		JavaPairRDD<Long, Double> storeCA = salesAsObjects.mapToPair(
				(PairFunction<Sale, Long, Double>) sale -> new Tuple2<Long, Double>(sale.getStoreId(), sale.getCa()))
				.reduceByKey((Function2<Double, Double, Double>) (ca1, ca2) -> ca1 + ca2);

		storeCA.collectAsMap().forEach((storeId, caByStore) -> System.out
				.println("Magasin : " + storeId + " a un chiffre d'affaires : " + caByStore));

	}

}
