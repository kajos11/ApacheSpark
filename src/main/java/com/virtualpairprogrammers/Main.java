package com.virtualpairprogrammers;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

public class Main {

	public static void main(String[] args) {
		
		List<Double> inputData = new ArrayList<>();
		inputData.add(23.3);
		inputData.add(236.34267);
		inputData.add(81.55);
		inputData.add(10.4);
		
		Logger.getLogger("org.apache").setLevel(Level.WARN);
		
		SparkConf conf = new SparkConf().setAppName("Starting Spark").setMaster("local[*]"); // local[*] -> run on all cores of local system
		JavaSparkContext sc = new JavaSparkContext(conf);
		
		JavaRDD<Double> myRdd = sc.parallelize(inputData);
		
		
		
		sc.close();
		
		

	}

}

