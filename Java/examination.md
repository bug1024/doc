# 分布式消息队列
* 为什么使用消息队列？有什么优点和缺点？
    * 业务解耦/最终一致性/广播/错峰流控
* 如何保证消息队列高可用？保证不被重复消费？
    * 消息队列的高可用，只要保证broker接受消息和确认消息的接口是幂等的，并且consumer的几台机器处理消息是幂等的
    * 关于消息的绝对顺序执行，主动去分配队列，单个消费者
    * 关于消息的重复消费，Consumer保证无状态和幂等性，如果保证不了就用map记录任务状态
* kafka/activemq/rabbitmq/rocketmq各有什么优缺点？
    * 服务端处理同步发送的性能上，Kafka > RocketMQ > RabbitMQ
    * Kafka的队列模式保证了写磁盘的过程是线性IO，所以性能比较高，用于日志系统
    * RocketMQ的消息写入内存后即返回ack，由单独的线程专门做刷盘的操作，所有的消息均是顺序写文件
    * RabbitMQ实现的AMQP协议比较重量级，为了保证消息的可靠性在吞吐量上做了取舍
    * http://dbaplus.cn/news-21-1123-1.html
* 如果让你写一个消息队列，该如何设计？总体思路是怎样的？
    * 设计消息队列的整体思路是先build一个整体的数据流，例如producer发送给broker，broker发送给consumer，consumer回复消费确认，broker删除/备份消息等。
    * 利用RPC将数据流串起来。然后考虑RPC的高可用性，尽量做到无状态，方便水平扩展
    * 之后考虑如何承载消息堆积，然后在合适的时机投递消息，而处理堆积的最佳方式，就是存储，存储的选型需要综合考虑性能/可靠性和开发维护成本等诸多因素
    * 为了实现广播功能，我们必须要维护消费关系，可以利用zk/config server等保存消费关系
    * 在完成了上述几个功能后，消息队列基本就实现了。然后我们可以考虑一些高级特性，如可靠投递，事务特性，性能优化等
    * https://tech.meituan.com/mq-design.html

# 分布式搜索引擎
* es的工作过程实现是怎样的？如何实现分布式？
* es在数据量很大的情况下如何提高查询效率？
    * SSD盘
    * 结合自己的业务实践，使用多个集群，每个集群使用不同的routing，用户是一个routing维度
    * 适当增加分片数量，提升系统的分布水平
* es查询是一个怎样的过程？底层lucence原理是怎样的？倒排序索引是什么？
    * 倒排索引由在文档中出现的唯一的单词列表，以及对于每个单词在文档中的位置组成
* es和mongodb有什么区别？场景又是如何的？
    * MongoDB超过Elasticsearch的地方在于其对于服务器端js脚本的支持、聚合的管道、MapReduce的支持和capped collections，这就保证了MongoDB可以对选定的数据执行任意类型的计算或者转换的终极的灵活性

# 高并发高可用架构设计
* 如何设计一个高可用高并发系统？
    * 无状态，便于扩展，如果有状态则用配置中心实现无状态
    * 拆分，系统／功能／读写／模块
    * 分库分表
    * 消息队列
    * 缓存／ 降级／限流／可回滚
* 如何限流？工作中怎么实现的？说一下具体实现？
    * 限制总并发数（比如数据库连接池、线程池）
    * 限制瞬时并发数（如nginx的limit_conn模块，用来限制瞬时并发连接数）
    * 限制时间窗口内的平均速率（如Guava的RateLimiter、nginx的limit_req模块，限制每秒的平均速率）
    * 其他还有如限制远程接口调用速率、限制MQ的消费速率
    * 令牌桶算法和漏桶算法
    * 分布式限流使用lua + redis
* 缓存如何使用的？使用不当会造成什么后果？
    * 如果使用不当会导致数据一致性问题、缓存被穿透导致应用雪崩等
* 如何熔断？熔断框架又哪些？具体原理是怎样的？
    * 熔断器Hystrix，Hystrix的Metrics中保存了当前服务的健康状况, Hystrix在1.5版本开始使用RxJava的Observable.window()实现滑动窗口来记录当前时间窗的各种事件
* 如何降级？如何进行系统拆分？如何做数据库拆分？

# 通信协议
* TCP/IP 协议是怎样的？
    * 是一系列网络协议的总和，是构成网络通信的核心骨架，它定义了电子设备如何连入因特网，以及数据如何在它们之间进行传输
    * TCP/IP协议采用4层结构，分别是应用层、传输层、网络层和链路层
