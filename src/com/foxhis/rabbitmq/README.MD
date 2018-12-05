## RabbitMQ

MQ全称为Message Queue, 消息队列（MQ）是一种应用程序对应用程序的通信方法。应用程序通过读写出入队列的消息（针对应用程序的数据）来通信，而无需专用连接来链接它们。消息传递指的是程序之间通过在消息中发送数据进行通信，而不是通过直接调用彼此来通信，直接调用通常是用于诸如远程过程调用的技术。排队指的是应用程序通过 队列来通信。队列的使用除去了接收和发送应用程序同时执行的要求。

### MQ特点

MQ是消费-生产者模型的一个典型的代表，一端往消息队列中不断写入消息，而另一端则可以读取或者订阅队列中的消息。MQ和JMS类似，但不同的是JMS是SUN JAVA消息中间件服务的一个标准和API定义，而MQ则是遵循了AMQP协议的具体实现和产品。

### 安装
 * 安装ERLANG ： 首先，因为RabbitMQ由ERLANG实现，下载ERLANG源码，即安装基于erlang语言支持的OTP软件，并设置其环境变量。
 
 * 安装RabbitMQ : 下载rabbitMQ软件进行安装（安装过程都是下一步），设置环境变量。
 
 * 启动RabbitMQ : 
       ```
       * 找到你安装rabbitMQ的路径，然后切换到sbin的文件夹
       * 输入rabbitmq-plugins enable rabbitmq_management命令来启动监控管理器
       * 然后在浏览器输入http:localhost:15672 用户名和密码默认都为guest。
       ```
### rabbitMQ常用的命令
```
启动监控管理器：rabbitmq-plugins enable rabbitmq_management
关闭监控管理器：rabbitmq-plugins disable rabbitmq_management
启动rabbitmq：rabbitmq-service start
关闭rabbitmq：rabbitmq-service stop
查看所有的队列：rabbitmqctl list_queues
清除所有的队列：rabbitmqctl reset
关闭应用：rabbitmqctl stop_app
启动应用：rabbitmqctl start_app
用户和权限设置（后面用处）

添加用户：rabbitmqctl add_user username password
分配角色：rabbitmqctl set_user_tags username administrator
新增虚拟主机：rabbitmqctl add_vhost  vhost_name
将新虚拟主机授权给新用户：rabbitmqctl set_permissions -p vhost_name username '.*' '.*' '.*'
角色说明

none  最小权限角色
management 管理员角色
policymaker   决策者
monitoring  监控
administrator  超级管理员 
```