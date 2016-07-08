
// link count

var casper = require("casper").create({
    loadImages: false,
    //logLevel:   "debug",
    verbose:    true
});

var links = {
    "http://fz.273.cn/" : 0,
    "http://fz.273.cn/car/" : 0,
    "http://fz.273.cn/cgb1/": 0,
    "http://fz.273.cn/car/x14968284.html": 0
};

var fantomas = Object.create(casper);

fantomas.countLinks = function() {
    return this.evaluate(function() {
        return __utils__.findAll("a[href]").length;
    });
};

fantomas.renderJSON = function(what) {
    this.echo(JSON.stringify(what, null, "  "));
};

fantomas.start();

Object.keys(links).forEach(function(url) {
    fantomas.thenOpen(url, function() {
        links[url] = this.countLinks();
    });
});

fantomas.run(function() {
    this.renderJSON(links);
    this.exit();
});
