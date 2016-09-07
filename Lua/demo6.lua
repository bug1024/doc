-- 元表
arr = {1, 2, 3}
-- 重写__tostring方法
arr = setmetatable(arr, {
    __tostring = function(self)
        return table.concat(arr, ',')
    end;
})
print(arr)

function isEmptyTable(t)
    if t == nil or next(t) == nil then
        return true
    else
        return false
    end
end

print(isEmptyTable(arr))

-- 虚变量
local start, finish = string.find("Hello World", "or")
print(start, finish)

local _, finish = string.find("Hello World", "or")
print(finish)

-- 冒号操作会带入一个self参数，用来代表自己。而点号操作，只是内容的展开
local str = "fuck abc"
print(str:sub(1, 2))
print(str.sub(1, 2))

obj = {x = 110}
function obj:fun1()
    print(self.x)
end
obj:fun1() -- obj.fun1()会报错

-- luajit
local ffi = require("ffi")
ffi.cdef[[
    int printf(const char *fmt, ...);
]]
ffi.C.printf("fuck %s", "you")

