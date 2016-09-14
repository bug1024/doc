# JS组件开发模板

```
    function Widget() {
        this.bindingBox = null;
    }
    Widget.prototype = {
        //注册事件
        on : function(type, handler) {
            if (typeof this.handlers[type] == 'undefined') {
                this.handlers[type] = [];
            }
            this.handlers[type].push(handler);
            return this;
        },
        //触发事件
        trigger : function(type, data) {
            if (this.handlers[type] instanceof Array) {
                var handlers = this.handlers[type]
                    length   = handlers.length;
                for (var i = 0; i < length; i++) {
                    handlers[i](data);
                }
            }
        },
        //渲染组件
        render : function(container) {
            this.renderUI();
            this.handlers = {};
            this.bindUI();
            this.initUI();
            $(container || body.document).append(this.bindingBox);
        },
        //销毁组件
        destory : function() {
            this.destructor();
            this.bindingBox.off();
            this.bindingBox.remove();
        },
        //以下4个方法需要重写
        //渲染UI
        renderUI : function() {},
        //绑定UI事件
        bindUI : function() {},
        //初始化UI
        initUI : function() {},
        //析构
        destructor : function() {}
    };
```
