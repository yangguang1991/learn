
package com.inspur.learn.jedis.dao;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashSet;
import java.util.Set;

/**
 * @Description:
 * @user: yangguang_lc
 * @Time: 2018/7/17  14:45
 */

//用于连接redis的
//这个单链接
public class RedisDao {

    public static Jedis jedis;
    public static String ip = "app-redis01.ycbus.com";
    public static int port = 7000;

    public static Jedis getJedis() {

        if (null == jedis) {
            // 虚拟机的ip地址
            jedis = new Jedis(ip, port);
            // 密码
            jedis.auth("93ormKe+4rc4aWLzohh8+jieshen");
        }
        return jedis;
    }

    public static JedisCluster getJedisCluster() {

        JedisPoolConfig poolConfig = new JedisPoolConfig();
        // 最大连接数
        poolConfig.setMaxTotal(100);
        // 最大空闲数
        poolConfig.setMaxIdle(100);
        // 最大允许等待时间，如果超过这个时间还未获取到连接，则会报JedisException异常：
        // Could not get a resource from the pool
        poolConfig.setMaxWaitMillis(5000);
        Set<HostAndPort> nodes = new HashSet<>();
        nodes.add(new HostAndPort("app-redis01.ycbus.com", 7000));
        nodes.add(new HostAndPort("app-redis01.ycbus.com", 7001));
        nodes.add(new HostAndPort("app-redis02.ycbus.com", 7002));
        nodes.add(new HostAndPort("app-redis02.ycbus.com", 7003));
        nodes.add(new HostAndPort("app-redis03.ycbus.com", 7004));
        // 池基本配置
        // MyJedisCluster cluster = new MyJedisCluster(nodes, poolConfig);
        JedisCluster cluster = new JedisCluster(nodes,10,10,10,"6PtE7AHIxJ541hv8IpXL", poolConfig);
        //执行JedisCluster对象中的方法，方法和redis一一对应。
        //cluster.auth("6PtE7AHIxJ541hv8IpXL");
        return cluster;
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
