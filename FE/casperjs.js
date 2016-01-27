// click a button and capture screen
var casper = require('casper').create();

var url = 'http://fz.273.cn/car/';

casper.start(url, function() {
    this.echo(this.getTitle());
});

casper.then(function() {
    this.click('.js_depreciate');
    this.wait(2000, function() {
        this.capture('list.png');
    });
});

casper.run();
