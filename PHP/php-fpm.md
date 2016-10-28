# PHP-FPM

## Nginx和PHP-FPM的进程间通信方式

### TCP IP加端口,可以跨服务器.而
```
    php-fpm.conf: listen = 127.0.0.1:9000
    nginx.conf: fastcgi_pass 127.0.0.1:9000;
```

### UNIX Domain Socket 不经过网络，只能用于Nginx跟PHP-FPM都在同一服务器的场景，php-fpm.sock是一个文件，由php-fpm生成，类型是srw-rw----
```
    php-fpm.conf: listen = /tmp/php-fpm.sock
    nginx.conf: fastcgi_pass unix:/tmp/php-fpm.sock;
```
