<?php

/**
 * 生成traceId算法, 对外函数为genTraceId()和parse()
 *
 * 注意:本类涉及到的字节数组, 都使用字节大端序
 */
class TraceId {

    private static $__seq = 1;
    private static $localIp = 0;

    //TraceId::genTraceId()返回的是新生成的traceId,
    //这里的traceId是当前请求全局唯一的traceId
    public static $traceId = 0;

    public static $rpcId = 0; //为0表示root, 函数代码会优先从header头部取
    public static $rpcIdReq = 1; //请求下游的序号,每一次调用后递增


    /**
     * 生成新的traceId
     *
     * @return string
     */
    public static function getTraceId() {
        // 优先使用已经生成的
        if (! empty(self::$traceId)) {
            return self::$traceId;
        }

        // 其次使用上游传递的
        if (isset($_SERVER['HTTP_X_TRACE_ID'])) {
            self::$traceId = $_SERVER['HTTP_X_TRACE_ID'];
            return self::$traceId;
        }

        // 最后考虑自己生成,说明是rpc的最上游
        self::$traceId = TraceId::genTraceId();

        return self::$traceId;
    }


    /**
     * 产生traceId
     *
     * @return string
     */
    public static function genTraceId(){
        $arr = array();

        $seq = TraceId::$__seq++;
        $time = self::getMillisecond();
        $ip = self::getLocalIp();
        $pid = getmypid();

        $arr = self::shortToBytes($seq);
        $arr = array_merge($arr, self::longToBytes($time));
        $arr = array_merge($arr, self::ipToBytes($ip));
        $arr = array_merge($arr, self::shortToBytes($pid));

        //echo "byte array:" . implode(',', $arr) . "\n";

        $hexArr = self::encodeHex($arr);

        $traceId = implode('', $hexArr);

        $bytes = self::decodeHex($traceId);
        //echo "decode byte array:" . implode(',', $bytes) . "\n";
        self::parse($traceId);

        return $traceId;
    }

    /**
     * 获取本机ip地址
     *
     * @return string 字符串ip(10.x.x.x.)
     */
    public static function getLocalIp() {
        if (!empty(self::$localIp)) {
            return self::$localIp;
        }

        if (!empty($_SERVER['SERVER_ADDR'])) {
            self::$localIp = $_SERVER['SERVER_ADDR'];
        } else {
            $hostname = empty($_SERVER['HOSTNAME']) ? '' : $_SERVER['HOSTNAME'];
            self::$localIp = gethostbyname($hostname);
        }

        return self::$localIp;
    }

    /**
     * 获取当前系统时间,精确到毫秒
     *
     * @return float
     */
    public static function getMillisecond() {
        list($t1, $t2) = explode(' ', microtime());
        return (float) sprintf('%.0f', (floatval($t1) + floatval($t2)) * 1000);
    }

    /**
     * 根据traceId反解出里面的各个字段(seq,time,ip,pid)
     *
     * @param string  $traceId
     * @return array  Array('seq'=>xx, 'time'=>xx, 'ip'=>'xx', 'pid'=>xx)
     */
    public static function parse($traceId){
        $bytes = self::decodeHex($traceId);

        //echo "decode hex bytes:" . implode(',', $bytes) . "\n";

        $seq_bytes = array_slice($bytes, 0, 2);
        $seq = self::bytesToShort($seq_bytes);

        $time_bytes = array_slice($bytes, 2, 8);
        $time = self::bytesToLong($time_bytes);
        $time = date('Y-m-d H:i:s', intval($time/1000)) . '.' . ($time%1000);

        $ip_bytes = array_slice($bytes, 10, 4);
        $ip = implode('.', $ip_bytes);

        $pid_bytes = array_slice($bytes, 14, 2);
        $pid = self::bytesToShort($pid_bytes);

        $res = array(
            'seq'     => $seq,
            'time'    => $time,
            'ip'      => $ip,
            'pid'     => $pid,
        );

        return $res;
    }

    /**
     * 根据字节数组转换为short整数, short占2个字节
     *
     * @param array $bytes
     * @return int
     */
    public static function bytesToShort($bytes){
        return ($bytes[0] << 8) | $bytes[1];
    }

