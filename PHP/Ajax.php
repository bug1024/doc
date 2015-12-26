<?php

abstract class AJax {

    protected $_request;

    protected $_currentParam = null;

    public function __construct() {
        $this->_request = $_REQUEST;
    }

    public function params($key = '', $default = false) {
        if (!empty($key)) {
            $this->_currentParam = isset($this->_request[$key]) ? $this->_request[$key] : $default;
        } else {
            $this->_currentParam = $this->_request;
        }

        return $this;
    }

    public function get() {
        return $this->_currentParam;
    }

    public function __call($method, $args) {
        return call_user_func([$this, $method], $this->_currentParam);
    }

    public function isEmail() {
        $ret = filter_var($this->_currentParam, FILTER_VALIDATE_EMAIL);
        if ($ret === false) {
            throw new Exception('not a email');
        }
        return $this;
    }

    public function isMobile() {
        if (!preg_match('/^1\d{10}$/', $this->_currentParam)) {
            throw new Exception('not a mobile');
        }
        return $this;
    }

}


class AjaxTest extends Ajax {


    public function test() {
        try {
            $ret1 = $this->params('mobile')->isMobile()->get();
            $ret2 = $this->params('mobile1', '12344')->isMobile()->get();
        } catch (Exception $e) {
            var_dump($e->getMessage());
        }
        var_dump($ret1, $ret2);
    }

}


(new AjaxTest())->test();

