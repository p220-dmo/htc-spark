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
	 * Exercice 7: Comparer les ventes (en termes de CA) entre les premiers trimestres (Q1) de 1997 et 1998
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		JavaSparkContext jsc = SparkConfig.getJavaSparkContext();
		JavaRDD<String> salesAsStringRDD = jsc.textFile("data/sales.csv");
		JavaRDD<Sale> salesAsObjects = salesAsStringRDD.map(csvLine -> Sale.parse(csvLine, ";"));
		/*$$$$$   /$$              /$$$$$$$$ /$$$$$$  /$$   /$$ /$$$$$$$$    /$$$$$$$   /$$$$$$   /$$$$$$   /$$$$$$  /$$$$$$$$ /$$$$$$$ 
		|_  $$_/  | $$             | $$_____//$$__  $$| $$  | $$|__  $$__/   | $$__  $$ /$$__  $$ /$$__  $$ /$$__  $$| $$_____/| $$__  $$
		  | $$    | $$             | $$     | $$  \ $$| $$  | $$   | $$      | $$  \ $$| $$  \ $$| $$  \__/| $$  \__/| $$      | $$  \ $$
		  | $$    | $$             | $$$$$  | $$$$$$$$| $$  | $$   | $$      | $$$$$$$ | $$  | $$|  $$$$$$ |  $$$$$$ | $$$$$   | $$$$$$$/
		  | $$    | $$             | $$__/  | $$__  $$| $$  | $$   | $$      | $$__  $$| $$  | $$ \____  $$ \____  $$| $$__/   | $$__  $$
		  | $$    | $$             | $$     | $$  | $$| $$  | $$   | $$      | $$  \ $$| $$  | $$ /$$  \ $$ /$$  \ $$| $$      | $$  \ $$
		 /$$$$$$  | $$$$$$$$       | $$     | $$  | $$|  $$$$$$/   | $$      | $$$$$$$/|  $$$$$$/|  $$$$$$/|  $$$$$$/| $$$$$$$$| $$  | $$
		|______/  |________/       |__/     |__/  |__/ \______/    |__/      |_______/  \______/  \______/  \______/ |________/|__/  |_*/
		
	                                                                                                                                              
	
		
	}

}
