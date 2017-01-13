#!/bin/sh

#需要启动php的脚本个数(自行修改)
process_num=10

#目标php脚本所在路径(自行修改)
path=/data/server/weidian/libs/Tfs

#目标php脚本文件名(自行修改)
scriptFile=tfsServiceOptitimAsyn.php

#当前shell脚本文件名(自行修改)
shellFile=start_tfs_service_opitim.sh

#======= php安装路径(不用修改) ===============
phpbin=/usr/local/webserver/php/bin/php
#==========================================

#==========================================
#后面的代码不用修改
#==========================================

cd $path

cur_pid=$$

if [[ "$1" == "stop" ]]; then
        pids=`ps aux|grep "$phpbin $scriptFile"|grep -v "grep" |awk '{printf $2" "}'`
        kill $pids
        echo "php pid:$pids"
        RETVAL=$?
        if [ $RETVAL -eq 0 ]; then
                echo "stop php success"
        else
                echo "stop php failed"
        fi

        ps aux|grep "$shellFile"|grep -vP "grep|$cur_pid" |awk '{printf $2" "}'|xargs kill
        RETVAL=$?
        if [ $RETVAL -eq 0 ]; then
                echo "stop shell success"
        else
                echo "stop shell failed"
        fi

        exit 0;
fi

while [ 1 ]
do
        pro_num=`ps axu|grep "$phpbin $scriptFile"|grep -v grep|wc -l`
        echo $pro_num
        if [ $pro_num -lt $process_num ]; then
                nohup $phpbin $scriptFile >log 2>&1 &
                echo "start new ok";
        fi
        sleep 1
done

