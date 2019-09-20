include "flow.thrift"

service RunTaskService {//定义接口的方法
 string runKpitask(flow.Task task);
}