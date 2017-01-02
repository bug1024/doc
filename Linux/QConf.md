# QConf

360开源的一款分布式配置管理工具

## 相关链接
 - [原理](http://catkang.github.io/2015/06/23/qconf.html)
 - [GitHub](https://github.com/Qihoo360/QConf/blob/master/README_ZH.md)

## 特点
 - 一处修改，所有机器实时同步更新
 - 高效读取配置
 - 安装部署方便，使用简单
 - 服务器宕机、网络中断、集群迁移等异常情况对用户透明
 - 支持c/c++、shell、php、python、lua、java、go、node 等语言

## 服务端
 - QConf使用ZooKeeper集群作为服务端提供服务
 - 单条配置直接存储在一个ZNode上
 - 利用ZooKeeper的Watch监听功能实现配置变化时对客户端的及时通知

## 客户端
 - 获取：查共享内存，无记录则向消息队列中加入请求，agent感知消息队列，agent查ZK并更新共享缓存
 - 更新：ZK通知agent变更，agent从ZK中获取新值同时更新共享内存
 - 设计目标：降低与ZooKeeper的链接数，本地缓存，及时更新，容错
 - 特点：无锁，单点写，父子进程keepalive，落盘数据维护

## 管理端
 - 业务修改配置的管理界面
