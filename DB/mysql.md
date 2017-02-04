# MySQL

- OLTP(Online Transaction Processing，联机事务处理)
- OLAP(Online Analysis Processing，联机分析处理)
- EXIST子查询中`*`可以放心使用，因为它只关心行是否存在，而不会去取各列的值
- InnoDB中每个页的大小为16KB
- Cardinality的统计是通过采样（sample）的方法来完成的
- T树不存放数据，只存放指针，可减少对内存的使用


### 解决双主同步id冲突的方案有两种：
 - 一个是双主使用不同的初始值，相同的步长来生成id，一个库从0开始（生成02468），一个库从1开始（生成13579），步长都为2，这样两边同步数据就不会冲突。
 - 另一个方式是不要使用数据库的auto-increment-id，而由业务层来保证生成的id不冲突。

### 关于为什么定义不使用Null的原因
 - 浪费存储空间，因为InnoDB需要有额外一个字节存储
 - 表内默认值Null过多会影响优化器选择执行计划

### 锁
 - MyISAM表锁是deadlock free的，这是因为MyISAM总是一次获得所需的全部锁，要么全部满足，要么等待，因此不会出现死锁。但在InnoDB中，除单个SQL组成的事务外，锁是逐步获得的，这就决定了在InnoDB中发生死锁
 - MyISAM表锁 InnoDB行锁

### 线程池
 - MySQL5.6出现以前，MySQL处理连接的方式是One-Connection-Per-Thread
 - 适用于有大量短查询的业务场景

### 缓冲池
 - 基于存储引擎的，也就是说每个存储引擎都有自己的缓冲池
 - MyISAM缓存池大小key_buffer_size
 - InnoDB缓存池大小innodb_buffer_pool_size

### 优化器
 - 通过EXPLAIN的rows值预估查询可能得到的行，如果大于某个值则选择全表扫描而不走索引，优化器不一定就是合理的

### 索引
 - 条件：高选择性，并且取表中少部分数据
 - 顺序读，随机读，预读取
