# 多域名站点升级方案

## 简述
 站点由全国站，地方站，门店站组成。
 地方站和门店站的域名规则几乎一致，很难在服务器层面区分，只有到了php业务层才能区分。
 而此次门店站产品需求并未更新，导致无法一次性将这些站点统一迁移到新的平台。

## Nginx代理方案1
 根据$host进行转发

 server.conf
```
    server {
        ...

        location / {
            # 判断是否需要转发到v4服务器
            include path/to/rw_v4.conf;
            # 原有rewrite规则
            include path/to/rw_v3.conf;
        }

        ...
    }
```

 rw_v4.conf
```
    # 需要转发到v4的二级域名，由于这些域名数量多达上百个，全部匹配的话性能损耗较大，因此目前只是临时开放少部分域名
    if ($host ~ ^(fz|xm|bj)\.273\.cn) {
        set $flag "1";
    }

    # 由于产品进度原因，以下路径的链接依旧维持v3版本
    if ($uri ~ ^/(check|buy|car/\d+\.html)$) {
        set $flag "2";
    }

    # 首页及列表页需要转发到v4
    if ($uri ~* (^/$|^/car/|^/[\w-]+/)) {
        set $flag "${flag}3";
    }

    if ($flag = "13") {
        # 转发到v4
        proxy_pass web.v4;
        break;
    }
```

## Nginx代理方案2
 根据$http_cookie进行转发

 rw_v4.conf
```
    # 带以下cookie的转发到v4，php业务层中在需要升级到v4的页面种下名为web_v4的cookie
    if ($http_cookie ~ "web_v4=1") {
        set $v4 "1";
    }

    if ($v4 = "1") {
        # 转发到v4
        proxy_pass web.v4;
        break;
    }
```

## 两种方案的对比
|方案 | 简洁性 | 可维护性                         | 性能                                          |
|---- | ----   | -----                            | ----                                          |
|   1 | 较复杂 | 每次开放新域名需要运维更新配置   | 开放域名过多时性能较差                        |
|   2 | 较简洁 | 由php开发自行配置                | 首次访问时需要进入v3业务层中然后再重定向到v4。|


## 其余方案
 - HAProxy代理
 - Nginx Lua使用Redis判断需要转发的域名

