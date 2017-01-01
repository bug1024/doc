<?php

/**
* 检测当前脚本是否在运行中，以及被同时执行的个数
*
* @param: $allow_run_num:被同时执行的最大个数, 默认1
* @return: 如果脚本运行数量已经达到了最大个数，则返回true, 否则false
*/
function script_is_run($allow_run_num = 1) {
    $script_name = $_SERVER['SCRIPT_NAME'];
    exec("ps axu|grep $script_name|grep -vP \"grep|vim\"", $out);
    if (is_array($out)) {
        $count = 0;
        foreach ($out as $cmd) {
            if (false !== strpos($cmd, $script_name)) {
                $count ++;
            }
            // 第1次运行时进程就会被识别进来，因此第1次运行时，就会识别到1个进程
            if ($count > $allow_run_num) {
                echo "script is already runing, exit now";
                return true;
            }
        }
    }
    return false;
}
if (script_is_run()) {
    exit(0);
}

/* 允许同时运行3个的调用方法
if ( script_is_run(3) ) {
    exit(0);
}
*/
