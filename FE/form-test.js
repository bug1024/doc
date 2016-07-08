
// 表单验证

var casper = require('casper').create({
    verbose: false,
    logLevel: 'info'
});

casper.start("http://fz.273.cn/car/");

casper.then(function() {
    this.click('.js_depreciate');
    this.waitForSelector('.mod-pop-ex-price', function() {
        console.log('我要砍价表单弹出');
    });
});

casper.run(function() {
    this.echo('success').exit();
});
