package com.zz.util;

import com.github.luben.zstd.Zstd;
import org.apache.commons.codec.binary.Base64;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SparkSession;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;


public class SparkEnv {
    public static void main(String[] args) {

    }

    public static SparkConf getSparkDefaultConfig(String appName, boolean isDebug){
        SparkConf sparkConf = new SparkConf();
        sparkConf.setAppName(appName);

        sparkConf.registerKryoClasses(new Class[]{ArrayList.class, HashSet.class, HashMap.class,String.class,Integer.class,Double.class,Long.class});

        sparkConf.set("spark.serializer","org.apache.spark.serializer.KryoSerializer");
        sparkConf.set("spark.kryo.registrationRequired","true");

        if (isDebug) {
            System.out.println("debug envs...");
            sparkConf.setMaster("local[8]");
            sparkConf.set("spark.ddriver.host","127.0.0.1");
        }else {
            System.out.println("production envs...");
        }
        return sparkConf;
    }

    public static JavaSparkContext getDefaultJavaSparkContext(String appName,boolean isDebug, SparkConf sparkConf){
        if (sparkConf == null) {
            sparkConf = getSparkDefaultConfig(appName, isDebug);
        }
        JavaSparkContext jsc = new JavaSparkContext(sparkConf);
        jsc.setLogLevel("WARN");
        return jsc;
    }

    public static SparkSession getDefaultSparkSession(String appName,boolean isDebug ,SparkConf sparkConf){
        if (sparkConf == null) {
            sparkConf = getSparkDefaultConfig(appName, isDebug);
        }
        SparkSession sc = SparkSession.builder().config(sparkConf).getOrCreate();
        sc.sparkContext().setLogLevel("WARN");
        return sc;
    }

    public static String deCompressString(ByteBuffer inBuffer, ByteBuffer outBuffer, byte[] copy){
        inBuffer.clear();
        inBuffer.put(copy);
        inBuffer.flip();

        outBuffer.clear();
        Zstd.decompress(outBuffer,inBuffer);
        outBuffer.flip();
        return StandardCharsets.UTF_8.decode(outBuffer).toString();
    }

    public static String compress(String line){
        byte[] compress = Zstd.compress(line.getBytes(StandardCharsets.UTF_8));
        return Base64.encodeBase64String(compress);
    }
}
