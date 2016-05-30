package com.jinxh.demo.utils;

/**
 * Created by jinxh on 16/3/14.
 */
public class CoordinateUtils {
    public static double PI = 3.14159265358979323;

    public static double R = 6371229;


//根据2点算距离

    public static int getDistance(double lat1, double longt1, double lat2, double longt2

    ) {

        double x, y;
        int distance;

        x = (longt2 - longt1) * PI * R

                * Math.cos(((lat1 + lat2) / 2) * PI / 180) / 180;

        y = (lat2 - lat1) * PI * R / 180;

        distance = (int) Math.hypot(x, y);


        return distance;

    }


//根据距离算角度差

    public static Double getAngulation(Double distance) {

        return (180 * distance) / (PI * R);

    }

    public static String getKmString(int distance) {
        String distanceStr = String.valueOf(distance);
        int distanceStrLength = distanceStr.length();
        if (distanceStrLength < 3) {
            return "0.1km";
        } else if (distanceStrLength > 3) {
            String distanceKm = distanceStr.substring(0, distanceStrLength - 3);
            String distanceM = distanceStr.substring(distanceStrLength - 3, distanceStrLength - 2);
            distanceStr = distanceKm + "." + distanceM;

        } else if (distanceStrLength == 3) {
            distanceStr = distanceStr.substring(0, distanceStrLength - 2);
            distanceStr = "0." + distanceStr;
        }
        return distanceStr + "km";
    }

    public static void main(String args[]) {
        System.out.println(getKmString(1101));
    }
}
