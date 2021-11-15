package fr.htc.spark.main;

import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;

import fr.htc.spark.beans.Customer;
import fr.htc.spark.beans.Sale;
import fr.htc.spark.config.SparkConfig;
import scala.Tuple2;

public class RunIt {

	/**
	 * Exercice 9: calculer le chiffre d'affaires généré par niveau d'instruction
	 * (colonne education du fichier customer.csv. Petite contrainte, le fichier
	 * customer.csv ne peut pas être broadcasté en l'état.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		JavaSparkContext jsc = SparkConfig.getJavaSparkContext();
		JavaRDD<String> salesAsStringRDD = jsc.textFile("data/sales.csv");
		JavaRDD<Sale> salesAsObjects = salesAsStringRDD.map(csvLine -> Sale.parse(csvLine, ";"));

		JavaPairRDD<Long, Double> customerSales = salesAsObjects
				.mapToPair(sale -> new Tuple2<>(sale.getCustomerId(), sale.getUnitSales() * sale.getStoreSales()));

		JavaPairRDD<Long, String> customerEducationLevel = SparkConfig.getJavaSparkContext()
				.textFile("data/customer.csv").mapToPair((PairFunction<String, Long, String>) s -> {
					Customer parse = Customer.parse(s);
					return new Tuple2<>(parse.getCustomerId(), parse.getEducation());
				});

		JavaPairRDD<String, Double> educationExpenses = customerSales.join(customerEducationLevel).values()
				.mapToPair((PairFunction<Tuple2<Double, String>, String, Double>) t -> new Tuple2<>(t._2(), t._1()))
				.reduceByKey((Function2<Double, Double, Double>) (o, o2) -> o + o2);
		educationExpenses.collectAsMap().forEach((educationLevelKey, caByCustomerValue) -> System.out
				.println("Education level : " + educationLevelKey + " a un chiffre d'affaires : " + caByCustomerValue));

	}

}
