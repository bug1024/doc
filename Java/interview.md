# one
* 迷宫寻路算法
* 100个犯人黑白帽子
    * 最后一个人如果看到奇数顶帽子报“黑”否则报“白”，他可能死
    * 其他人记住这个值（实际是黑帽奇偶数），在此之后当再听到黑时，取反一次
    * 从倒数第二人开始，就有两个信息：记住的值与看到的值，相同报“白”，不同报“黑”
    * 99人能100%活，1人50%能活
* equals和==的区别与使用场景？
    * == 用于基本数据类型的比较，判断引用是否指向堆内存的同一块地址
    * equals 用于判断两个变量是否是对同一个对象的引用，即堆中的内容是否相同，返回值为布尔类型
* switch语句的条件是否可以使用字符串类型
    * 取值只能是整型或者可以转换为整型的数值类型，比如byte、short、int、char、还有枚举；需要强调的是：long和String类型是不能作?在switch语句上的
* byte数组和char数组的区别？
    * char是unicode编码，占两个字节，一个字节占计算机里的8位(bit)
* HashMap Hashtable ConcurrentHashMap 区别以及实现？
    * HashMap是非同步的，单线程情况下效率很高
    * Hashtable和ConcurrentHashMap是同步的
    * Hashtable使用syncronized，ConcurrentHashMap使用ReentrantLock
* ACID是什么？
    * 指数据库事务正确执行的四个基本要素的缩写
    * 原子性（Atomicity）
    * 一致性（Consistency）
    * 隔离性（Isolation）
    * 持久性（Durability）
* 如何保证代码质量?
    * 单元测试
    * 集成测试
    * code review
    * 日常/预发/线上
    * 版本控制与快速回滚
* 主要负责哪一块业务？是否有高并发的场景？比较有难度的项目是什么？


