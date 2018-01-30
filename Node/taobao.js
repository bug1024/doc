
var casper = require('casper').create({
    pageSettings: {
        userAgent : "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36"
        //loadImages:  false,
        //loadPlugins: false
    },
    logLevel: "info",              // Only "info" level messages will be logged
    verbose: true                  // log messages will be printed out to the console
});

var loginUrl = "https://login.taobao.com/member/login.jhtml";

casper.start(loginUrl, function() {
    this.echo("打开登录页面");
    this.click("#J_Quick2Static");
});

casper.then(function() {
    this.echo("输入账号密码");
    this.fill("form#J_Form", {
        "TPL_username" : "xxx",
        "TPL_password" : "xxx"
    }, false);
});

casper.then(function() {
    this.wait(2000, function() {
        if (this.getTitle() == "我的淘宝") {
            this.echo("登录成功");
        } else {
            this.echo("登录失败");
        }
        this.capture("taobao.png");
    });
});

casper.run();
