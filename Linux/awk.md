# awk

```
    awk  -F ':' '{print $1,$2,NR,NF}' file.txt
```
- NR：行号
- NF：总列数
- -F ':'  使用:作为分割符
