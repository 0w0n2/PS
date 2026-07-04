#include <bits/stdc++.h>

using namespace std;

int solution(int n) {
    int total = 0;
    
    while (n>0) {
        if (n%2 == 1) {
            total++;
            n--;
        } else {
            n /= 2;
        }
    }

    return total;
}