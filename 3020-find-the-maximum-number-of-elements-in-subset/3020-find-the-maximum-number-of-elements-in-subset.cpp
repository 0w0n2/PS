// 조건을 만족하는 부분수열의 최대 원소 개수 리턴
// 조건: x, x^2, x^4..., x^2, x 식의 구성(2, 4, 16, 4, 2) x^3이 아닌 x^4 주의
#include <bits/stdc++.h>

using namespace std;

class Solution {
public:
    int maximumLength(vector<int>& nums) {
        unordered_map<long long, int> num2ct; // 숫자 별 개수

        int oneCt = 0;
        for (int num : nums) {
            if (num == 1) { // 1 아닌 것만 담기
                oneCt++;
            } else {
                num2ct[num]++;
            }
        }

        int answer = 1; // 1개 원소 부분수열

        // 1만으로 채워진 부분수열
        if (oneCt > 0) {
            answer = (oneCt % 2 == 1) ? oneCt : oneCt - 1;
        }

        // 그 외 부분수열 계산
        for (auto [num, ct] : num2ct) {
            long long cur = num;
            int length = 0;

            while (num2ct.count(cur) && num2ct[cur] >= 2) { // .count() => HashMap.containsKey()
                length += 2;
                cur *= cur; // (x^2)^2
            }

            if (cur != -1 && num2ct.count(cur) && num2ct[cur] >= 1) { 
                length += 1;
            } else {
                length -= 1; // 여기서 끝
            }

            answer = max(answer, length);
        }

        return answer;
    }
};