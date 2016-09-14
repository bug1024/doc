# 大型网站架构

## 海量数据存储
 - 分库表基础上，单master多slave横向扩展
 - master和slave通过binlog近乎实时同步，任意slave可为master实时备份
 - 主业务和辅业务模块数据库分离
 - 多地部署，一处写入多出读取，保证就近读取
