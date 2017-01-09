# awk

```
    awk  -F ':' '{print $1,$2,NR,NF}' file.txt
```
- NR：行号
- NF：总列数
- -F ':'  使用:作为分割符


## 统计

```
    awk -F"real_ip:" '{split($2, a, " ");print a[1]}' file_name | awk '{a[$1]+=1;}END{for(i in a){print a[i]" " i;}}' | sort -nr
```
