n = int(input()) # 회원수
user = []
for i in range(n):
    age, name = map(str, input().split())
    user.append((int(age), name))

user.sort(key = lambda x : x[0]) ## age만 비교

for i in user:
    print(i[0], i[1])