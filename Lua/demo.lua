

-- 类C语言，大小写敏感

--[[
    Lua脚本是一个很轻量级的脚本，也是号称性能最高的脚本，用在很多需要性能的地方，比如：游戏脚本，nginx，wireshark的脚本，
    当你把他的源码下下来编译后，你会发现解释器居然不到200k，这是多么地变态啊（/bin/sh都要1M，MacOS平台），而且能和C语言非常好的互动。
--]]


-- 输出
print("老司机带带我我要去昆明")

-- 数字 只有double型
num1 = 1024
num2 = 3.0
num3 = 314.16e-2
num3 = 0xff
print(num1, num2, num3, num4)

-- while
sum = 0
num = 1
while num <= 100 do
    sum = sum + sum
    num = num + 1
end
print("sum = ", sum)

-- if else
age = 100
if age == 18 then
    print("成年人")
elseif age < 18 then
    print("too young, too simple")
else
    local age = io.read()
    print("你的年龄是"..age) -- ..表示字符串连接符
end

-- for
sum = 0
for i = 1, 100, 2 do -- for (i = 1; i <= 100; i = i + 2)
    sum = sum + i
end
print(sum)

-- until
sum = 2
repeat
    sum = sum ^ 2
    print(sum)
until sum > 1000

-- 函数
function myPower(x)
    return function(y) return y^x end
end
power2 = myPower(2)
power3 = myPower(3)
print(power2(4)) -- 4的2次方
print(power3(5)) -- 5的3次方

-- table
-- Lua中的变量，如果没有local关键字，全都是全局变量，Lua也是用Table来管理全局变量的，Lua把这些全局变量放在了一个叫“_G”的Table里。
map = {name="wangyu", age=25, hansome=false}
map.handsome = true
-- 遍历table
for k, v in pairs(map) do
    print(k, v)
end

arr = {"bug1024", 25, true}
-- 遍历array
for i = 1, #arr do -- #arr表示数组长度
    print(arr[i])
end

-- MetaTable MetaMethod
-- MetaTable主要用来做类似重载操作符的功能
--[[
    __add(a, b)                     对应表达式 a + b
    __sub(a, b)                     对应表达式 a - b
    __mul(a, b)                     对应表达式 a * b
    __div(a, b)                     对应表达式 a / b
    __mod(a, b)                     对应表达式 a % b
    __pow(a, b)                     对应表达式 a ^ b
    __unm(a)                        对应表达式 -a
    __concat(a, b)                  对应表达式 a .. b
    __len(a)                        对应表达式 #a
    __eq(a, b)                      对应表达式 a == b
    __lt(a, b)                      对应表达式 a < b
    __le(a, b)                      对应表达式 a <= b
    __index(a, b)                   对应表达式 a.b 类似js中的prototype
    __newindex(a, b, c)             对应表达式 a.b = c
    __call(a, ...)                  对应表达式 a(...)
--]]
a = {numberator=2, denominator=3}
b = {numberator=1, denominator=4}
fraction_op = {}
function fraction_op.__add(x, y)
    ret = {}
    ret.numberator = x.numberator * y.denominator + y.numberator * x.denominator
    ret.denominator = x.denominator * y.denominator
    return ret
end
setmetatable(a, fraction_op)
setmetatable(b, fraction_op)
c = a + b
for k, v in pairs(c) do
    print(k, v)
end

-- 模块化
local m = require("module")
m.sayHi()

