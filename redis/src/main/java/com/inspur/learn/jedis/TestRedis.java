package com.inspur.learn.jedis;

import com.inspur.learn.jedis.dao.RedisDao;
import com.inspur.learn.util.VoSerial;
import com.inspur.learn.vo.User;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

import javax.sound.midi.Soundbank;
import java.util.*;

/**
 * @Description:
 * @user: yangguang_lc
 * @Time: 2018/7/17  14:53
 */
public class TestRedis {
    // 用的是Junit测试的
    private static Jedis jedis;

    static {
        //jedis = RedisDao.getJedis();
        //jedis = RedisPoolUtil.getJedis();
    }

    /**
     * redis存储字符串
     */
    public void testString() {
        // -----添加数据----------
        jedis.set("name", "xinxin");//
        System.out.println(jedis.get("name"));//

        jedis.append("name", " is my lover"); // 拼接
        System.out.println(jedis.get("name"));

        jedis.del("name"); // 删除某个键
        System.out.println(jedis.get("name"));
        // 设置多个键值对
        jedis.mset("name", "yy", "age", "23", "qq", "123");
        jedis.incr("age"); // 进行加1操作
        System.out.println(jedis.get("name") + "-" + jedis.get("age") + "-"
                + jedis.get("qq"));

    }

    /**
     * redis操作Map
     */
    public void testMap() {
        // -----添加数据----------
        Map<String, String> map = new HashMap<String, String>();
        map.put("name", "xinxin");
        map.put("age", "22");
        map.put("qq", "123456");
        jedis.hmset("user", map);
        // 取出user中的name，执行结果:[minxr]-->注意结果是一个泛型的List
        // 第一个参数是存入redis中map对象的key，后面跟的是放入map中的对象的key，后面的key可以跟多个，是可变参数
        List<String> rsmap = jedis.hmget("user", "name", "age", "qq");
        System.out.println(rsmap);

        // 删除map中的某个键值
        jedis.hdel("user", "age");
        System.out.println(jedis.hmget("user", "age")); // 因为删除了，所以返回的是null
        System.out.println(jedis.hlen("user")); // 返回key为user的键中存放的值的个数2
        System.out.println(jedis.exists("user"));// 是否存在key为user的记录 返回true
        System.out.println(jedis.hkeys("user"));// 返回map对象中的所有key
        System.out.println(jedis.hvals("user"));// 返回map对象中的所有value
        System.out.println("----------iterator------------");
        Iterator<String> iter = jedis.hkeys("user").iterator();
        while (iter.hasNext()) {
            String key = iter.next();
            System.out.println(key + ":" + jedis.hmget("user", key));
        }
    }

    /**
     * jedis操作List
     */
    public void testList() {
        // jedis 排序
        // 注意，此处的rpush和lpush是List的操作。是一个双向链表（但从表现来看的）
        // 开始前，先移除所有的内容
        jedis.del("java");
        System.out.println(jedis.lrange("java", 0, -1));
        // 先向key java framework中存放三条数据
        jedis.lpush("java", "spring");
        jedis.lpush("java", "struts");
        jedis.lpush("java", "hibernate");
        // 再取出所有数据jedis.lrange是按范围取出，
        // 第一个是key，第二个是起始位置，第三个是结束位置，jedis.llen获取长度 -1表示取得所有
        System.out.println(jedis.lrange("java", 0, -1));

        jedis.del("java");
        jedis.rpush("java", "spring");
        jedis.rpush("java", "struts");
        jedis.rpush("java", "hibernate");
        System.out.println(jedis.lrange("java", 0, -1));
    }

    /**
     * jedis操作Set
     */
    public void testSet() {
        // 添加
        jedis.sadd("employee", "liuling");
        jedis.sadd("employee", "xinxin");
        jedis.sadd("employee", "ling");
        jedis.sadd("employee", "zhangxinxin");
        jedis.sadd("employee", "who");

        System.out.println("before delete:" + jedis.smembers("employee"));// 获取所有加入的value
        // 移除noname
        jedis.srem("employee", "who");
        System.out.println("after delete:" + jedis.smembers("employee"));// 获取所有加入的value
        // 是否是user集合的元素
        System.out.println(jedis.sismember("employee", "who"));// 判断 who
        // 随机选择一个元素
        System.out.println(jedis.srandmember("employee"));
        System.out.println(jedis.scard("employee"));// 返回集合的元素个数
    }

