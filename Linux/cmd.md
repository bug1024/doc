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