* http工作流程？http1.0 1.1 2.0具体有哪些区别?
    * 域名解析 -> tcp三次握手建立链接 -> 响应http请求 报文（状态码/报头/正文）->  解析报文内容
    * 1.0 链接无法复用，即不支持持久链接；线头阻塞（Head of Line (HOL) Blocking）
    * 1.1 支持持久链接（在request和response中的header中的connection是close或者Keep-Alive进行控制）;支持http管道
    * 2.0把解决性能问题的方案内置在了传输层，通过多路复用来减少延迟，通过压缩 HTTP首部降低开销，同时增加请求优先级和服务器端推送的功能
* TCP三次次握手和四次挥手？画一下流程图
    * 三次握手建立链接：SYN=i -> ACK=i+1 SYN=j -> ACK=j+1
    * 四次挥手断开链接：FIN=i -> ACK=i+1, FIN=j -> ACK=j+1
    * 关闭连接时，当收到对方的FIN，它仅仅表示对方没有数据发送给你了，但未必你所有的数据都全部发送给对方了，所以你可能未必会马上会关闭SOCKET，即你可能还需要发送一些数据给对方之后，再发送FIN报文给对方来表示你同意现在可以关闭连接了，所以它这里的ACK报文和FIN报文多数情况下都是分开发送的
* 画一下https的工作流程？具体如何实现的？如何防止被抓包？

# 算法
* 一个文件有45亿数字，如何去重？如何找到最大那个数？

# 数据结构
* 二叉树和红黑树

# 数据库
* 使用MySQL索引都有哪些原则？索引是什么数据结构？B+tree和B tree什么区别？
    * 最左前缀匹配
    * 唯一索引
    * 联合索引
    * B+tree是变种，每个节点的指针上限为2d而不是2d+1，内节点不存储data，只存储key，叶子节点不存储指针
    * B+tree的结构设计具体原因与外存储器原理及计算机存取原理有关，局部性原理与磁盘预读
* MySQL存储引擎有哪些？有什么区别？
    * MyISAM
    * InnoDB
    * MEMORY
* 设计高并发系统数据库层面该怎么设计？数据库锁有哪些类型？如何实现？
    * 行锁
    * 页锁
    * 表锁
    * MyISAM中是不会产生死锁的，因为MyISAM总是一次性获得所需的全部锁，要么全部满足，要么全部等待
    * 在MySQL中，行级锁并不是直接锁记录，而是锁索引
* 数据库事务有哪些？
    * ACID 原子／一致性／隔离性／持久性
    * 隔离级别

# 分库分表
* 如何设计可以动态扩容缩容的分库分表方案
* 用过哪些分库分表中间件？有什么优缺点？底层是如何实现的？
    * Mycat
    * cobar
* 现在有一个未分库分表的系统，后期要进行分库分表，如何设计让未分库分表的系统动态切换到分库分表系统上？
* 分布式事务了解吗？如何解决？TCC？如果出现网络原因，网络连不通怎么办？
* 为什么要分库分表？
* 分布式寻址方式有哪些算法？知道一致性hash吗？简单写下实现代码？如果使用userId取模分片，那么如何查一段时间里的数据？
* 如何解决分库分表主键问题？有什么实现方案？
    * id生成器

# 分布式缓存
* redis和memcached区别？为什么单线程的redis比多线程的memcached效率更高？
    * memcached是多线程模型，能完全使用到多核，底层依赖的是通用的libevent库。为了保证线程安全，内部有大量的锁的代码
    * memcached指令执行次数多，线程切换次数多耗时也多，需要获取锁、释放锁的次数更多，如果锁冲突的机率高，线程等待时间变多，等于变相减小多线程处理能力
* redis数据类型有哪些？使用场景如何？
    * String Hash List Set SortedSet
* redis主从复制如何实现？集群模式如何实现？redis的key是如何寻址的？
     * slave向master发送sync指令，master接收到时调用bgsave指令fork一个子进程进行持久化工作，该期间master的写指令都缓存在内存中
     * bgsave指令完成后master会将rdb文件发送给slave，slave接收到后将其存在磁盘上，然后读入内存，这个动作完成后master会将这段时间缓存的写指令再以redis协议的格式发送给slave
     * 2.8版本引入增量同步机制
     * 集群方案：Redis官方集群方案RedisCluster 服务端sharding RedisProxy（Codis&Twemproxy）
* redis如何设计分布式锁？使用zk可以吗？如何实现？两种效率谁更高？
    * ZK分分布式锁主要得益于ZooKeeper为我们保证了数据的强一致性，zookeeper的znode节点创建的唯一性和递增性能保证所有来抢锁的worker的原子性
    * ZK每次进行锁操作前都要创建若干节点，完成后要释放节点，会浪费很多时间，而Redis只是简单的数据操作，没有这个问题
