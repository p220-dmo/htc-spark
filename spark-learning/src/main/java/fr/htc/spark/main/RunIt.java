package fr.htc.spark.main;

import java.util.Map;

import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.broadcast.Broadcast;

import fr.htc.spark.beans.Sale;
import fr.htc.spark.beans.Store;
import fr.htc.spark.config.SparkConfig;
import scala.Tuple2;
import scala.reflect.ClassTag$;

public class RunIt {

	/**
	 * Exercice 6: Calculer le chiffre d'affaire par région.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		JavaSparkContext jsc = SparkConfig.getJavaSparkContext();
		JavaRDD<String> salesAsStringRDD = jsc.textFile("data/sales.csv");
		JavaRDD<Sale> salesAsObjects = salesAsStringRDD.map(csvLine -> Sale.parse(csvLine, ";"));

		// Lecture du fichier store à broadcaster (fichier très petit)
		Map<Integer, Integer> storeRegionMapRdd = SparkConfig.getJavaSparkContext().textFile("data/store.csv")
				.mapToPair((PairFunction<String, Integer, Integer>) s -> {
					Store store = Store.parse(s);
					return new Tuple2<>(store.getId(), store.getRegionId());
				}).collectAsMap();
		Broadcast<Map<Integer, Integer>> storeRegionMap = SparkConfig.getSparkContext().broadcast(storeRegionMapRdd,
				ClassTag$.MODULE$.apply(Map.class));

		// Faire un Map-side Join
		JavaPairRDD<Integer, Double> caByRegion = salesAsObjects
				.mapToPair(sale -> new Tuple2<>(storeRegionMap.value().getOrDefault(sale.getStoreId(), -1),
						sale.getUnitSales() * sale.getStoreSales()))
				.reduceByKey((Function2<Double, Double, Double>) (a, b) -> a + b);

		caByRegion.collectAsMap().forEach((k, v) -> System.out.println("Region : " + k + " avec un CA : " + v));

	}

}
