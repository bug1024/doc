
-- type()返回类型
print(type("fuck"));
print(type(print));
print(type(true));
print(type(1.0));
print(type(nil));

-- Lua 语言中“不等于”运算符的写法为：~=

local a = {x = 1, y = 0}
local b = {x = 1, y = 0}

if a == b then
    print("a = b")
else
    print("a ~= b")
end

-- a and b 如果 a 为 nil，则返回 a，否则返回 b
-- a or b 如果 a 为 nil，则返回 b，否则返回 a

-- 变长参数
local function func(...)
    local temp = {...}
    local ans  = table.concat(temp, "#")
    print(ans)
end
func(1, 2, 3)
func(5, 9, 'fuck')

-- 当函数参数是table类型时，传递进来的是实际参数的引用
local change(arg)
