tc = int(input()) # 테스트케이스 개수
for i in range(tc):
    num = list(map(int, input().split()))
    sum = 0
    for n in num:
        if (n%2==1): sum+=n
    print(f"#{i+1} {sum}")