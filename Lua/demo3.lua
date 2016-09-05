
-- String库

-- 返回字符 s[i]、s[i + 1]、s[i + 2]、······、s[j] 所对应的 ASCII 码。i 的默认值为 1，即第一个字节,j 的默认值为 i
print(string.byte("abc", 1, 3))
print(string.byte("abc", 3))
print(string.byte("abc"))

-- 接收 0 个或更多的整数（整数范围 ：0~255）,返回这些整数所对应的 ASCII 码字符组成的字符串。当参数为空时，默认是一个 
print(string.char(96, 97, 98))
print(string.char())
print(string.char(65, 66))

print(string.lower("FUCK"))
print(string.upper("fuck"))
print(string.len("fuck you")) -- 使用此函数是不推荐的。应当总是使用 # 运算符来获取 Lua 字符串的长度
print(#"fuck you") -- 由于 Lua 字符串的长度是专门存放的，并不需要像 C 字符串那样即时计算，因此获取字符串长度的操作总是 O(1) 的时间复杂度

print(string.find("abcd def", "d"))
print(string.find("abcd def", "d", 5))

print(string.format("%.4f", 4.1415926))

print(string.match("hello lua", "lua"))
print(string.match("hello lua 2016/9/5", "%d+/%d+/%d+")) -- string.match 目前并不能被 JIT 编译，应 尽量 使用 ngx_lua 模块提供的 ngx.re.match 等接口

print(string.rep("fuck", 3)) -- copty three times

print(string.sub("fuck you", 1))
print(string.sub("fuck you", 1, 2))
print(string.sub("fuck you", -3, -1))

print(string.gsub("fuck you", "u", "*"))

print(string.reverse("ooxx"))