    /**
     * 根据字节数组转换为Long整数, long类型占8个字节
     *
     * @param string  $traceId
     * @return int
     */
    public static function bytesToLong($bytes){
        return ($bytes[0] << 56) |
            ($bytes[1] << 48) |
            ($bytes[2] << 40) |
            ($bytes[3] << 32) |
            ($bytes[4] << 24) |
            ($bytes[5] << 16) |
            ($bytes[6] << 8)  | $bytes[7] ;
    }

    /**
     * ip地址转为字节数组
     *
     * @param string $ip 10.x.x.x
     * @return array
     */
    public static function ipToBytes($ip) {
        $ret = array();
        try {
            $ipArr = explode('.', $ip);
            $ret[0] = intval($ipArr[0]) & 0xFF;
            $ret[1] = intval($ipArr[1]) & 0xFF;
            $ret[2] = intval($ipArr[2]) & 0xFF;
            $ret[3] = intval($ipArr[3]) & 0xFF;
            return $ret;
        } catch (Exception $e) {
        }

    }

    /**
     * 字节数组转为16进制, 每个字节(8bit)会转换为2个16进制字符,类似0xAB
     *
     * @param array $arrBytesData 字节数组
     * @return string 16进制表示的字符串
     */
    public static function encodeHex($arrBytesData) {
        $len = count($arrBytesData);
        $out = array();
        // two characters form the hex value.
        for ($i = 0, $j = 0; $i < $len; $i++) {
            //每个字节,可以转为2个16进制字符
            $out[$j++] = dechex( (0xF0 & $arrBytesData[$i]) >> 4);
            $out[$j++] = dechex( 0x0F & $arrBytesData[$i] );
        }

        return $out;
    }

    /**
     * 16进制的字符转为字节数组, 每2个16进制字符转为1个字节
     *
     * @param  string $traceId
     * @return array
     */
    public static function decodeHex($traceId) {
        $len = strlen($traceId);
        $out = array();
        // two characters form the hex value.
        for ($i = 0, $j = 0; $j < $len; $i++) {
            $f = hexdec($traceId[$j]) << 4;
            $j++;
            $f = $f | hexdec($traceId[$j]);
            $j++;
            $out[$i] = $f & 0xFF;
        }

        return $out;
    }

    /**
     * short类型转为字节数组
     *
     * @param int $val
     * @return array
     */
    public static function shortToBytes($val) {
        $byt = array();
        $byt[0] = $val >> 8;
        $byt[1] = (0xFF & $val);

        return $byt;
    }

    /**
     * int类型转为字节数组
     *
     * @param int $val
     * @return array
     */
    public static function intToBytes($val) {
        $byt = array();
        $byt[0] = ($val >> 24 & 0xff);
        $byt[1] = ($val >> 16 & 0xff);
        $byt[2] = ($val >> 8  & 0xff);
        $byt[3] = ($val       & 0xff);

        return $byt;
    }

    /**
     * long类型转为字节数组
     *
     * @param int $val
     * @return array
     */
    public static function longToBytes($val) {
        $byt = array();
        $byt[0] = ($val >> 56 & 0xff);
        $byt[1] = ($val >> 48 & 0xff);
        $byt[2] = ($val >> 40 & 0xff);
        $byt[3] = ($val >> 32 & 0xff);
        $byt[4] = ($val >> 24 & 0xff);
        $byt[5] = ($val >> 16 & 0xff);
        $byt[6] = ($val >> 8  & 0xff);
        $byt[7] = ($val       & 0xff);

        return $byt;
    }

    /**
     * 获取本次服务的rpcId
     *
     * @return string
     */
    public static function getCurrentRpcId(){
        // 优先使用已经生成的
        if (! empty(self::$rpcId)) {
            return self::$rpcId;
        }

        // 其次使用上游传递的
        if (isset($_SERVER['HTTP_X_TRACE_RPCID'])) {
            self::$rpcId = $_SERVER['HTTP_X_TRACE_RPCID'];
        }

        return self::$rpcId;
    }

    /**
     * 生成下游服务的rpcId
     *
     * @return string
     */
    public static function getRpcIdNextService(){
        $cur_rpcId = self::getCurrentRpcId();

        return $cur_rpcId . '.' .  self::$rpcIdReq++;
    }

}

echo TraceId::getTraceId();

