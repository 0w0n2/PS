year = int(input())
if(year%4==0):
    if(year%400==0):print(str(1))
    elif(year%100!=0):print(str(1))
    else: print(str(0))
else: print(str(0))