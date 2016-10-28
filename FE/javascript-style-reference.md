# JavaScript编码规范 2015-9-2 #

为规范前端开发代码，提高代码质量，特制定此文档，其中**声明**，**安全**和**分号**这三节是必须执行的，组件类必须遵循**注释规范**。

## 声明 ##

- 变量声明必须加var关键字，严格控制作用域；
- 建议使用驼峰式命名变量和函数，如：functionNamesLikeThis, variableNamesLikeThis, ClassNamesLikeThis,namespaceNamesLikeThis；
- 私有成员变量和方法命名以下划线开头，如：var _this；
- 常量定义单词全部大写，以下划线连接，但不要用const关键字来声明，如：SOME_CONSTANTS；
- 函数参数大于3个时，应以对象形式作为参数集传递；
- 禁止在代码块中声明函数，错误的范例：if (true) {function foo() {}}；
- 禁止用new来实例化**基本类型**，错误的范例：var x = new Boolean(false)；
- 直接定义数组或对象，而不使用new关键字声明，错误的范例：var a = new Array();var o = new Object()；
- 使用单引号来定义字符串；
- 文件名必须全部用小写，文件名分隔符用**中划线**连接，版本连接符用**实心点**，合并文件的文件名连接符用**下划线**，如：passport-core.min.js和reset-1.0_utils-1.0.css；

## 安全 ##

- 审查用户输入，如：从URL获取参数，使用跳转页面的referer，用于eval或DOM操作的用户数据；
- 禁止通过在iframe使用script进行跨域回调；
- 警惕jquery xss，禁止这样的写法：$(window.location.hash)；
- 禁止引用站外资源；

## 分号 ##

  - 语句结束一定要加分号

    ```javascript
    // bad
    var myMethod = function() {
        return 42;
    }
    (function() {
        // 一个匿名函数，在这里会被错误解析当作参数调用导致报错
    })();

    // good
    var myMethod = function() {
        return 42;
    };
    (function() {
        // 一个匿名函数，在这里会被错误解析当作参数调用导致报错
    })();
    ```

## 异常 ##

由于无法完美的控制都不出现异常，所以尽量在可能但不确定出现异常的地方（大量运算，AJAX请求，数组操作或DOM操作等）用try-catch(e)来抛出异常，这样有利于规模较大的项目中排查错误。使用自定义异常抛出错误信息，更有利于错误信息阅读，且更加通用，因为不同浏览器抛出的信息不一样，有时候可能很难定位到问题所在位置，特别是IE下可能遇到的如下报错信息：

    Stack overflow at line: 0
    或：
    未知的运行时错误

## 标准化代码编写 ##

为获取最大化的可移植性和兼容性，代码中应使用标准中支持的方式来书写代码，虽然浏览器支持某些语法但不在规范中，你无法保证你将来不会遇到兼容其他浏览器的情况，如果这个浏览器完全或部分特性只按规范实现。

    //错误的范例
    var char = 'hello world'[3];
    var myForm = document.myForm;

    //正确的写法
    var char = 'hello world'.charAt(3)
    var myForm = document.forms[0];或者var myForm = document.getElementsByTagName('form')[0];

## 面向对象编程 ##

- 类中的成员变量使用构造函数来初始化；
- 除非是必须移除类的成员，否则析构函数中对成员的销毁应通过将其设置为null，而不是用delete，因为重新赋值方式性能比用delete好；
- 避免通过prototype方式污染内置对象原型链；

## 作用域相关 ##

- 不使用with关键字，容易造成作用域混乱；
- this仅用于类成员函数或对象中；
- 通用全局函数，特别是通用组件代码应将业务逻辑放入闭包中，并通过“命名空间”将其引入；
- 若函数中使用到全局变量，则访问全局变量时应使用window来引入，如：

        var somevar = 10；
        function getvar(){
            var num = window.somevar + 1;
            //...
            return num;
        }


