while(True):
    ip=input()
    if(ip=="#"): break
    print(sum(map(ip.count, 'aAeEiIoOuU')))