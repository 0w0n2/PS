tc = int(input()) # 테스트케이스
for i in range(tc):
    num = list(map(int, input().split()))
    print(f"#{i+1} {int(round(sum(num)/len(num), 0))}")