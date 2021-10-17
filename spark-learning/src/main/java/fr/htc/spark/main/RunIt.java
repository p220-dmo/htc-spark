package fr.htc.spark.main;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import fr.htc.spark.beans.Sale;
import fr.htc.spark.config.SparkConfig;

public class RunIt {

	/**
	 * Exercice 3: Charger le fichier des ventes (sales.csv) dans une RDD de type
	 * Sale. La classe Sale est aussi à développer type String
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		JavaSparkContext jsc = SparkConfig.getJavaSparkContext();
		JavaRDD<String> salesAsStringRDD = jsc.textFile("data/sales.csv");


		// Charger le fichier comme à l'exercice 2 et mapper chaque ligne vers un objet
		// Sale qu'on aura créé !!!
		
		JavaRDD<Sale> salesAsObjects = salesAsStringRDD
				 .map(csvLine -> Sale.parse(csvLine, ";"));
		
		//Afficher les 10 premier objets Sale
		
		salesAsObjects.take(10).forEach(sale -> System.out.println(sale));

	}

}