## 空白 ##

  - 将tab设为4个空格

    ```javascript
    // bad
    function() {
    ∙∙var name;
    }

    // bad
    function() {
    ∙var name;
    }

    // good
    function() {
    ∙∙∙∙var name;
    }
    ```
  - 大括号前放一个空格

    ```javascript
    // bad
    function test(){
        console.log('test');
    }

    // good
    function test() {
        console.log('test');
    }

    // bad
    dog.set('attr',{
        age: '1 year',
        breed: 'Bernese Mountain Dog'
    });

    // good
    dog.set('attr', {
        age: '1 year',
        breed: 'Bernese Mountain Dog'
    });
    ```

  - 在做长方法链时使用缩进.

    ```javascript
    // bad
    $('#items').find('.selected').highlight().end().find('.open').updateCount();

    // good
    $('#items')
        .find('.selected')
            .highlight()
            .end()
        .find('.open')
            .updateCount();
    ```

## 逗号 ##

  - 不要将逗号放前面

    ```javascript
    // bad
    var once
      , upon
      , aTime;

    // good
    var once,
        upon,
        aTime;

    // bad
    var hero = {
          firstName: 'Bob'
        , lastName: 'Parr'
        , heroName: 'Mr. Incredible'
        , superPower: 'strength'
    };

    // good
    var hero = {
        firstName: 'Bob',
        lastName: 'Parr',
        heroName: 'Mr. Incredible',
        superPower: 'strength'
    };
    ```

  - 不要加多余的逗号，这可能会在IE下引起错误，同时如果多一个逗号某些ES3的实现会计算多数组的长度。

    ```javascript
    // bad
    var hero = {
        firstName: 'Kevin',
        lastName: 'Flynn',
    };

    var heroes = [
        'Batman',
        'Superman',
    ];

    // good
    var hero = {
        firstName: 'Kevin',
        lastName: 'Flynn'
    };

    var heroes = [
        'Batman',
        'Superman'
    ];
    ```




## 类型 ##

  - **原始值**: 相当于传值

    + `string`
    + `number`
    + `boolean`
    + `null`
    + `undefined`

    ```javascript
    var foo = 1,
        bar = foo;

    bar = 9;

    console.log(foo, bar); // => 1, 9
    ```
  - **复杂类型**: 相当于传引用

    + `object`
    + `array`
    + `function`

    ```javascript
    var foo = [1, 2],
        bar = foo;

    bar[0] = 9;

    console.log(foo[0], bar[0]); // => 9, 9
    ```

