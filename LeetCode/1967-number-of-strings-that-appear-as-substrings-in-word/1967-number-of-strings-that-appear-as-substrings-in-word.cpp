#include <bits/stdc++.h>

using namespace std;

class Solution {
public:
    int numOfStrings(vector<string>& patterns, string word) {
        int ct = 0;
        for (const string& p : patterns) {
            if (word.find(p) != string::npos) {
                // string::npos
                // npos: no position
                // 문자열 내에서 특정 문자나 부분 문자열을 찾지 못했음을 나타내는 특수한 정수 상수
                ct++;
            }
        }

        return ct;
    }
};