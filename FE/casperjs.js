// casperjs --web-security=no casperjs.js
var casper = require('casper').create();

var url = 'http://m.sfzpxs.cn/0300/20151108282.html';

var urls = [
    'http://m.sfzpxs.cn/0300/20151108282.html',
    'http://m.sfzpxs.cn/0300/20151108282_2.html',
    'http://m.sfzpxs.cn/0300/20151108282_3.html',
    'http://m.sfzpxs.cn/0300/20151108282_4.html',
    'http://m.sfzpxs.cn/0300/20151108282_5.html',
    'http://m.sfzpxs.cn/0300/20151108282_6.html',
    'http://m.sfzpxs.cn/0300/20151108282_7.html',
    'http://m.sfzpxs.cn/0300/20151108282_8.html',
    'http://m.sfzpxs.cn/0300/20151108282_9.html',
    'http://m.sfzpxs.cn/0300/20151108282_10.html',
    'http://m.sfzpxs.cn/0300/20151108282_11.html'
];


function getImages() {
    var els = document.querySelectorAll('img');

    var results = [];

    Array.prototype.forEach.call(els, function(el){
        if (el.hasAttribute('alt')) {
            var alt = el.hasAttribute('alt') ? el.getAttribute('alt') : null;
            var src = el.getAttribute('src');
            results.push([src, alt]);
        }

    });

    return results;
}

var imgs = [];

casper.start();

for (var i in urls) {
    casper.thenOpen(urls[i], function() {
        imgs = this.evaluate(getImages);
        for (var i in imgs) {
            console.log(imgs[i][0], imgs[i][1]);
            this.download(imgs[i][0], imgs[i][1] + '.jpg');
        }
    });
}

casper.run(function() {
    this.done();
});
