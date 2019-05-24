package com.inspur.hdfs;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @user: yangguang_lc
 * @Time: 2018/6/15  9:13
 */
public class TestHdfs {


    public static String uri = "hdfs://indata-10-110-2-102.indata.com:8020";
    public static String defaultAddress = "webhdfs://indata-10-110-2-102.indata.com:8020";// 设置hdfs的连接方式为webhdfs，通过HTTP访问hdfs

    public static List<String> getDir() {
        String dir = "/apps/kafka";
        List<String> names = null;
        if (dir.equals("")) {
            return new ArrayList<String>();
        }
        dir = uri + dir;
//        Configuration conf = getConfig();
//        FileSystem fs;
//        try {
//            fs = FileSystem.get(URI.create(dir), conf);
//            FileStatus[] stats = fs.listStatus(new Path(dir));
//            names = new ArrayList<String>();
//            for (int i = 0; i < stats.length; ++i) {
//                if (stats[i].isFile()) {
//                    // regular file
//                    names.add(stats[i].getPath().toString());
//                } else if (stats[i].isDirectory()) {
//                    // dir
//                    names.add(stats[i].getPath().toString());
//                } else if (stats[i].isSymlink()) {
//                    // is s symlink in linux
//                    names.add(stats[i].getPath().toString());
//                }
//            }
//            fs.close();

        return names;
    }

//    public static Configuration getConfig() {
//        PropertiesUtils.loadProperties();
//        Configuration conf = null;
//
//        // TODO Auto-generated method stub
//        conf = new Configuration();
//        String KRB5_CONFIG = PropertiesUtils.getProperties("KRB5_CONFIG");
//        System.setProperty("java.security.krb5.conf", KRB5_CONFIG);// 设置kerberos配置信息
//        conf.set("fs.defaultFS", defaultAddress);// namenode地址
//        conf.set("fs.hdfs.impl",
//                org.apache.hadoop.hdfs.DistributedFileSystem.class.getName());
//        conf.set("fs.file.impl",
//                org.apache.hadoop.fs.LocalFileSystem.class.getName());
//        conf.set("fs.webhdfs.impl",
//                org.apache.hadoop.hdfs.web.WebHdfsFileSystem.class.getName());
//        conf.setBoolean("hadoop.security.authentication", true);
//        conf.set("hadoop.security.authentication", "kerberos");
//        conf.set("dfs.namenode.kerberos.principal", "nn/_HOST@INDATA.COM");// hdfs-site.xml中配置信息
//        conf.set("dfs.datanode.kerberos.principal", "dn/_HOST@INDATA.COM");// hdfs-site.xml中配置信息
//        UserGroupInformation.setConfiguration(conf);
//        try {
//            String KRB5_KEYTAB = PropertiesUtils.getProperties("KRB5_KEYTAB");
//            UserGroupInformation.loginUserFromKeytab("nn/_HOST@INDATA.COM",
//                    "/etc/security/keytabs/nn.service.keytab");// kerberos 认证
//            UserGroupInformation.getLoginUser();
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//
//        return conf;
//    }

    public static void main(String[] args) {
        System.out.println(getDir());
    }


}
