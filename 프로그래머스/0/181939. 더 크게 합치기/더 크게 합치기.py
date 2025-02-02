def solution(a, b):
    s1 = int(str(a)+str(b))
    s2 = int(str(b)+str(a))
    return s1 if s1>=s2 else s2