# RabbitMQ

MQ全称为Message Queue, 消息队列（MQ）是一种应用程序对应用程序的通信方法。应用程序通过读写出入队列的消息（针对应用程序的数据）来通信，而无需专用连接来链接它们。消息传递指的是程序之间通过在消息中发送数据进行通信，而不是通过直接调用彼此来通信，直接调用通常是用于诸如远程过程调用的技术。排队指的是应用程序通过 队列来通信。队列的使用除去了接收和发送应用程序同时执行的要求。

## MQ特点

MQ是消费-生产者模型的一个典型的代表，一端往消息队列中不断写入消息，而另一端则可以读取或者订阅队列中的消息。MQ和JMS类似，但不同的是JMS是SUN JAVA消息中间件服务的一个标准和API定义，而MQ则是遵循了AMQP协议的具体实现和产品。

## 安装
 * 安装ERLANG ： 首先，因为RabbitMQ由ERLANG实现，下载ERLANG源码，即安装基于erlang语言支持的OTP软件，并设置其环境变量。
 
 * 安装RabbitMQ : 下载rabbitMQ软件进行安装（安装过程都是下一步），设置环境变量。
 
 * 启动RabbitMQ : 
       
       * 找到你安装rabbitMQ的路径，然后切换到sbin的文件夹
       * 输入rabbitmq-plugins enable rabbitmq_management命令来启动监控管理器
       * 然后在浏览器输入http:localhost:15672 用户名和密码默认都为guest。
       
## 几个概念
+ Exchange:交换机，决定了消息路由规则；
+ Queue:消息队列；
+ Channel:进行消息读写的通道；
+ Bind:绑定了Queue和Exchange，意即为符合什么样路由规则的消息，将会放置入哪一个消息队列；

## 消息持久
```
1）将交换机置为可持久；
2) 将通道置为可持久
3) 消息发送时设置可持久。

当我们“生产”了一条可持久化的消息，尝试中断MQ服务，启动消费者获取消息，消息依然能够恢复。相反，则抛出异常。
```

## 入门

### 基本概念
RabbitMQ是流行的开源消息队列系统，用erlang语言开发。RabbitMQ是AMQP（高级消息队列协议）的标准实现。如果不熟悉AMQP，直接看RabbitMQ的文档会比较困难。不过它也只有几个关键概念，这里简单介绍。
RabbitMQ的结构图如下：

### 几个概念说明：
```
    Broker：简单来说就是消息队列服务器实体。
　　Exchange：消息交换机，它指定消息按什么规则，路由到哪个队列。
　　Queue：消息队列载体，每个消息都会被投入到一个或多个队列。
　　Binding：绑定，它的作用就是把exchange和queue按照路由规则绑定起来。
　　Routing Key：路由关键字，exchange根据这个关键字进行消息投递。
　　vhost：虚拟主机，一个broker里可以开设多个vhost，用作不同用户的权限分离。
　　producer：消息生产者，就是投递消息的程序。
　　consumer：消息消费者，就是接受消息的程序。
　　channel：消息通道，在客户端的每个连接里，可建立多个channel，每个channel代表一个会话任务。
```
### 消息队列的使用过程大概如下：
```

       （1）客户端连接到消息队列服务器，打开一个channel。
　　（2）客户端声明一个exchange，并设置相关属性。
　　（3）客户端声明一个queue，并设置相关属性。
　　（4）客户端使用routing key，在exchange和queue之间建立好绑定关系。
　　（5）客户端投递消息到exchange。
exchange接收到消息后，就根据消息的key和已经设置的binding，进行消息路由，将消息投递到一个或多个队列里。
exchange也有几个类型，完全根据key进行投递的叫做Direct交换机，例如，绑定时设置了routing key为”abc”，那么客户端提交的消息，只有设置了key为”abc”的才会投递到队列。对key进行模式匹配后进行投递的叫做Topic交换机，符号”#”匹配一个或多个词，符号”*”匹配正好一个词。例如”abc.#”匹配”abc.def.ghi”，”abc.*”只匹配”abc.def”。还有一种不需要key的，叫做Fanout交换机，它采取广播模式，一个消息进来时，投递到与该交换机绑定的所有队列。
RabbitMQ支持消息的持久化，也就是数据写在磁盘上，为了数据安全考虑，我想大多数用户都会选择持久化。消息队列持久化包括3个部分：
　　（1）exchange持久化，在声明时指定durable => 1
　　（2）queue持久化，在声明时指定durable => 1
　　（3）消息持久化，在投递时指定delivery_mode => 2（1是非持久化）
如果exchange和queue都是持久化的，那么它们之间的binding也是持久化的。如果exchange和queue两者之间有一个持久化，一个非持久化，就不允许建立绑定。


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