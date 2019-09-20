include "type.thrift"
struct Task{
  1: required string content, //设置一个Task对象
  2: type.Type  taskType //
}