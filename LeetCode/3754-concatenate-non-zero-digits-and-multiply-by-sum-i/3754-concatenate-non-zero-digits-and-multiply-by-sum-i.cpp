#include <bits/stdc++.h>

using namespace std;

class Solution {
public:
    long long sumAndMultiply(int n) {
       long long totalSum = 0;
       string x = "";

       for (char c : to_string(n)) {
        if (c=='0') continue;

        totalSum += c - '0';
        x += c;
       }
       
       if (x.empty()) return 0;
       return stoll(x) * totalSum;
    }
};