## 对象 ##

  - 使用字面值创建对象

    ```javascript
    // bad
    var item = new Object();

    // good
    var item = {};
    ```

  - 不要使用保留字 [reserved words](https://developer.mozilla.org/en-US/docs/JavaScript/Reference/Reserved_Words) 作为键

    ```javascript
    // bad
    var superman = {
        class: 'superhero',
        default: { clark: 'kent' },
        private: true
    };

    // good
    var superman = {
        klass: 'superhero',
        defaults: { clark: 'kent' },
        hidden: true
    };
    ```

## 数组 ##

  - 使用字面值创建数组

    ```javascript
    // bad
    var items = new Array();

    // good
    var items = [];
    ```

  - 如果你不知道数组的长度，使用push

    ```javascript
    var someStack = [];

    // bad
    someStack[someStack.length] = 'abracadabra';

    // good
    someStack.push('abracadabra');
    ```

  - 当你需要拷贝数组时使用slice. [jsPerf](http://jsperf.com/converting-arguments-to-an-array/7)

    ```javascript
    var len = items.length,
        itemsCopy = [],
        i;

    // bad
    for (i = 0; i < len; i++) {
        itemsCopy[i] = items[i];
    }

    // good
    itemsCopy = items.slice();
    ```

  - 使用slice将类数组的对象转成数组.

    ```javascript
    function trigger() {
        var args = Array.prototype.slice.call(arguments);
        ...
    }
    ```

## 字符串 ##

  - 对字符串使用单引号 `''`

    ```javascript
    // bad
    var name = "Bob Parr";

    // good
    var name = 'Bob Parr';

    // bad
    var fullName = "Bob " + this.lastName;

    // good
    var fullName = 'Bob ' + this.lastName;
    ```

  - 超过80个字符的字符串应该使用字符串连接换行
  - 注: 如果过度使用，长字符串连接可能会对性能有影响. [jsPerf](http://jsperf.com/ya-string-concat) & [Discussion](https://github.com/airbnb/javascript/issues/40)

    ```javascript
    // bad
    var errorMessage = 'This is a super long error that was thrown because of Batman. When you stop to think about how Batman had anything to do with this, you would get nowhere fast.';

    // bad
    var errorMessage = 'This is a super long error that \
    was thrown because of Batman. \
    When you stop to think about \
    how Batman had anything to do \
    with this, you would get nowhere \
    fast.';

    // good
    var errorMessage = 'This is a super long error that ' +
      'was thrown because of Batman.' +
      'When you stop to think about ' +
      'how Batman had anything to do ' +
      'with this, you would get nowhere ' +
      'fast.';
    ```

  - 编程时使用join而不是字符串连接来构建字符串，特别是IE: [jsPerf](http://jsperf.com/string-vs-array-concat/2).

    ```javascript
    var items,
        messages,
        length, i;

    messages = [{
        state: 'success',
        message: 'This one worked.'
    },{
        state: 'success',
        message: 'This one worked as well.'
    },{
        state: 'error',
        message: 'This one did not work.'
    }];

    length = messages.length;

    // bad
    function inbox(messages) {
      items = '<ul>';

      for (i = 0; i < length; i++) {
        items += '<li>' + messages[i].message + '</li>';
      }

      return items + '</ul>';
    }

    // good
    function inbox(messages) {
      items = [];

      for (i = 0; i < length; i++) {
        items[i] = messages[i].message;
      }

      return '<ul><li>' + items.join('</li><li>') + '</li></ul>';
    }
    ```

## 函数 ##

  - 函数表达式:

    ```javascript
    // 匿名函数表达式
    var anonymous = function() {
        return true;
    };

    // 有名函数表达式
    var named = function named() {
        return true;
    };

    // 立即调用函数表达式
    (function() {
        console.log('Welcome to the Internet. Please follow me.');
    })();
    ```

  - 绝对不要在一个非函数块里声明一个函数，把那个函数赋给一个变量。浏览器允许你这么做，但是它们解析不同。
  - **注:** ECMA-262定义把`块`定义为一组语句，函数声明不是一个语句。[阅读ECMA-262对这个问题的说明](http://www.ecma-international.org/publications/files/ECMA-ST/Ecma-262.pdf#page=97).

    ```javascript
    // bad
    if (currentUser) {
        function test() {
            console.log('Nope.');
        }
    }

    // good
    if (currentUser) {
        var test = function test() {
            console.log('Yup.');
        };
    }
    ```

  - 绝对不要把参数命名为 `arguments`, 这将会逾越函数作用域内传过来的 `arguments` 对象.

    ```javascript
    // bad
    function nope(name, options, arguments) {
        // ...stuff...
    }

    // good
    function yup(name, options, args) {
        // ...stuff...
    }
    ```

## 属性 ##

  - 当使用变量访问属性时使用中括号.

    ```javascript
    var luke = {
        jedi: true,
        age: 28
    };

    function getProp(prop) {
        return luke[prop];
    }

    var isJedi = getProp('jedi');
    ```

## 条件表达式和等号 ##

  - 适当使用 `===` 和 `!==` 以及 `==` 和 `!=`.

  -以下表达式结果为false

    ```javascript
    null
    undefined
    '' // 空字符串
    0  // 数字
    ```

  -以下表达式结果全为true

    ```javascript
    '0' // 字符串
    []  // 空数组
    {}  // 空对象

    Boolean('0') == true
    '0' != true
    0 != null
    0 == []
    0 == false

    Boolean(null) == false
    null != true
    null != false

    Boolean(undefined) == false
    undefined != true
    undefined != false

    Boolean([]) == true
    [] != true
    [] == false

    Boolean({}) == true
    {} != true
    {} != false
    ```

  - 条件表达式的强制类型转换遵循以下规则：

    + **对象** 被计算为 **true**
    + **Undefined** 被计算为 **false**
    + **Null** 被计算为 **false**
    + **布尔值** 被计算为 **布尔的值**
    + **数字** 如果是 **+0, -0, or NaN** 被计算为 **false** , 否则为 **true**
    + **字符串** 如果是空字符串 `''` 则被计算为 **false**, 否则为 **true**

    ```javascript
    if ([0]) {
        // true
        // An array is an object, objects evaluate to true
    }
    ```

  - 使用快捷方式.

    ```javascript
    // bad
    if (name !== '') {
        // ...stuff...
    }

    // good
    if (name) {
        // ...stuff...
    }

    // bad
    if (collection.length > 0) {
        // ...stuff...
    }

    // good
    if (collection.length) {
        // ...stuff...
    }
    ```

  - 阅读 [Truth Equality and JavaScript](http://javascriptweblog.wordpress.com/2011/02/07/truth-equality-and-javascript/#more-2108) 了解更多

## 块 ##

  - 给所有多行的块使用大括号

    ```javascript
    // bad
    if (test)
        return false;

    // good
    if (test) return false;

    // good
    if (test) {
        return false;
    }

    // bad
    function() {return false;}

    // good
    function() {
        return false;
    }
    ```

## 注释 ##

  - 使用 `/** ... */` 进行多行注释，包括描述，指定类型以及参数值和返回值

    ```javascript
    // bad
    // make() returns a new element
    // based on the passed in tag name
    //
    // @param <String> tag
    // @return <Element> element
    function make(tag) {

        // ...stuff...

        return element;
    }

    // good
    /**
     * make() returns a new element
     * based on the passed in tag name
     *
     * @param <String> tag
     * @return <Element> element
     */
    function make(tag) {

      // ...stuff...

      return element;
    }
    ```

  - 使用 `//` 进行单行注释，在评论对象的上面进行单行注释，注释前放一个空行.

    ```javascript
    // bad
    var active = true;  // is current tab

    // good
    // is current tab
    var active = true;

    // bad
    function getType() {
        console.log('fetching type...');
        // set the default type to 'no type'
        var type = this._type || 'no type';

        return type;
    }

    // good
    function getType() {
        console.log('fetching type...');

        // set the default type to 'no type'
        var type = this._type || 'no type';

        return type;
    }
    ```

  - 如果你有一个问题需要重新来看一下或如果你建议一个需要被实现的解决方法的话需要在你的注释前面加上 `FIXME` 或 `TODO` 帮助其他人迅速理解

    ```javascript
    function Calculator() {

      // FIXME: shouldn't use a global here
      total = 0;

      return this;
    }
    ```

    ```javascript
    function Calculator() {

      // TODO: total should be configurable by an options param
      this.total = 0;

      return this;
    }
  ```

## 类型转换 ##

  - 在语句的开始执行类型转换.
  - 字符串:

    ```javascript
    //  => this.reviewScore = 9;

    // bad
    var totalScore = this.reviewScore + '';

    // good
    var totalScore = '' + this.reviewScore;

    // bad
    var totalScore = '' + this.reviewScore + ' total score';

    // good
    var totalScore = this.reviewScore + ' total score';
    ```

  - 对数字使用 `parseInt` 并且总是带上类型转换的基数.

    ```javascript
    var inputValue = '4';

    // bad
    var val = new Number(inputValue);

    // bad
    var val = +inputValue;

    // bad
    var val = inputValue >> 0;

    // bad
    var val = parseInt(inputValue);

    // good
    var val = Number(inputValue);

    // good
    var val = parseInt(inputValue, 10);

    // good
    /**
     * parseInt was the reason my code was slow.
     * Bitshifting the String to coerce it to a
     * Number made it a lot faster.
     */
    var val = inputValue >> 0;
    ```

  - 布尔值:

    ```javascript
    var age = 0;

    // bad
    var hasAge = new Boolean(age);

    // good
    var hasAge = Boolean(age);

    // good
    var hasAge = !!age;
    ```

## 命名约定 ##

  - 避免单个字符名，让你的变量名有描述意义。

    ```javascript
    // bad
    function q() {
        // ...stuff...
    }

    // good
    function query() {
        // ..stuff..
    }
    ```

  - 当命名对象、函数和实例时使用驼峰命名规则

    ```javascript
    // bad
    var OBJEcttsssss = {};
    var this_is_my_object = {};
    var this-is-my-object = {};
    function c() {};
    var u = new user({
        name: 'Bob Parr'
    });

    // good
    var thisIsMyObject = {};
    function thisIsMyFunction() {};
    var user = new User({
        name: 'Bob Parr'
    });
    ```

  - 当命名构造函数或类时使用驼峰式大写

    ```javascript
    // bad
    function user(options) {
        this.name = options.name;
    }

    var bad = new user({
        name: 'nope'
    });

    // good
    function User(options) {
        this.name = options.name;
    }

    var good = new User({
        name: 'yup'
    });
    ```

  - 命名私有属性时前面加个下划线 `_`

    ```javascript
    // bad
    this.__firstName__ = 'Panda';
    this.firstName_ = 'Panda';

    // good
    this._firstName = 'Panda';
    ```

  - 当保存对 `this` 的引用时使用 `_this`.

    ```javascript
    // bad
    function() {
        var self = this;
        return function() {
          console.log(self);
        };
    }

    // bad
    function() {
        var that = this;
        return function() {
          console.log(that);
        };
    }

    // good
    function() {
        var _this = this;
        return function() {
            console.log(_this);
        };
    }
    ```

## 存取器 ##

  - 属性的存取器函数不是必需的
  - 如果你确实有存取器函数的话使用getVal() 和 setVal('hello')

    ```javascript
    // bad
    dragon.age();

    // good
    dragon.getAge();

    // bad
    dragon.age(25);

    // good
    dragon.setAge(25);
    ```

  - 如果属性是布尔值，使用isVal() 或 hasVal()

    ```javascript
    // bad
    if (!dragon.age()) {
        return false;
    }

    // good
    if (!dragon.hasAge()) {
        return false;
    }
    ```

  - 可以创建get()和set()函数，但是要保持一致

    ```javascript
    function Jedi(options) {
        options || (options = {});
        var lightsaber = options.lightsaber || 'blue';
        this.set('lightsaber', lightsaber);
    }

    Jedi.prototype.set = function(key, val) {
        this[key] = val;
    };

    Jedi.prototype.get = function(key) {
        return this[key];
    };
    ```

## 构造器 ##

  - 给对象原型分配方法，而不是用一个新的对象覆盖原型，覆盖原型会使继承出现问题。

    ```javascript
    function Jedi() {
        console.log('new jedi');
    }

    // bad
    Jedi.prototype = {
        fight: function fight() {
          console.log('fighting');
        },

        block: function block() {
          console.log('blocking');
        }
    };

    // good
    Jedi.prototype.fight = function fight() {
        console.log('fighting');
    };

    Jedi.prototype.block = function block() {
        console.log('blocking');
    };
    ```

  - 方法可以返回 `this` 帮助方法可链。

    ```javascript
    // bad
    Jedi.prototype.jump = function() {
        this.jumping = true;
        return true;
    };

    Jedi.prototype.setHeight = function(height) {
        this.height = height;
    };

    var luke = new Jedi();
    luke.jump(); // => true
    luke.setHeight(20) // => undefined

    // good
    Jedi.prototype.jump = function() {
        this.jumping = true;
        return this;
    };

    Jedi.prototype.setHeight = function(height) {
        this.height = height;
        return this;
    };

    var luke = new Jedi();

    luke.jump()
      .setHeight(20);
    ```


  - 可以写一个自定义的toString()方法，但是确保它工作正常并且不会有副作用。

    ```javascript
    function Jedi(options) {
        options || (options = {});
        this.name = options.name || 'no name';
    }

    Jedi.prototype.getName = function getName() {
        return this.name;
    };

    Jedi.prototype.toString = function toString() {
        return 'Jedi - ' + this.getName();
    };
    ```

## 事件 ##

  - 当给事件附加数据时，传入一个哈希而不是原始值，这可以让后面的贡献者加入更多数据到事件数据里而不用找出并更新那个事件的事件处理器

    ```js
    // bad
    $(this).trigger('listingUpdated', listing.id);

    ...

    $(this).on('listingUpdated', function(e, listingId) {
        // do something with listingId
    });
    ```

    更好:

    ```js
    // good
    $(this).trigger('listingUpdated', {listingId: listing.id});

    ...

    $(this).on('listingUpdated', function(e, data) {
        // do something with data.listingId
    });
    ```

## jQuery ##

  - 缓存jQuery查询

    ```javascript
    // bad
    function setSidebar() {
        $('.sidebar').hide();

        // ...stuff...

        $('.sidebar').css({
            'background-color': 'pink'
        });
    }

    // good
    function setSidebar() {
        var $sidebar = $('.sidebar');
        $sidebar.hide();

        // ...stuff...

        $sidebar.css({
          'background-color': 'pink'
        });
    }
    ```

  - 对DOM查询使用级联的 `$('.sidebar ul')` 或 `$('.sidebar ul')`，[jsPerf](http://jsperf.com/jquery-find-vs-context-sel/16)
  - 对有作用域的jQuery对象查询使用 `find`

    ```javascript
    // bad
    $('.sidebar', 'ul').hide();

    // bad
    $('.sidebar').find('ul').hide();

    // good
    $('.sidebar ul').hide();

    // good
    $('.sidebar > ul').hide();

    // good (slower)
    $sidebar.find('ul');

    // good (faster)
    $($sidebar[0]).find('ul');
    ```

## 杂项 ##

- 尽量使用优雅的模版写法，避免多行字符串用\加换行的方式或使用+运算连接字符串这种难维护的编码方式；

## 常用词命名统一 ##
规则：

1. *可读性强，见名知义。*
1. 尽量不与 jQuery 社区已有的习惯冲突。
1. 尽量写全。不用缩写，除非是下面列表中约定的。

大家一起来，逐步完善。
- options    表示配置，与 jQuery 社区保持一致，不要用 config, opts 等
- active  表示当前，不要用 current 等
- active  表示当前，不要用 current 等
- index  表示索引，不要用 idx 等
- trigger  触点元素
- triggerType  触发类型、方式
- context  表示传入的 this 对象
- object  推荐写全，不推荐简写为 o, obj 等
- element 推荐写全，不推荐简写为 el, elem 等
- length  不要写成 len, l
- prev  previous 的缩写
- constructor  不能写成 ctor
- easing  表示动画平滑函数
- min  minimize 的缩写
- max  maximize 的缩写
- DOM  不要写成 dom, Dom
- .tpl  使用 tpl 后缀表示模版
- btn  button 的缩写

## JSHint配置 ##

待定

## ECMAScript 5兼容性 ##

- 参考[Kangax](https://twitter.com/kangax/)的 ES5 [compatibility table](http://kangax.github.com/es5-compat-table/)

## 性能 ##

- [On Layout & Web Performance](http://kellegous.com/j/2013/01/26/layout-performance/)
- [String vs Array Concat](http://jsperf.com/string-vs-array-concat/2)
- [Try/Catch Cost In a Loop](http://jsperf.com/try-catch-in-loop-cost)
- [Bang Function](http://jsperf.com/bang-function)
- [jQuery Find vs Context, Selector](http://jsperf.com/jquery-find-vs-context-sel/13)
- [innerHTML vs textContent for script text](http://jsperf.com/innerhtml-vs-textcontent-for-script-text)
- [Long String Concatenation](http://jsperf.com/ya-string-concat)
- Loading...

