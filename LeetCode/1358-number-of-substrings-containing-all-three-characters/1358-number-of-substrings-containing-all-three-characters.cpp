#include <bits/stdc++.h>

class Solution {
public:
    int numberOfSubstrings(string s) {
        int left = 0;
        int ans = 0;
        int n = s.size();
        int ct[3] = {0, 0, 0};

        for (int right = 0; right < n; right++) {
            ct[s[right] - 'a']++;

            while (ct[0] > 0 && ct[1] > 0 && ct[2] > 0) {
                ans += n - right; // 현재 구간에 a, b, c가 모두 있다면 오른쪽을 더 늘린 문자열들도 전부 조건을 만족

                ct[s[left] - 'a']--; // 가장 왼쪽 알파벳 제거
                left++; // 포인터 오른쪽으로
            }
        }

        return ans;
    }
};