
// 顶部关键字搜索

var casper = require('casper').create({
    verbose: false,
    logLevel: 'info'
});

casper.start("http://fz.273.cn/");

casper.then(function() {
    this.fill('form#car_suggest_form', {
        'kw': '宝马'
    }, true);
});

casper.wait(1000, function() {
    this.echo(this.getTitle());
    var kw = require('url').parse(this.getCurrentUrl(), true).query.kw;
    var title = this.getElementInfo('.detail .tit a').text;
    var match = /.+宝马.+/.test(title);
    if (match) {
        this.echo(kw, 'GREEN_BAR');
    } else {
        this.echo('查找失败', 'RED_BAR');
    }
});

casper.run(function() {
    this.echo('完成顶部关键字搜索检测').exit();
});
