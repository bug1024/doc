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