    public void testZset() throws InterruptedException {

        jedis.zadd("zset", 2.0, "12");
        jedis.zadd("zset", 3.0, "13");
        // Map<String, Double> map = new HashMap<String, Double>();
        // map.put("13", score--);
        // System.out.println( jedis.zadd("zset", map));
        // 通过score获得元素的值
        Set<String> set = jedis.zrangeByScore("zset", 2, 3);
        System.out.println(set);
        System.out.println(jedis.zrevrank("zset", "13"));
        // 删除一个元素
        // jedis.zrem("zset", "12");
        System.out.println(jedis.zscore("zset", "12"));

    }

    // 实现一个阻塞式的消息队列
    @SuppressWarnings("deprecation")
    public void testBlockList() throws InterruptedException {
        // jedis.lpush("message", "123");
        // jedis.lpush("message", "678");
        // List<String> list1 = jedis.lrange("message", 0, -1);
        // System.out.println("list1=" + list1);
        // 0的话表示，只要获取不到数据就一直处于阻塞状态
        while (true) {
            List<String> list = jedis.blpop(0, "message");
            System.out.println(list);
            List<String> list2 = jedis.lrange("message", 0, -1);
            System.out.println("list2=" + list2);
        }
    }

    // hash可以存储对象的，一个个的field
    public void testHash() throws InterruptedException {
        List<String> list = jedis.hmget("myhash", "f1", "f2", "f3");
        System.out.println(list);
        Map<String, String> map = new HashMap<String, String>();
        map.put("name", "rondo");
        map.put("team", "celtics");
        jedis.hmset("myobject", map);
        User u1 = new User(21, "rondo");
        jedis.hset("player".getBytes(), "user".getBytes(), VoSerial
                .serival(u1));
        Set<String> keys = jedis.hkeys("player");
        System.out.println("keys=" + keys);
        List<String> vals = jedis.hvals("player");
        System.out.println("keys=" + vals);
    }

    // hash可以存储对象的，一个个的field
    public static void getMap(String mapName) {

        Iterator<String> iter = jedis.keys(mapName).iterator();
        while (iter.hasNext()) {
            String key = iter.next();
            System.out.println(key + ":" + jedis.hmget(mapName, key));
        }
    }

    public static void method1() {
        while (true) {
            try {
                //阻塞队列，1小时没有数据释放连接
                List<String> result = jedis.blpop(60 * 60 * 1, "321321321");
                if (result != null && result.size() > 1) {
                    String key = result.get(0);
                    //todo something
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                jedis.close();
            }
        }
    }

    //通过cluster来操作数据,删除
    public static void method2() {
        JedisCluster cluster = RedisDao.getJedisCluster();
        //hkeys和keys是不一样的，另外JedisCluster是不提供keys方法的，
        Set<String> set = cluster.hkeys("CPMP:ALL_CHARGER*");
        System.out.println("set=" + set);
        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            System.out.println("key=" + key);
        }


        //hkeys和keys是不一样的，另外JedisCluster是不提供keys方法的，
        //需要自己遍历redis节点实现
        set = cluster.smembers("CPMP:ALL_CHARGER");
        System.out.println("set=" + set);

        TreeSet<String> keys = new TreeSet<>();
        Map<String, JedisPool> clusterNodes = cluster.getClusterNodes();
        for (String k : clusterNodes.keySet()) {
            JedisPool jp = clusterNodes.get(k);
            Jedis connection = jp.getResource();
            try {
                keys.addAll(connection.keys("CPMP:ALL_CHARGER*"));
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                connection.close();//用完一定要close这个链接！！！
            }
        }
        System.out.println("keys=" + keys);
    }

    public static void main(String[] args) {
        method2();
    }
}
