
// 页面错误检测

var links = [
    "http://fz.273.cn/",
    "http://fz.273.cn/car/",
    "http://fz.273.cn/cgb1/",
    "http://fz.273.cn/car/x14968284.html"
];

var casper = require('casper').create({
    verbose: false,
    logLevel: 'info'
});

// http status
casper.on("http.status.200", function(resource) {
    this.echo(resource.url + " is OK", "INFO");
});
casper.on("http.status.301", function(resource) {
    this.echo(resource.url + " is permanently redirected", "PARAMETER");
});
casper.on("http.status.302", function(resource) {
    this.echo(resource.url + " is temporarily redirected", "PARAMETER");
});
casper.on("http.status.404", function(resource) {
    this.echo(resource.url + " is not found", "COMMENT");
});
casper.on("http.status.500", function(resource) {
    this.echo(resource.url + " is in error", "ERROR");
});

casper.start();

casper.each(links, function(self, link) {
    this.thenOpen(link, function() {
        this.exists('.xdebug-error') && this.echo("page error is detected", "ERROR");
    });
});

casper.run(function() {
    this.echo('完成页面错误检测').exit();
});
