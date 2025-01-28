import sys
input = sys.stdin.readline

tc = int(input())
num = [False]*20

for _ in range(tc):
    ip = list(input().split())

    if (ip[0]=="all"): num = [True]*20
    elif (ip[0]=="empty"): num = [False]*20
    else: 
        n = int(ip[1])
        if (ip[0]=="add"): num[n-1] = True
        elif (ip[0]=="remove"): num[n-1] = False
        elif (ip[0]=="check"): print(1 if num[n-1] else 0)
        elif (ip[0]=="toggle"): num[n-1] = not num[n-1]