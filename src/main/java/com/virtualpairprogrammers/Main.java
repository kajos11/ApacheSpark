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
		
		List<Integer> inputData = new ArrayList<>();
		inputData.add(35);
		inputData.add(12);
		inputData.add(90);
		inputData.add(20);
		
		Logger.getLogger("org.apache").setLevel(Level.WARN);
		
		SparkConf conf = new SparkConf().setAppName("Starting Spark").setMaster("local[*]"); // local[*] -> run on all cores of local  
		JavaSparkContext sc = new JavaSparkContext(conf);
		
		JavaRDD<Integer> myRdd = sc.parallelize(inputData);
		
		Integer result = myRdd.reduce((value1, value2) -> value1 + value2);
		
		JavaRDD<Double> sqrtRdd = myRdd.map((value)->Math.sqrt(value));
		
		sqrtRdd.collect().forEach(System.out::println);
		
		System.out.println(result);
		System.out.println(sqrtRdd.count());
		// how many elements just using map and reduce
		Integer sqrtRddCount = sqrtRdd.map((val)->1).reduce((val1,val2)-> val1+val2);
		System.out.println("sqrtRddCount: "+sqrtRddCount);
		
		sc.close();
		
		

	}

}