```
    # 加锁
    SET resource_name my_random_value NX PX 30000
    # 解锁，使用lua
    if redis.call("get", KEYS[1]) == ARGV[1] then
        return redis.call("del", KEYS[1])
    else
        return 0
    end
```
* redis持久化？有什么优缺点？具体底层如何实现？
    * rdb
    * aof
* redis过期策略有哪些？LRU？代码实现？
    * 被动删除 get/del时判断是否过期
    * 主动删除 定时清除
    * 超过内存触发主动删除

# 分布式服务框架
* 说一下dubbo的实现过程？如果注册中心挂了，可以继续通信吗？
    * 注册中心挂了可以继续通信的，因为消费者本地有一个生产者的列表，但无法从注册中心去同步最新的服务列表，短期的注册中心挂掉是不要紧的，但一定要尽快修复
* zk原理？zk应用场景？paxos算法了解吗？说一下原理和实现
    * Zookeeper 的核心是广播，这个机制保证了各个Server之间的同步。实现这个机制的协议叫做Zab协议
* dubbo支持哪些序列化协议？hessian？hessian数据结构是怎样的？PB知道吗？为啥PB效率更高？
    * dubbo序列化
    * hessian序列化
    * json序列化
    * Kryo
    * PB序列化&反序列化简单只需要简单的数学运算 = 位移等等
    * PB数据压缩效果好，采用了独特的编码方式，如Varint、Zigzag编码方式等等，采用T-L-V的数据存储方式减少了分隔符的使用
* 了解netty吗？netty可以干嘛？NIO/BIO/AIO都是什么？有什么区别？
    * 本质：JBoss做的一个Jar包
    * 目的：快速开发高性能、高可靠性的网络服务器和客户端程序
    * 优点：提供异步的、事件驱动的网络应用程序框架和工具
    * 通俗的说：一个好使的处理Socket的东西
    * 应用：系统采用分布式部署，各个节点之间需要远程服务调用，高性能的 RPC 框架必不可少，Netty 作为异步高性能的通信框架，往往作为基础通信组件被这些 RPC 框架使用
    * Dubbo/RocketMQ都使用netty作为通信组件
    * BIO （Blocking I/O）：同步阻塞I/O模式，数据的读取写入必须阻塞在一个线程内等待其完成。这里使用那个经典的烧开水例子，这里假设一个烧开水的场景，有一排水壶在烧开水，BIO的工作模式就是， 叫一个线程停留在一个水壶那，直到这个水壶烧开，才去处理下一个水壶。但是实际上线程在等待水壶烧开的时间段什么都没有做
    * NIO （New I/O）：同时支持阻塞与非阻塞模式，但这里我们以其同步非阻塞I/O模式来说明，那么什么叫做同步非阻塞？如果还拿烧开水来说，NIO的做法是叫一个线程不断的轮询每个水壶的状态，看看是否有水壶的状态发生了改变，从而进行下一步的操作
    * AIO （Asynchronous I/O）：异步非阻塞I/O模型。异步非阻塞与同步非阻塞的区别在哪里？异步非阻塞无需一个线程去轮询所有IO操作的状态改变，在相应的状态改变后，系统会通知对应的线程来处理。对应到烧开水中就是，为每个水壶上面装了一个开关，水烧开之后，水壶会自动通知我水烧开了
* duubo负载均衡策略和高可用策略有哪些？动态代理策略呢？
    * 随机／轮询／最少使用／一致性hash
* 为何需要进行系统拆分？拆分不用dubbo可以实现吗？dubbo和thrift区别？
    * 性能考虑：一个大的系统，往往其中的某个部分，承担了很大的并发压力，或者逻辑运算特别复杂，因此成为系统的性能瓶颈 ，这时就可以考虑将这部分独立出来，从而实现单独部署，再通过负载均衡等手段，来解决性能的问题 
    * 逻辑清晰：根据业务上、流程上的不同环节，将整个系统拆分为不同的子系统，在逻辑上会更加清晰，从而更容易在架构上说清楚，或者进一步做优化
    * 分而治之：减少系统之间的耦合，一个模块出问题，不影响其他模块的运行，同时在开发时可以减少代码冲突
    * 流量限定：将大流量的业务单独剥离出来，提升系统的可控制性
    * 便于管理：随着业务的发展，模块之间的耦合性越来越强 ；开发人员越来越多，相互之间代码版本也难以管理

