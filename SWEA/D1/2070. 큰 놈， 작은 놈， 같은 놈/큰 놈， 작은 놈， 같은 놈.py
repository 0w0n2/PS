tc = int(input()) # 테스트케이스
for i in range(tc):
    n1, n2 = map(int, input().split())
    if (n1<n2): print(f"#{i+1} <")
    elif (n1==n2): print(f"#{i+1} =")
    else : print(f"#{i+1} >")