word = ['a', 'A', 'e', 'E', 'i', 'I', 'o', 'O', 'u', 'U']
while(True):
    ip=input()
    if(ip=="#"): break
    sum = 0
    for i in range(len(word)):
        sum += ip.count(word[i])
    print(sum)