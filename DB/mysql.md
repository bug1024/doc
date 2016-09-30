# MySQL

- OLTP(Online Transaction Processing，联机事务处理)
- OLAP(Online Analysis Processing，联机分析处理)
- EXIST子查询中*可以放心使用，因为它只关心行是否存在，而不会去取各列的值
- InnoDB中每个页的大小为16KB
- Cardinality的统计是通过采样（sample）的方法来完成的
- T树不存放数据，只存放指针，可减少对内存的使用
