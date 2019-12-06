package com.example.thrift.server;

import com.example.thrift.RunTaskService;
import com.example.thrift.RunTaskServiceImpl;
import org.apache.spark.SparkConf;
import org.apache.spark.sql.SparkSession;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import scala.collection.Iterator;
import scala.collection.Map;

/**
 * @Description:
 * @user: yang
 * @Time: 2019/7/8  22:04
 */
public class RunTaskServer {
    public static void main(String[] args) {

        Logger logger = LoggerFactory.getLogger(RunTaskServer.class);
        int port = 12436;
        // *) 传输层(Transport), 设置监听端口为9000
        TServerSocket serverTransport = null;
        try {
            serverTransport = new TServerSocket(port);
        } catch (TTransportException e) {
            e.printStackTrace();
        }

        // *) 协议层
        TBinaryProtocol.Factory protocolFactory = new TBinaryProtocol.Factory(true, true);
        // *) 处理层(Processor)


        SparkConf sparkConf = new SparkConf();
        sparkConf.set("spark.serializer", "org.apache.spark.serializer.KryoSerializer");

        SparkSession sparkSession = SparkSession.builder().config(sparkConf).getOrCreate();

        RunTaskServiceImpl handler = new RunTaskServiceImpl();


        handler.setSparkSession(sparkSession);


        Map<String, String> map = sparkSession.conf().getAll();

        Iterator<String> it = map.keySet().iterator();
        String key = "";
        while (((Iterator) it).hasNext()) {
            key = it.next();
            System.out.println(key + " ==== " + map.get(key));
        }

        if (null == sparkSession) {
            System.out.println("sparkSession is null");
        }

        RunTaskService.Processor processor =
                new RunTaskService.Processor(handler);

        // *) 服务层(Server)
        TServer server = new TThreadPoolServer(
                new TThreadPoolServer.Args(serverTransport)
                        .protocolFactory(protocolFactory)
                        .processor(processor)
        );
        if (null == server) {
            System.out.println("server is null");
        }
        // *) 启动监听服务
        server.serve();
        logger.info("服务已经启动，端口为：" + port);


    }
}
