package fr.htc.spark.main;

import java.util.Map;

import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
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

		// Lecture du fichier store à broadcaster er reconversion en Map <StoreId, RegionId> (fichier très petit)
		Map<Integer, Integer> storeRegionMap = SparkConfig.getJavaSparkContext().textFile("data/store.csv")
				.map(storeAsCsv -> Store.parse(storeAsCsv))
				.mapToPair(store -> new Tuple2<>(store.getId(), store.getRegionId())).collectAsMap();

		// Version Eclatée : Lecture du fichier store à broadcaster er reconversion en Map <StoreId, RegionId> (fichier très petit)
//		Map<Integer, Integer> storeRegionMapRdd = null;
//		JavaRDD<String> storeStringRDD = SparkConfig.getJavaSparkContext().textFile("data/store.csv");
//		JavaRDD<Store> storeRDD = storeStringRDD.map(storeAsCsv -> Store.parse(storeAsCsv));
//		JavaPairRDD<Integer, Integer> toto = storeRDD.mapToPair(store ->  new Tuple2<>(store.getId(), store.getRegionId()));

		// Broadcater la Map à travers les Workers
		Broadcast<Map<Integer, Integer>> storeRegionMapBrdct = SparkConfig.getSparkContext().broadcast(storeRegionMap,
				ClassTag$.MODULE$.apply(Map.class));

		// Faire un Map-side Join
		JavaPairRDD<Integer, Double> caByRegion = salesAsObjects
				.mapToPair(sale -> new Tuple2<>(storeRegionMapBrdct.value().getOrDefault(sale.getStoreId(), -1),
						sale.getUnitSales() * sale.getStoreSales()))
				.reduceByKey((a, b) -> a + b);

		caByRegion.collectAsMap().forEach((regionIdKey, caByRegionValue) -> System.out
				.println("Region : " + regionIdKey + " avec un CA : " + caByRegionValue));
	}

}
