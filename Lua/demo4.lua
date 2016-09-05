
-- Table 库
-- 不要在 lua 的 table 中使用 nil 值，如果一个元素要删除，直接 remove，不要用 nil 去代替。
local tb1 = {1, a = 2, 3}
print(table.getn(tb1))

local tb2 = {1, nil}
print(table.getn(tb2))

local tb3 = {1, nil , 2, nil}
print(table.getn(tb3))


local a = {1, 2, 3, "fuck"}
print(table.concat(tb1))
print(table.concat(a))

table.insert(a, 1, "you") -- 在表索引为1处插入3
table.remove(a) -- 默认是删除表的最后一个元素
table.remove(a, 2)
print(table.concat(a))
print(table.maxn(a))

local b = {1, 4, 3, 6, 2, 5}
table.sort(b)
for v in pairs(b) do
    print(v)
end

-- 日期时间
local t1 = os.time()
local a = {year = 2016, month = 9, day = 4, hour = 17, min = 30}
local t2 = os.time(a)
print(t1, t2, os.difftime(t2, t1))
print(os.date("today is %A, in %B"))
print(os.date("now is %x %X"))

-- 数学函数
print(math.min(1, 2, 3))
print(math.max(1, 2, 3))
math.randomseed(os.time()) -- 伪随机数种子
print(math.random(1, 100))
print(math.abs(-99))
print(math.floor(6.7))
print(math.ceil(6.7))
print(math.pi)

