// server
var http = require('http');
var url  = require('url');
var util = require('util');

var start = function() {
    function onRequest(req, res) {
        var pathObj = url.parse(req.url);
        console.log(pathObj.pathname + ' received');
        res.writeHead(200, {'Content-Type': 'text/plain'});
        res.end(util.inspect(pathObj, true));
    }

    http.createServer(onRequest).listen(8282);
    console.log('Server has started.')
};

exports.start = start;
