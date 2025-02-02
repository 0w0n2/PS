tc = int(input())
for step in range(tc):
    n = int(input())  # 학생 수
    num = list(map(int, input().split()))  # 점수 리스트로 변환
    maxNum = -1
    maxCount = 0
    for i in range(0, 101):  # 점수 범위 0~100
        if num.count(i) >= maxCount:  # 현재 점수 빈도가 최댓값보다 크면
            maxCount = num.count(i)
            maxNum = max(maxNum, i)
    print(f"#{step + 1} {maxNum}")