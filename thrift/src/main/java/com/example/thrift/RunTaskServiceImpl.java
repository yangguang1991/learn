package com.example.thrift;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFlatMapFunction;
import org.apache.spark.api.java.function.VoidFunction;
import org.apache.spark.sql.SparkSession;
import org.apache.thrift.TException;
import scala.Tuple2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Description: 实现一个thrift远程调度，发送数据给spark，spark进行wordcount,
 * 并且证明了持续性的提交任务需要在yarn-client模式下运行，yarn-cluster是不满足的
 * @user: yang
 * @Time: 2019/7/8  21:52
 */
public class RunTaskServiceImpl implements RunTaskService.Iface, Serializable {


    public static Log log = LogFactory.getLog(RunTaskServiceImpl.class);


    SparkSession sparkSession = null;

    public SparkSession getSparkSession() {
        return sparkSession;
    }

    public void setSparkSession(SparkSession sparkSession) {
        this.sparkSession = sparkSession;
    }


    @Override
    public String runKpitask(Task task) throws TException {

        String result = "";
        switch (task.getTaskType()) {
            case KPI:
                result = "kpi";
                wordCount(task.getContent());
                System.out.println(task.getContent());
                break;
            case KPI1:
                result = "KPI1";

                System.out.println(task.getContent());
                break;
            case KPI2:
                result = "KPI2";

                System.out.println(task.getContent());
                break;
        }
        return result;
    }


    public void wordCount(String str) {
        SparkSession sparkSession = this.sparkSession.newSession();
        List<String> list = new ArrayList<>();
        list.add(str);
        JavaSparkContext sc = JavaSparkContext.fromSparkContext(sparkSession.sparkContext());
        JavaRDD<String> text = sc.parallelize(list);

        JavaPairRDD<String, Integer> re = text.mapPartitionsToPair(new PairFlatMapFunction<Iterator<String>, String, Integer>() {
            @Override
            public Iterator<Tuple2<String, Integer>> call(Iterator<String> iterator) throws Exception {

                List<Tuple2<String, Integer>> list2 = new ArrayList<>();
                while (iterator.hasNext()) {
                    String str = iterator.next();
                    String[] arr = str.split(" ");
                    for (int i = 0; i < arr.length; i++) {
                        Tuple2<String, Integer> tuple2 =
                                new Tuple2<>(arr[i], 1);
                        list2.add(tuple2);
                    }
                }
                return list2.iterator();
            }
        }).reduceByKey(new Function2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer integer, Integer integer2) throws Exception {
                return integer + integer2;
            }
        });

//    //结果打印
        re.foreach(new VoidFunction<Tuple2<String, Integer>>() {
            @Override
            public void call(Tuple2<String, Integer> tuple2) throws Exception {

                log.info(tuple2._1 + " =========  " + tuple2._2);
            }
        });
    }

}
