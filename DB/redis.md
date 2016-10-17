# Redis

## 特点
 - 高性能Key-Value存储
 - 丰富的数据结构：string、list、hash、set、zset、hypeloglog
 - 支持数据过期：主动过期+惰性过期
 - 支持多种LRU策略：volatile-lru、volatile-ttl 等
 - 内存管理：tcmaloc、jemalloc
 - 内存存储+磁盘持久化: rdb、aof
 - 支持主从复制
 - 单线程

## 配置

aof配置
```
    appendonly yes 是否打开aof日志功能
    appendfsync always 每一个命令都立刻同步到aof,安全但是速度慢
    appendfsync everysec 折衷方案每秒一次,一般选用这种。
    appendfsync no 交给操作系统,由操作系统判断缓冲区大小，同意写入aof中,频率低速度快。
    no-appendfsync-on-rewrite yes 正在导出rdb文件，是否要停止aof
    auto-aof-rewrite-percentage 100 aof文件比起上次重写时的大小，增长率是百分之百时重写。
    auto-aof-rewrite-min-size 64mb 至少超过64m重写。

```
rdb配置
```
    save 10 100 10s后如果至少100个key发生变化则生成rdb
    save 100 50 可以多个，任意一个条件满足即可触发
    dbfilename dump.rdb 指定rdb保存到本地数据库文件名
    stop-writes-on-bgsave-error yes 当硬盘因为权限等原因无法写入时，停止写入
    rdbchecksum yes 对rdb文件进行校验

```
