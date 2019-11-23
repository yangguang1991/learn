package com.example.clustering.dbscan;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:  一个简单，特殊版的DBSCAN，因为数目是1个就认为是核心点了
 * @user: yang
 * @Time: 2019/11/23  20:47
 */
public class DBScan {

    public static double getDistance(Station s1, Station s2) {
        return Math.pow(s1.getLongl() - s2.getLongl(), 2.0) +
                Math.pow(s1.getLa() - s2.getLa(), 2.0);
    }

    public static void main(String[] args) {

        Station s1 = new Station();
        s1.setLongl(0);
        s1.setLa(0);

        Station s2 = new Station();
        s2.setLongl(1);
        s2.setLa(0);

        Station s3 = new Station();
        s3.setLongl(2);
        s3.setLa(0);

        Station s4 = new Station();
        s4.setLongl(90);
        s4.setLa(0);


        List<Station> list = new ArrayList<>();
        list.add(s1);
        list.add(s2);
        list.add(s3);
        list.add(s4);


        int cluster = 0;
        Station temp = null;

        for (int i = 0; i < list.size(); i++) {
            temp = list.get(i);
            if (temp.getCluster() == 0) {//说明不属于任何的簇,那么需要设置簇
                temp.setCluster(++cluster);
            }
            //
            for (int j = i + 1; j < list.size(); j++) {
                if (getDistance(temp, list.get(j)) <= 1) {
                    list.get(j).setCluster(temp.getCluster());
                }
            }
        }


        for (Station temp1 : list) {

            System.out.println(temp1.getLongl() + "   " + temp1.getCluster());

        }


    }

}
