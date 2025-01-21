while(True):
    ip = input()
    if (ip=="# 0 0"): break
    name, age, weight = ip.split()
    age, weight = map(int, [age, weight])
    if(age>17 or weight>=80): print(f'{name} Senior')
    else: print(f'{name} Junior')