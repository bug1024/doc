# 调试工具

 - tcpdump -i eth2 -w capture.cap tcp port 9501 and dst host 172.16.1.31

 - strace -o strace.log -tt -f -e {call} -p {pid}
