#include <bits/stdc++.h>

using namespace std;

class Solution {
public:
    vector<int> twoSum(vector<int>& nums, int target) {
        unordered_map<int, int> val2Idx;

        for (int i=0; i<nums.size(); i++) {
            int need = target - nums[i];

            if (val2Idx.count(need)) {
                return {val2Idx[need], i};
            }

            val2Idx[nums[i]] = i;
        }

        return {};
    }
};