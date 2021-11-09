package fr.htc.spark.main;

import java.util.Map;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.PairFunction;

import fr.htc.spark.beans.Sale;
import fr.htc.spark.config.SparkConfig;
import scala.Tuple2;

public class RunIt {

	/**
	 * Exercice 5: Calculer le nombre d'unités vendues par magasin
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		JavaSparkContext jsc = SparkConfig.getJavaSparkContext();
		JavaRDD<String> salesAsStringRDD = jsc.textFile("data/sales.csv");

		JavaRDD<Sale> salesAsObjects = salesAsStringRDD.map(csvLine -> Sale.parse(csvLine, ";"));

		//Mapper la RDD vers une Map de type Pair et faire un countByKey qui nous retourne une Map <storeId, unitSales>
		Map<Long, Long> numberUnitsByStore = salesAsObjects
				.mapToPair((PairFunction<Sale, Long, Double>) sale -> new Tuple2<Long, Double>(sale.getStoreId(), sale.getUnitSales()))
				.countByKey();
		
		numberUnitsByStore
			.forEach((storeId, unitSales) -> System.out.println("Magasin : " + storeId + " a un vendu : " + unitSales + " unités"));


	}

}
