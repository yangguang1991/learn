package com.inspur.wordcount;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * @author Yangguang
 * @Title: TestInvertedIndex
 * @ProjectName learn
 * @Description: TODO
 * @date 2019/10/28 11:01
 * @Version 1.0.0
 */
public class TestInvertedIndex {


    public static Integer Wrong_Number = 0;

    //自定义的mapper，继承org.apache.hadoop.mapreduce.Mapper
    public static class MyMapper extends Mapper<LongWritable, Text, Text, Text> {
        @Override
        protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, Text>.Context context)
                throws IOException, InterruptedException {
            String line = value.toString();
            System.out.println("line=" + line);
            //split  函数是用于按指定字符（串）或正则去分割某个字符串，结果以字符串数组形式返回，这里按照"\t"来分割text文件中字符，即一个制表符，这就是为什么我在文本中用了空格分割，导致最后的结果有很大的出入。
            String[] splited = line.split(" ");


            for (int i = 0; i < splited.length; i++) {
                System.out.println("tmp=" + splited[i]);
            }


            //foreach 就是 for（元素类型t 元素变量x:遍历对象obj）{引用x的java语句}
            try {
                context.write(new Text(splited[1]), new Text(splited[0]));
                System.out.println("1=" + splited[1] + "  0=" + splited[0]);
            } catch (Exception e) {
                Wrong_Number++;
                System.out.println("some error happen");
                e.printStackTrace();
            }

        }
    }

    //map运行之后会有一个shuffle过程，
    //这个过程会有排序，以及按照key进行分组，还有落盘的操作
    //然后reduce在读取的时候就可以按组进行reduce

    public static class MyReducer extends org.apache.hadoop.mapreduce.Reducer<Text, Text, Text, Text> {
        @Override
        protected void reduce(Text k2, Iterable<Text> v2s,
                              Reducer<Text, Text, Text, Text>.Context context) throws IOException, InterruptedException {
            String v = "";
            for (Text v2 : v2s) {
                v = v2.toString() + "," + v;
            }
            System.out.println("k2=" + k2 + " v=" + v);
            context.write(k2, new Text(v));
        }
    }

    //客户端代码，写完交给ResourceManager框架去执行
    //执行命令
    // hadoop  jar   Hadoop-1.0-SNAPSHOT.jar com.inspur.wordcount.TestInvertedIndex  "/oozie_work/shellTest/test.sh"  "/oozie_work/shellTest/"

    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();

        Job job = Job.getInstance(conf, "wordcount by yang");
        //打成jar执行
        job.setJarByClass(TestInvertedIndex.class);
        //数据在哪里？
        FileInputFormat.setInputPaths(job, args[0]);
        //使用哪个mapper处理输入的数据？
        job.setMapperClass(TestInvertedIndex.MyMapper.class);
        //map输出的数据类型是什么？
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);
        //设置reduce任务的个数，默认是只有一个
        job.setNumReduceTasks(2);
        //使用哪个reducer处理输入的数据？
        job.setReducerClass(TestInvertedIndex.MyReducer.class);
        //reduce输出的数据类型是什么？
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);
        //数据输出到哪里？
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        //交给yarn去执行，直到执行结束才退出本程序
        //为true的时候会在界面打印相关的信息
        job.waitForCompletion(true);
    }


}
