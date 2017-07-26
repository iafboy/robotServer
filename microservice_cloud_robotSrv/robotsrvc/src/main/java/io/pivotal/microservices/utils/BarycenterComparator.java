package io.pivotal.microservices.utils;

import io.pivotal.microservices.model.Barycenter;

import java.util.Comparator;

/**
 * Created by xiaoju on 2017/7/25.
 */
public class BarycenterComparator implements Comparator {
    @Override
    public int compare(Object first, Object second) {
        double first_rssi=((Barycenter)first).getR();
        double second_rssi=((Barycenter)second).getR();
        if(first_rssi>second_rssi){
            return 1;
        }else if(first_rssi==second_rssi){
            return -1;
        }else
            return 0;
    }
}
