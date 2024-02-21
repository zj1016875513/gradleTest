package com.zz.test;


import com.zz.util.SparkEnv;
import org.apache.hadoop.io.compress.GzipCodec;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;

public class Test {
    public static void main(String[] args) {
//        TextVector.TextVectorAttr.Builder builder = TextVector.TextVectorAttr.newBuilder();
//        builder.setUrl("www.abc.com");
//        builder.setHot(0.1f);
//        TextVector.TextVectorAttr build = builder.build();
//        System.out.println(build);


        System.out.println("abcdef");
        SparkConf conf = SparkEnv.getSparkDefaultConfig("test", true);
        System.out.println(conf);
        JavaSparkContext jsc = SparkEnv.getDefaultJavaSparkContext("test", true, conf);

        jsc.textFile("D:\\CodeProject\\JavaProject\\gradleTest\\src\\main\\resources\\test\\testFile1.txt")
                .foreach(x->System.out.println(x));

        jsc.textFile("D:\\CodeProject\\JavaProject\\gradleTest\\src\\main\\resources\\test\\testFile1.txt")
                .saveAsTextFile("D:\\CodeProject\\JavaProject\\gradleTest\\src\\main\\resources\\test\\testFile1_out", GzipCodec.class);

    }
}
