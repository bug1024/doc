// node server
var http = require('http');
var fs   = require('fs');

http.createServer(function(req, res) {
    var data = fs.readFileSync('test.txt');
    var text = data.toString();

    res.end(text);
}).listen(8282);

console.log((new Date), 'Node server is running');

fs.readFile('test.txt', function(err, data) {
    if (err) {
        return console.log(err);
    }

    console.log('async', data.toString());
});
