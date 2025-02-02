def solution(arr, n):
    L = len(arr)
    if(L%2): # 배열의 길이가 짝수라면
        for i in range(0,L,2): arr[i]+=n
    else: # 배열의 길이가 홀수라면
        for i in range(1,L,2): arr[i]+=n
    return arr