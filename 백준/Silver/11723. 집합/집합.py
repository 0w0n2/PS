import sys

tc = int(sys.stdin.readline().rstrip())
num = [False]*20

for _ in range(tc):
    ip = sys.stdin.readline().rstrip()

    if (ip=="all"): num = [True]*20
    elif (ip=="empty"): num = [False]*20
    else: 
        c, n = ip.split()
        n = int(n)
        if (c=="add"): num[n-1] = True
        elif (c=="remove"): num[n-1] = False
        elif (c=="check"):
            print("1" if num[n-1] else "0")
        elif (c=="toggle"): num[n-1] = not num[n-1]