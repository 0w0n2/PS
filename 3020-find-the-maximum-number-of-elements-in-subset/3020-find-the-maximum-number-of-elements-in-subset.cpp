// 조건을 만족하는 부분수열의 최대 원소 개수 리턴
// 조건: x, x^2, x^4..., x^2, x 식의 구성(2, 4, 16, 4, 2) x^3이 아닌 x^4 주의
#include <bits/stdc++.h>

using namespace std;

class Solution {
public:
    int maximumLength(vector<int>& nums) {
        unordered_map<long long, int> num2ct; // 숫자 별 개수

        num2ct.reserve(nums.size() * 2);
        num2ct.max_load_factor(0.7);

        for (int num : nums) {
            num2ct[num]++;
        }

        int answer = 1; // 1개 원소 부분수열

        // 1만으로 채워진 부분수열
        if (num2ct.count(1) > 0) {
            answer = (num2ct[1] % 2 == 1) ? num2ct[1] : num2ct[1] - 1;
        }

        // 그 외 부분수열 계산
        for (const auto& [num, ct] : num2ct) {
            if (num == 1) {
                continue;
            }

            long long cur = num;
            int length = 0;

            while (true) {
                // key=cur인 원소의 위치를 반환
                // 없으면 .end()를 반환
                auto it = num2ct.find(cur); // iterator

                // pair<const long long, int>
                // -> first = key, secont = value
                // 그런데 it는 pair 자체가 아니라 pair를 가리키는 iterator라서 it.second 이렇겐 못 부르고
                // it->second (== (*it).second) 식으로 가져와야 함

                if (it == num2ct.end()) { // key=cur인 원소가 없음(num2ct.end()) -> 이어갈 수 없다
                    length--;
                    break;
                }

                if (it->second == 1) { // value=1, 이 숫자 개수 1개일 때 -> 탈출
                    length++;
                    break;
                } 

                length += 2;
                cur *= cur;
            }

            answer = max(answer, length);
        }

        return answer;
    }
};