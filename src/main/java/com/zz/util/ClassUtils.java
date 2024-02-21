package com.zz.util;

import java.util.*;

public class ClassUtils {
    public static List<Class<?>> getBasicClasses(){
        Class<?>[] cls = {
            Object.class,String.class,String[].class,Character.class,
            Integer.class,Integer[].class,int[].class,
            Long.class,Long[].class,long[].class,
            Double.class,Double[].class, double[].class,
            Float.class,Float[].class,float[].class,
            Boolean.class,Boolean[].class,boolean[].class,
            List.class,ArrayList.class, Set.class, HashMap.class,Map.class
        };

        return new ArrayList<>(Arrays.asList(cls));
    }


}
