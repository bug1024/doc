# Linux

## Cgroup

 - 计算资源
 - 内存资源
 - IO资源
 - 网络资源

## 进程
 - 具有独立功能的程序，关于某个数据集合的一次运行过程
 - 进程 = 程序 + 数据+ PCB
 - 3个基本状态：ready(就绪等cpu)/running(运行)/waiting(等待事件的发生)
 - PCB：进程存在的唯一标志
 - 调度：状态转换时发生调度，IO密集型/CPU密集型，进程树，overhead(系统消耗)
 - IPC：管道/共享内存(生产者/消费者)/消息传递(send/recv)/信号量/套接字，busy waiting

## CPU调度
 - FCFS(First-Come First-Served)
 - SJF(Shortest-Job-First)，preemptive(抢占式)
 - Priority Scheduling
 - Round Robin
 - Multilevel  Queue Scheduling
 - Multilevel Feedback Queues

## 进程同步
 - entry section, critical section, exit section, remainder section
 - 临界区使用原则：互斥(忙则等待)，空闲让进，有限等待，让权等待
 - semaphore: wait()用于申请资源, signal()用于释放资源
 - 生产者消费者问题，读者写者问题，哲学家进餐问题
 - 管程

## 死锁
 - 两个或两个以上的进程由于竞争资源导致系统无法推进
 - 产生必要条件：互斥，占有必等待，非抢占，循环等待
 - 银行家算法(allocation,max,available)

## 内存管理
 - swapping, paging, segmentation
 - 单CPU需要两个寄存器（基础+界限）
 - 逻辑地址 物理地址 重定位寄存器
 - 连续分配：固定分区，可变分区
 - 离散分配：分页(页=>页框)，分段，段页

## 虚拟存储
 - demand paging
 - page replacement
 - allocation of frames
 - thrashing(抖动)


## 监控指标
 - load 特定事时间间隔内运行队列中的平均线程数
 - user time CPU执行用户进程所占用的时间
 - system time CPU在内核所花费的时间
 - nice time 系统在调整进程优先级的时候所花费的时间
 - idle time 系统空闲等待进程运行的时间
 - waiting time CPU在等待IO操作所花费的时间
 - hard irq time 系统处理硬件中断所占用的时间
 - soft irq time 系统处理软件中断所占用的时间
 - steal time 被强制等待虚拟机CPU的时间，占比较高表示当前虚拟机与该宿主其他虚拟机争用CPU频繁
 - 磁盘剩余空间 使用df和du命令查看
 - 网络traffic 使用sar命令查看 sar -n DEV 1 1
 - 磁盘IO 使用iostat命令查看 iostat -d -k
 - 内存使用 使用free命令查看
 - swap IO 使用vmstat命令查看
