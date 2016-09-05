-- 文件操作

local file = io.input("test.txt")
repeat
    line = io.read()
    if nil == line then
        break
    end
    print(line)
until(false)
io.close(file)

local file = io.open("test.txt", "a")
file:write("\nNginx Lua")

local file = io.open("test.txt", "r")
for line in file:lines() do
    print(line)
end
print(io.type(file))
file:close()
print(io.type(file))


