
package com.inspur.learn.jedis.dao;

import redis.clients.jedis.Jedis;

/**
 * @Description:
 * @user: yangguang_lc
 * @Time: 2018/7/17  14:45
 */

//用于连接redis的
//这个单链接
public class RedisDao {

    public static Jedis jedis;
    public static String ip = "192.168.110.164";
    public static int port = 6379;

    public static Jedis getJedis() {

        if (null == jedis) {
            // 虚拟机的ip地址
            jedis = new Jedis(ip, port);
            // 密码
            jedis.auth("93ormKe+4rc4aWLzohh8+jieshen");

        }
        return jedis;

    }

    // redis也需要关闭才可以将数据存到redis数据库中
    public static void close(Jedis jedis) {
        if (null != jedis) {
            jedis.close();
        }
    }

    public static void main(String[] args) {
        jedis = getJedis();
        System.out.println(jedis);


//        System.out.println("seq:" + jedis.incr("seq"));
//        System.out.println("seq:" + jedis.incr("name"));
    }
}
