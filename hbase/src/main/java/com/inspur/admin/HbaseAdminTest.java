package com.inspur.admin;

import com.inspur.Constant.Constants;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.client.ClusterConnection;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * @Description: 该类主要实现hbase管理员相关功能
 * @user: yangguang_lc
 * @Time: 2018/8/26  17:15
 */
public class HbaseAdminTest {
    public static Configuration conf = null;
    private static Connection connection = null;


    static {
        conf = HBaseConfiguration.create();
        conf.addResource(Constants.Hbase_Site_Xml);
        conf.set("hbase.zookeeper.quorum", Constants.Zookeeper_Host);
        conf.set("hbase.zookeeper.property.clientPort", "2181");
        conf.set("hbase.master", "slave0:16000");
        try {
            connection = ConnectionFactory.createConnection(conf);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * @Description: 对某一个表增加一个列族
     */
    public void addColumnFamily() {
        try {
            HBaseAdmin admin = new HBaseAdmin((ClusterConnection) connection);
            HColumnDescriptor columnDescriptor = new HColumnDescriptor("cf2");
            admin.addColumn("t2", columnDescriptor);
            System.out.println(" addColumn ");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * @Description: 对某一个表删除一个列族
     */
    public void removeColumnFamily() {
        try {
            HBaseAdmin admin = new HBaseAdmin((ClusterConnection) connection);
            admin.deleteColumn("t1", "f3");
            System.out.println(" deleteColumn ");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * @Description: 判断一个表是否存在
     */
    public void tableExists() {
        try {
            HBaseAdmin admin = (HBaseAdmin) connection.getAdmin();
            System.out.println(" tableExists " + admin.tableExists("t1"));
            System.out.println(" tableExists " + admin.tableExists("t1".getBytes()));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * @Description: 删除一个表
     */
    public void deleteTable() {
        try {
            String tableName = "t2";
            HBaseAdmin admin = new HBaseAdmin((ClusterConnection) connection);


            if (admin.tableExists(tableName)) {
                admin.disableTable(tableName);
                admin.deleteTable(tableName);
                System.out.println(" tableExists " + admin.tableExists("t1".getBytes()));
            } else {

                System.out.println(" tableExists " + false);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //根据rowkey来划分region

    public byte[][] getsplitKeys() {

        String[] keys = new String[]{"rowkey01|", "rowkey02|","rowkey03|"};
        byte[][] splitKeys = new byte[keys.length][];
        TreeSet<byte[]> rows = new TreeSet<byte[]>(Bytes.BYTES_COMPARATOR);//升序排序
        for (int i = 0; i < keys.length; i++) {
            rows.add(Bytes.toBytes(keys[i]));
        }
        Iterator<byte[]> rowKeyIter = rows.iterator();
        int i = 0;
        while (rowKeyIter.hasNext()) {
            byte[] tempRow = rowKeyIter.next();
            System.out.println(new String(tempRow));
            splitKeys[i] = tempRow;
            i++;
        }
        return splitKeys;

    }


    /**
     * @Description: 创建一个表,并且设置预分区
     */
    public void createTable() {
        try {
            String tableName = "split_t2";
            HBaseAdmin admin = new HBaseAdmin((ClusterConnection) connection);

            //不存在的时候才会创建该表
            if (false == admin.tableExists(tableName)) {

                HTableDescriptor descriptor = new HTableDescriptor(tableName);

                descriptor.addFamily(new HColumnDescriptor("cf1"));
                descriptor.setValue("prefix_split_key_policy.prefix_length", "8");
                admin.createTable(descriptor, getsplitKeys());
                System.out.println("tableExists=" + admin.tableExists(tableName));

            } else {
                System.out.println("table exist ");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {

        HbaseAdminTest ha = new HbaseAdminTest();
        ha.createTable();;

    }
}
