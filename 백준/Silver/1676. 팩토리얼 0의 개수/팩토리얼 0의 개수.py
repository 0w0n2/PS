n = int(input())
facfac = 1
for i in range(1, n+1):
    facfac*=i
facfac = str(facfac)[::-1]
trimfacfac = facfac.lstrip("0")
print(len(facfac)-len(trimfacfac))