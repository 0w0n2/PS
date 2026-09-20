#include <iostream>
#include <bits/stdc++.h>

using namespace std;

int solution(int n, int a, int b) {
    // (a-1)XOR(b-1)에서 가장 높은 1비트가 만나는 라운드 결정
    // 000 - 001 
    int x = (a-1) ^ (b-1); // xor
    
    int step = 0;
    while (x) { // x!=0 될 때까지 가장 높은 1비트를 찾아서
        step++;
        x >>= 1;
    }
    
    return step;
}