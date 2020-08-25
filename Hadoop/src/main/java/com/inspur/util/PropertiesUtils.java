package com.inspur.util;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author Yangguang
 * @Title: PropertiesUtils
 * @ProjectName learn
 * @Description: TODO
 * @date 2019/8/17 12:58
 * @Version 1.0.0
 */


public class PropertiesUtils {

    public static Properties p = null;

    public static Log log = LogFactory.getLog(PropertiesUtils.class);

    public static void loadProperties() {
        try {

            InputStream input = PropertiesUtils.class.getClassLoader().getResourceAsStream("db.properties");
            p = new Properties();
            p.load(input);

        } catch (IOException e) {

            log.error("PropertiesUtil loadPoperty error", e);
        }
    }

    //获取配置文件中的port
    public static String getUrl() {
        return p.getProperty("url");
    }

    //获取配置文件中的password
    public static String getPassword() {
        return p.getProperty("password");
    }


    public static String getUser() {
        return p.getProperty("user");
    }

    public static String getDriver() {
        return p.getProperty("driver");
    }


    //获取配置文件中的port
    public static String getMysqlUrl() {
        return p.getProperty("mysqlurl");
    }

    //获取配置文件中的password
    public static String getMysqlPassword() {
        return p.getProperty("mysqlpassWord");
    }


    public static String getMysqlUser() {
        return p.getProperty("mysqluserName");
    }

    public static String getMysqlDriver() {
        return p.getProperty("mysqldriver");
    }


    public static String getProperties(String key) {
        return p.getProperty(key);
    }


    public static void main(String[] args) {


        System.out.println(getMysqlDriver());

    }

}
