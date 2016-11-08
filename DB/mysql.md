# MySQL

- OLTP(Online Transaction Processing，联机事务处理)
- OLAP(Online Analysis Processing，联机分析处理)
- EXIST子查询中*可以放心使用，因为它只关心行是否存在，而不会去取各列的值
- InnoDB中每个页的大小为16KB
- Cardinality的统计是通过采样（sample）的方法来完成的
- T树不存放数据，只存放指针，可减少对内存的使用


### 解决双主同步id冲突的方案有两种：
 - 一个是双主使用不同的初始值，相同的步长来生成id，一个库从0开始（生成02468），一个库从1开始（生成13579），步长都为2，这样两边同步数据就不会冲突。
 - 另一个方式是不要使用数据库的auto-increment-id，而由业务层来保证生成的id不冲突。

### 关于为什么定义不使用Null的原因
 - 浪费存储空间，因为InnoDB需要有额外一个字节存储
 - 表内默认值Null过多会影响优化器选择执行计划
