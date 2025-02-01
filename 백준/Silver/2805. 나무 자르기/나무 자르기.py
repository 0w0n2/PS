n, m = map(int, input().split()) # n:나무개수, m:필요나무길이합
tree = list(map(int, input().split())) # 나무들의 길이

# 이분 탐색
def binarySearch(tree, start, end, target):
    while(start<=end): # 0~max까지 절단기에 설정할 수 있음
        mid = (start + end)//2
        nowSum = 0

        for t in tree:
            if (t>mid): nowSum+=t-mid

        if (nowSum>=target): start = mid+1
        elif (nowSum<target): end = mid-1
        
    return start-1

print(binarySearch(tree, 0, max(tree), m))