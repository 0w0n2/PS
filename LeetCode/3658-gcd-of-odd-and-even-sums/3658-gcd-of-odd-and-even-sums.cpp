#include <bits/stdc++.h>

using namespace std;

class Solution {
public:
    int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a%b);
    }

    int gcdOfOddEvenSums(int n) {
        int sumOdd = n*n; // 홀수의 합
        int sumEven = n*(n+1); // 짝수의 합

        return gcd(sumOdd, sumEven);    
    }
};