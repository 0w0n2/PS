import sys
input = sys.stdin.readline

n, m = map(int, input().split()) # n : 듣도 못한 사람 수, m:보도 못한 사람 수
s1 = {input().strip() for _ in range(n)}
s2 = {input().strip() for _ in range(m)}
result = sorted(s1 & s2)
print(len(result))
for i in result:
    print(i)