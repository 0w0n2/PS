mul = str(int(input()) * int(input()) * int(input()))
num = [0]*10 # 0 ~ 9까지
for i in mul:
    num[int(i)]+=1
for i in num:
    print(i)