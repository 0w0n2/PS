#include <bits/stdc++.h>

using namespace std;

vector<int> nums;
int targetValue;

int recursive(int sum, int idx);

int solution(vector<int> numbers, int target) {
    nums = numbers; // ::는 전역을 의미
    targetValue = target;
    
    return recursive(0, 0);
}

int recursive(int sum, int idx) {
    if (idx == nums.size()) { // 기저조건
        if (sum == targetValue) {
            return 1;
        }
        
        return 0;
    }
    
    int res = recursive(sum + nums[idx], idx + 1);
    res += recursive(sum - nums[idx], idx + 1);

    return res;
}