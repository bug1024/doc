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
 - IPC：共享内存(生产者/消费者)/消息传递(send/recv)，busy waiting

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
