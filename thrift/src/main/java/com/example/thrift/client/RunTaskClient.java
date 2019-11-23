package com.example.thrift.client;

import com.example.thrift.RunTaskService;
import com.example.thrift.Task;
import com.example.thrift.Type;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

/**
 * @Description: runtask.thrift  just test
 * @user: yang
 * @Time: 2019/7/8  21:55
 */
public class RunTaskClient {

    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println(" 请输入要计算的内容：");
        }

        // *) 传输层
        TTransport transport = new TSocket("localhost", 12436);
        try {
            transport.open();
            // *) 协议层, 与服务端对应
            TProtocol protocol = new TBinaryProtocol(transport);
            // *) 创建RPC客户端
            RunTaskService.Client client = new RunTaskService.Client(protocol);
            // *) 调用服务

            Task task = new Task();
            task.setTaskType(Type.KPI);
            task.setContent(args[0]);

            System.out.println(client.runKpitask(task));
            // *) 关闭句柄
            transport.close();

        } catch (TTransportException e) {
            e.printStackTrace();
        } catch (TException e) {
            e.printStackTrace();
        }


    }
}
