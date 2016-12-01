
### PHP
 - PHP7
 - 启动-终止模型 Module Initializatin/Request Initialization/Request Shutdown/Module Shutdown
 - 扩展开发 ext_skel conifg.m4
 - 版本特性 5.3命名空间和匿名函数 5.4traits和[] 5.5yied关键字 异常finally opcache 5.6变长参数... phpdbg扩展 7??操作符 变量类型声明 优化zend

### mysql
 - 索引 explain用法 最左前缀匹配 Cardinality
 - 慢查询分析工具mysqldumpslow与pt-query-digest
 - 分表分区 分表方案 跨表跨库查询 MyCat中间件
 - 主从 binlog原理
 - 备份

## Nginx
 - 原理 master-worker
 - 配置

### redis
 - 场景 计数器 排行榜 队列
 - 持久化 rdb aof

### memcached
 - [一致性哈希](http://coderroc.com/article/%E7%9F%A5%E8%AF%86%E8%AE%B0%E5%BD%95/Consistent-Hashing.html)
 - [常见问题](http://kb.cnblogs.com/page/69074/)

### MongoDB
 - 场景 日志
 - bson

### Swoole
 - task底层使用Unix Socket管道通信，是全内存的，没有IO消耗
 - IO复用异步非阻塞程序使用经典的Reactor模型，本身不处理任何数据收发，只是可以监视一个socket句柄的事件变化，基于epoll

### 全文索引
 - Sphinx build/merge静态索引 实时性差但性能极佳
 - Solr 索引格式更多 建立索引时IO阻塞导致搜索性能下降 ZK进行分布式管理
 - ElasticSearch 实时 自带分布式协调管理功能 主从分片 节点 逆向索引

### 消息队列
 - RabbitMQ 可靠消费具备消息消费确认
 - [原理](http://tech.meituan.com/mq-design.html)

### 操作系统
 - 进程与线程
 - 同步异步阻塞非阻塞
 - select poll epoll
 - 工具：crontab awk strace tcpdump

### 算法
 - 排序

### 设计模式
 - 单例
 - 适配器
 - 责任链
 - 观察者

### 检索系统
 - 架构 MySQL + Canal + RabbitMQ + PHP + Solr

### App服务端
 - HTTPS
 - OAuth2 client_id + client_secret + response_type获取授权码 使用授权码获取access_token

### 职业规划
 - 架构师

