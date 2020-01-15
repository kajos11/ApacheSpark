package com.virtualpairprogrammers;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import scala.Tuple2;

public class Main {

	public static void main(String[] args) {
		
		List<String> inputData = new ArrayList<>();
		inputData.add("WARN: Tuesday 4 September 0405");
		inputData.add("ERROR: Tuesday 4 September 0408");
		inputData.add("FATAL: Wednesday 5 September 1632");
		inputData.add("ERROR: Friday 7 September 1854");
		inputData.add("WARN: Saturday 8 September 1905");
		inputData.add("ERROR: Monday 3 September 1945");
		
		Logger.getLogger("org.apache").setLevel(Level.WARN);
		
		SparkConf conf = new SparkConf().setAppName("Starting Spark").setMaster("local[*]"); // local[*] -> run on all cores of local  
		JavaSparkContext sc = new JavaSparkContext(conf);
		
		sc.parallelize(inputData)
		 .mapToPair((rawValue)->new Tuple2<>(rawValue.split(":")[0],1L))
		 .reduceByKey((value1, value2)->value1+value2)
		 .foreach(tuple -> System.out.println(tuple._1+" has "+tuple._2+" instances"));
		
		
	
		/*
		 * //JavaRDD<Double> sqrtRdd = originalIntegers.map((value)->Math.sqrt(value));
		 * 
		 * JavaRDD<IntegerWithSquareRoot> sqrtRdd = originalIntegers.map(value -> new
		 * IntegerWithSquareRoot(value));
		 * 
		 * //Tuple2<Integer,Double> myVal = new Tuple2<> (9,3.0);
		 * 
		 * JavaRDD<Tuple2<Integer,Double>> sqrtRddTuple = originalIntegers.map(value ->
		 * new Tuple2<>(value,Math.sqrt(value)));
		 * 
		 * sqrtRddTuple.collect().forEach(System.out::println);
		 */
		/*
		 * JavaRDD<Integer> myRdd = sc.parallelize(inputData);
		 * 
		 * Integer result = myRdd.reduce((value1, value2) -> value1 + value2);
		 * 
		 * JavaRDD<Double> sqrtRdd = myRdd.map((value)->Math.sqrt(value));
		 * 
		 * sqrtRdd.collect().forEach(System.out::println);
		 * 
		 * System.out.println(result); System.out.println(sqrtRdd.count()); // how many
		 * elements just using map and reduce Integer sqrtRddCount =
		 * sqrtRdd.map((val)->1).reduce((val1,val2)-> val1+val2);
		 * System.out.println("sqrtRddCount: "+sqrtRddCount);
		 */
		
		sc.close();
		
		

	}

}


