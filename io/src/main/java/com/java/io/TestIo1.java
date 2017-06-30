package com.java.io;

import sun.misc.Unsafe;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Ness on 2017/6/24.
 */
public class TestIo1 {


//
//    public static double[] Mercator2BD09(double lng,double lat) {
//        double[] lnglat=new double[2];
//        List<Double> c=null;
//        lnglat[0]=Math.abs(lng);
//        lnglat[1]=Math.abs(lat);
//
//        for(int d=0;d<6;d++)
//        {
//            if(lnglat[1]>=Sp.get(d))
//            {
//                c = Au.get(d);
//                break;
//            }
//        }
//        lnglat = Yr(lnglat,c);
//        return lnglat;
//    }
//
//
//    public static double[] Yr(double[] lnglat,List<Double> b){
//        if(b!=null){
//            double c = b.get(0)+b.get(1)*Math.abs(lnglat[0]);
//            double d = Math.abs(lnglat[1])/b.get(9);
//            d = b.get(2)+b.get(3)*d+b.get(4)*d*d+b.get(5)*d*d*d+b.get(6)*d*d*d*d+b.get(7)*d*d*d*d*d+b.get(8)*d*d*d*d*d*d;
//            BigDecimal bd=new BigDecimal(c*(0>lnglat[0]?-1:1));
//            lnglat[0]=bd.setScale(6, BigDecimal.ROUND_UP).doubleValue();
//            bd=new BigDecimal(d*(0>lnglat[1]?-1:1));
//            lnglat[1]=bd.setScale(6, BigDecimal.ROUND_UP).doubleValue();
//            return lnglat;
//        }
//        return null;
//    }


    public static void main(String[] args) throws IOException, NoSuchFieldException, IllegalAccessException {

        Field f = Unsafe.class.getDeclaredField("theUnsafe");
        f.setAccessible(true);
        Unsafe unsafe = (Unsafe) f.get("theUnsafe");


        System.out.println(unsafe);

    }

}
