package fr.htc.spark.main;

import java.util.Map;
import java.util.function.Function;

import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.broadcast.Broadcast;

import fr.htc.spark.beans.Sale;
import fr.htc.spark.beans.TimeByDay;
import fr.htc.spark.config.SparkConfig;
import scala.Tuple2;
import scala.reflect.ClassTag$;

public class RunIt {

	/**
	 * Exercice 7: Comparer les ventes (en termes de CA) entre les premiers
	 * trimestres (Q1) de 1997 et 1998
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		JavaSparkContext jsc = SparkConfig.getJavaSparkContext();
		JavaRDD<String> salesAsStringRDD = jsc.textFile("data/sales.csv");
		JavaRDD<Sale> salesAsObjects = salesAsStringRDD.map(csvLine -> Sale.parse(csvLine, ";"));

// Clé=TimeId et Valeur=Année
		Map<Integer, Integer> quarterTimeId = jsc.textFile("data/time.csv").map(time -> TimeByDay.parse(time))
				.filter(time -> time.getQuarter().equals("Q1"))
				.mapToPair(timeByDay -> new Tuple2<Integer, Integer>(timeByDay.getTimeId(), timeByDay.getYear()))
				.collectAsMap();

//Broadcast 
		Broadcast<Map<Integer, Integer>> filteredTimeIds = SparkConfig.getSparkContext().broadcast(quarterTimeId,
				ClassTag$.MODULE$.apply(Map.class));

		JavaPairRDD<Integer, Double> yearCAQuarter = salesAsObjects
				.filter(sale -> filteredTimeIds.value().containsKey(sale.getTimeId()))
				.mapToPair(
						sale -> new Tuple2<>(filteredTimeIds.value().getOrDefault(sale.getTimeId(), -1), sale.getCa()))
				.reduceByKey((x, y) -> x + y);

		yearCAQuarter.collectAsMap()
				.forEach((yearKey, caByYear) -> System.out.println("CA Q1 de l'année " + yearKey + " : " + caByYear));

	}

}
