# 性能压测工具

## ab
```
    ab -n100 -c5 http://www.baidu.com/
```
- Request per sencond 每秒处理的请求数量
- Time per request 第一个值为每次并发消耗的平均时间，第二个为每次请求所消耗的平均时间
- Complete requests 完成的请求数量
- Failed requests 失败的请求数量
