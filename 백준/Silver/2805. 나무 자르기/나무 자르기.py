import bisect

n, m = map(int, input().split()) # n:나무개수, m:필요나무길이합
tree = sorted(list(map(int, input().split()))) # 나무들의 길이

# 다른 사람 코드 보고 고침 https://www.acmicpc.net/source/89348501
# 누적합 계산
prefix = [0]*(n+1)
for i in range(1, n+1):
    prefix[i] = prefix[i-1] + tree[i-1]

# 이분 탐색
def binarySearch(start, end):
    while(start<=end): # 0~max까지 절단기에 설정할 수 있음
        mid = (start + end)//2
        idx = bisect.bisect_right(tree, mid) # tree 내에서 mid 보다 큰 구간의 시작
        # tree[idx] 이상인 tree만 절단기에 잘리기 가능
        # 모든 나무들의 합 : prefix[n]
        # 안 잘리는 나무들의 합 : prefix[idx]
        # 잘리는 나무들의 합 : prefix[n] - prefix[idx]
        # 나무 하나당 잘리는 길이 : mid
        # 잘리는 나무들의 모든 잘리는 길이 합 : mid * 개수 = mid * (n-idx)
        nowSum = prefix[n] - prefix[idx] - mid*(n-idx) 
        if (nowSum>=m): start = mid+1
        else: end = mid-1
        
    return start-1

print(binarySearch(0, max(tree)))