# java基础
* hashcode相等的两个类一定相等吗？equals呢？相反呢？
    * equals()相等的两个对象，hashCode()一定相等，hashCode()相等的话，并一定是相等的两个对象，即equals并一定相等
    * equals比较的是两个对象，而hasCode是对象的属性，对象相等那么其属性一定相等，相反其属性相等但是并不一定是同一个对象
* 集合框架？
    * Collection Map List Set Queue
* hashmap hashtable底层实现区别？hashtable和concurrenthashtable呢？
    * HashMap几乎可以等价于Hashtable，除了HashMap是非synchronized的，并可以接受null(HashMap可以接受为null的键值(key)和值(value)，而Hashtable则不行)
    * HashMap是非synchronized，而Hashtable是synchronized，这意味着Hashtable是线程安全的，多个线程可以共享一个Hashtable；而如果没有正确的同步的话，多个线程是不能共享HashMap的。
    * 另一个区别是HashMap的迭代器(Iterator)是fail-fast迭代器，而Hashtable的enumerator迭代器不是fail-fast的。所以当有其它线程改变了HashMap的结构（增加或者移除元素），将会抛出ConcurrentModificationException，但迭代器本身的remove()方法移除元素则不会抛出ConcurrentModificationException异常。但这并不是一个一定发生的行为，要看JVM。这条同样也是Enumeration和Iterator的区别
    * 由于Hashtable是线程安全的也是synchronized，所以在单线程环境下它比HashMap要慢。如果你不需要同步，只需要单一线程，那么使用HashMap性能要好过Hashtable
    * HashMap不能保证随着时间的推移Map中的元素次序是不变的
    * Java 5提供了ConcurrentHashMap，它是HashTable的替代，比HashTable的扩展性更好
* hashmap和treemap区别？底层数据结构是怎样的？
    * treemap有序的，底层实现是红黑树
* 线程池用过吗？都有什么参数？底层如何实现的？
    * java.util.concurrent.Executors
* synchnized和lock区别？synchnized何时是对象锁？什么时候是全局锁？为什么？
* ThreadLocal是什么？底层如何实现？写一个例子？
* volatile工作原理？
    * 可见性
    * 原子性
    * 顺序性
    * 内存屏障
* cas知道吗？如何实现？
* 单例模式？请用4种写法实现

# JVM
* JMV内存模型？垃圾回收器？
* 线上频繁full gc如何处理？cpu使用率过高怎么办？如何定位问题？如何解决？思路和方法怎样的？
* 字节码知道吗？都有哪些？
    * 魔数/版本号/常量池/访问标志/类索引/父类索引/接口索引/字段表集合/方法/属性
* Integer x=5,int y=5,比较x==y都经过哪些步骤？
* 类加载机制？都有哪些加载器？都加载哪些文件？手写类加载demo？
* osgi是什么？如何实现的？
    * OSGi(Open Service Gateway Initiative)技术是面向Java的动态模型系统
* 做过哪些JVM优化？使用哪些方法？达到什么效果？
* class.forName("java.lang.String")和String.class.getClassLoader.loadClass("java.lang.String")区别？
    * Class.forName是从指定的classloader中装载类,如果没有指定,也就是一个参数的时候,是从装载当前对象实例所在的classloader中装载类
    * ClassLoader的实例调用loadclass方法,是指从当前ClassLoader实例中调用类,而这个实例与装载当前所在类实例的Classloader也许不是同一个
    * 说白了就是他们实现装载的时候，使用的类装载器的指定是不同的

# Spring
* Spring有哪些机制？AOP底层如何实现的？IOC呢？
    * IOC AOP
    * AOP底层实现：JDK动态代理/CGLIB代理
    * Bean/Context/Core组件
    * [Spring常见问题](http://www.importnew.com/15851.html)
* cglib知道吗？和jdk动态代理区别是什么？手写一个jdk动态代理？
    * 静态代理：事先写好代理对象类，在程序发布前就已经存在了
    * 动态代理：应用程序发布后，通过动态创建代理对象
    * JDK动态代理只能对实现了接口的类生成代理，而不能针对类
    * CGLIB是针对类实现代理，主要是对指定的类生成一个子类，覆盖其中的方法
    * 因为是继承，所以该类或方法最好不要声明成final，final可以阻止继承和多态
```java
    // cglib
    public Object createProxyObject(Object obj) {
        this.targetObject = obj;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(obj.getClass());
        enhancer.setCallback(this);
        Object proxyObj = enhancer.create();
        return proxyObj;
    }
    // JDK
    public Object newProxy(Object targetObject) {
        this.targetObject = targetObject;
        return Proxy.newProxyInstance(targetObject.getClass().getClassLoader(),
        targetObject.getClass().getInterfaces(), this);
    }
```
