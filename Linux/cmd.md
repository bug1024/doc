## 新增用户
useradd user1

## 设置密码
passwd user1

## 新建用户组
groupadd group1

## 用户组新增用户
gpasswd -a user1 group1

## 修改目录所有者
chown -R user1:group1 dir1

## 统计行数
find ./ -name "*.php" |xargs cat|wc -l

## 统计非空行行数
find  ./ -name "*.php" |xargs cat|grep -v ^$|wc -l

## 搜索文件
find ./ -name "*.php"

搜索文本
grep -rn xxxx ./*

## TCP ESTABLISHED数量
netstat -n|grep ^tcp|awk '{print $NF}'|sort -nr|uniq -c

类似于/dev/null, /dev/zero也是一个伪文件, 但事实上它会产生一个null流(二进制的0流, 而不是ASCII类型)

## xargs
之所以能用到这个命令，关键是由于很多命令不支持|管道来传递参数，而日常工作中有有这个必要，所以就有了xargs命令，例如：
```
    find /sbin -perm +700 |ls -l       这个命令是错误的
    find /sbin -perm +700 |xargs ls -l   这样才是正确的
```
