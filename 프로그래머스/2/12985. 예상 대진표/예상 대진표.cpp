#include <iostream>
#include <bits/stdc++.h>

using namespace std;

int solution(int n, int a, int b) {
    int step = 0;
    while (a!=b) {
        a = (a+1)/2;
        b = (b+1)/2;
        
        step++;
    }
    
    return step;
}