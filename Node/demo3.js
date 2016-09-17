// email register
var request = require('request');

var api = 'https://ssl.mail.163.com/regall/unireg/call.do;jsessionid=5C56980DC3CCD239B57795428B053F23?cmd=register.start';

var data = {
    name: 'username',
    flow: 'main',
    uid: 'username@163.com',
    password: 'abc123',
    confirmPassword: 'abc123',
    mobile: '+86-15059130243',
    vcode:'1111',
    acode:'507525',
    from:'163mail_right'
};

request.post(api, {form: data}, function(err, res, body) {
    console.log(body);
});
