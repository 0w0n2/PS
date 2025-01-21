while(True):
    ip = input()
    if (ip=="# 0 0"): break
    name, age, weight = ip.split()
    print(f"{name} {'Senior' if int(age)>17 or int(weight)>=80 else 'Junior'}")