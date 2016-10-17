# MySQL优化

## 优化方面
 - 索引以及SQL语句
 - 表结构
 - 系统配置
 - 硬件

## 慢查询分析工具
 - mysqldumpslow
 - pt-query-digest

## 最佳实践
 - 避免使用NULL字段 NULL字段很难查询优化 NULL字段的索引需要额外空间 NULL字段的复合索引无效
