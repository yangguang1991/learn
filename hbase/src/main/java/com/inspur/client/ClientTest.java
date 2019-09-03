package com.inspur.client;

import com.inspur.Constant.Constants;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.Iterator;
import java.util.NavigableMap;

/**
 * @Description:
 * @user: yangguang_lc
 * @Time: 2018/8/27  17:23
 */
public class ClientTest {

    public static Configuration conf = null;

    static {
        conf = HBaseConfiguration.create();
        conf.addResource(Constants.Hbase_Site_Xml);
        conf.set("hbase.zookeeper.quorum", Constants.Zookeeper_Host);
        conf.set("hbase.zookeeper.property.clientPort", "2181");
        conf.set("hbase.master", "slave0:16000");

    }

    /**
     * @Description: 对某一个表插入一条数据
     */
    public void putRow(String tableName, String rowkey) {

        HTable table = null;

        try {
            table = new HTable(conf, tableName);
            Put p = new Put(Bytes.toBytes(rowkey));

            p.addColumn(Bytes.toBytes("cf1"), Bytes.toBytes("c1"), Bytes.toBytes("value1"));

            table.put(p);


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                table.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @Description: 获取某一个表的一条数据
     */
    public void getRow() {
        String tableName = "t1";
        HTable table = null;

        try {
            table = new HTable(conf, tableName);
            Get g = new Get(Bytes.toBytes("rowkey001"));
            Result result = table.get(g);
            byte[] value1 =
                    result.getValue(Bytes.toBytes("f1"), Bytes.toBytes("col1"));
            byte[] value2 =
                    result.getValue(Bytes.toBytes("f2"), Bytes.toBytes("col1"));
            byte[] value3 =
                    result.getValue(Bytes.toBytes("f2"), Bytes.toBytes("col2"));


            System.out.println(new String(value1));
            System.out.println(new String(value2));
            System.out.println(new String(value3));


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                table.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @Description: 删除某一个表的一条数据
     */
    public void deleteRow() {
        String tableName = "t1";
        HTable table = null;

        try {
            table = new HTable(conf, tableName);
            Delete delete = new Delete(("rowkey001").getBytes());
            delete.addFamily("f2".getBytes());
            table.delete(delete);
            System.out.println("delete row001,f2");

            Delete delete2 = new Delete(("rowkey002").getBytes());
            delete2.addColumns("f2".getBytes(), "col1".getBytes());
            table.delete(delete2);
            System.out.println("delete row002,f2,col1");


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                table.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * @Description: 扫描某一个表
     */
    public void scanTable() {
        String tableName = "t1";
        HTable table = null;
        //类似于iterator，只不过需要关闭释放资源
        ResultScanner rs = null;

        try {
            table = new HTable(conf, tableName);
            Scan sc = new Scan();
            // sc.addColumn("f1".getBytes(), "col1".getBytes());
            sc.addFamily("f1".getBytes());
            rs = table.getScanner(sc);

            Result result = null;
            String str = null;
            while ((result = rs.next()) != null) {
                str = new String(result.getValue("f1".getBytes(), "col1".getBytes()));
                System.out.println("value=" + str);
            }


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                table.close();
                rs.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * @Description: 设置起始rowkey和endkey
     */
    public void scanTableWithStartAndEndRowkey() {
        String tableName = "t1";
        HTable table = null;
        //类似于iterator，只不过需要关闭释放资源
        ResultScanner rs = null;

        try {
            table = new HTable(conf, tableName);
            Scan sc = new Scan();
            // sc.addColumn("f1".getBytes(), "col1".getBytes());
            //sc.addFamily("f1".getBytes());

            sc.withStartRow("rowkey001".getBytes(), true);
            sc.withStopRow("rowkey003".getBytes(), true);

            rs = table.getScanner(sc);

            Result result = null;
            String str = null;
            String rowkey = "";
            while ((result = rs.next()) != null) {
                System.out.println("========start========");
                str = new String(result.getValue("cf1".getBytes(), "col1".getBytes()));
                rowkey = new String(result.getRow());
                System.out.println("rowkey=" + rowkey + "  value=" + str);

                System.out.println("========获取的是特定的列族========");
                NavigableMap<byte[], byte[]> map = result.getFamilyMap("cf1".getBytes());
                scanMap(map);
                System.out.println("========获取的是所有的========");
                NavigableMap<byte[], NavigableMap<byte[], byte[]>> map2 = result.getNoVersionMap();

                Iterator it2 = map2.keySet().iterator();
                byte[] key2 = null;
                while (it2.hasNext()) {
                    key2 = (byte[]) it2.next();
                    System.out.println("key2=" + new String(key2));
                    scanMap(map2.get(key2));

                }

            }


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                table.close();
                rs.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @Description: 设置起始rowkey和endkey
     */
    public void scanMap(NavigableMap<byte[], byte[]> map) {
        Iterator it = null;
        byte[] key = null;
        it = map.keySet().iterator();
        while (it.hasNext()) {
            key = (byte[]) it.next();
            System.out.println("key=" + new String(key) + "  value=" + new String(map.get(key)));
        }

    }


    public static void main(String[] args) {
        ClientTest ct = new ClientTest();
        ct.putRow("split_t2", "rowkey011");
        HBaseConfiguration.create();

    }

}
