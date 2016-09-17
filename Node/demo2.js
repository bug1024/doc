// node stream
var fs = require('fs');

var stream = fs.createReadStream('test.txt');

var data = '';

stream.on('data', function(chunk) {
    data += chunk;
});

stream.on('error', function(err) {
    console.log(err.stack)
});

stream.on('end', function() {
    console.log(data);
});
