tc = int(input())
for i in range(tc):
    num = sorted(list(map(int, input().split())))
    print(f"#{i+1} {num[len(num)-1]}")