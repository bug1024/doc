
# 个人信息
 - 王煜/男/1990
 - 电子邮箱：bug1024.com@gmail.com
 - 教育经历：本科/福建师范大学/网络工程
 - 工作年限：3年
 - 英语水平：CET-6，纯英文阅读无障碍
 - 期望职位：PHP开发工程师
 - 自我总结：合格PHPer，半个JSer，Lua爱好者，vim控

---

# 工作经历

## 273二手车交易网（ 2014年7月至今 ）
 创办于2003年，总部位于福建福州，是国内领先的二手车O2O交易平台，业务体系包括连锁店经纪业务以及配套的专业二手车检测、安全支付、售后保障、二手车金融等服务。

### 岗位与职责
 外网组技术负责人，组内成员共4人，负责外网主站业务开发与App Server端开发。

### 主要项目

#### 业务框架升级
 - 引入Yaf作为基础框架，提升站点性能，页面平均QPS提升10%
 - 封装核心业务，清理历史问题代码，并输出配套说明文档，方便RD与PM加深对业务的认识
 - 规范异常处理，摒弃原有状态码判断函数运行结果的方式，做到异常中心化处理与异常信息上报
 - 推进前端组件化，减少前端代码冗余，降低后端工程师使用前端组件的成本
 - 搭建业务问题自查系统，提升问题排查效率，有效改善了原先客服频繁骚扰RD的状况

#### 检索系统
 - 替换原有Sphinx，改用Solr，配合运维部署集群
 - 使用阿里canal作为MySQL同步组件配合RabbitMQ实现索引更新，大幅度提升了系统的实时性与扩展性
 - 实现商品列表内容秒级更新，提高用户体验

#### 短信推送系统
 - 使用Swoole Task实现短信异步推送，很大程度上解决了原有短信接口同步推送堵塞问题
 - 可开启黑名单过滤，设置屏蔽关键字，使用MongoDB记录短信发送记录，接口调用更人性化
 - 检测服务并可自动切换第三方短信接口，保证服务可用性

---

# 技能清单
 - Web开发：PHP/Node/Lua
 - Web框架：Yaf/Laravel/ThinkPHP
 - 前端框架：jQuery/Bootstrap/AngularJS
 - 服务器：Nginx
 - 数据库：MySQL/Redis/Memcached/MongoDB
 - 版本管理：Git/Svn
 - 测试工具：PHPUnit/PhantomJS/ab/wrk
 - 编辑器：Vim/PhpStorm

## 个人项目
 - [fis273](https://npm.taobao.org/package/fis273) 一套前端资源管理方案
 - [jeet-lua](https://github.com/bug1024/jeet-lua) 一个轻量的Lua MVC Web框架
 - [jeet-vim](https://github.com/bug1024/jeet-vim) 一套web开发者的vim配置方案

## 书籍网站
 - [开发者头条](http://toutiao.io/) 一个程序员技术分享平台
 - [湾区日报](https://wanqu.co/) 一个关于创业与技术的优质英文文章推荐网站
 - [个人书单](https://github.com/bug1024/doc/blob/master/Blog/my-growth.md) 阅读过值的技术书籍

---

# 致谢

 感谢您花时间阅读我的简历，期待能有机会和您共事。

