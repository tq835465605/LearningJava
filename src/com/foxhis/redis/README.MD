## Redis 

Redis是完全开源免费的，遵守BSD协议，是一个高性能的key-value数据库。

Redis 与其他 key - value 缓存产品有以下三个特点：

* Redis支持数据的持久化，可以将内存中的数据保存在磁盘中，重启的时候可以再次加载进行使用。      
* Redis不仅仅支持简单的key-value类型的数据，同时还提供list，set，zset，hash等数据结构的存储。
* Redis支持数据的备份，即master-slave模式的数据备份。

###  Redis安装：

下载地址：<https://github.com/MSOpenTech/redis/releases>

Redis 支持 32 位和 64 位。这个需要根据你系统平台的实际情况选择，现在redis不支持32位下载，需要从64位的source重新vs编译获得.

打开一个 cmd 窗口 使用 cd 命令切换目录到 C:\redis 运行：

```
redis-server.exe redis.windows.conf
```

切换到 redis 目录下运行:
```
redis-cli.exe -h 127.0.0.1 -p 6379
```

设置键值对:
```
set myKey abc
```
取出键值对:
```
get myKey
```
## Redis 数据类型

Redis支持五种数据类型：string（字符串），hash（哈希），list（列表），set（集合）及zset(sorted set：有序集合)。

String

实例
```
redis 127.0.0.1:6379> SET name "runoob"
OK
redis 127.0.0.1:6379> GET name
"runoob"
```
在以上实例中我们使用了 Redis 的 SET 和 GET 命令。键为 name，对应的值为 runoob。

注意：一个键最大能存储512MB。
