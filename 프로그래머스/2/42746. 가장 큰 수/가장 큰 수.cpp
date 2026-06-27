#include <bits/stdc++.h>

using namespace std;

string solution(vector<int> numbers) {
    vector<string> nums;
    
    for (int n : numbers) {
        nums.push_back(to_string(n));
    }
    
    sort(nums.begin(), nums.end(), [](const string& a, const string& b) {
        return a+b > b+a; // a+b 이렇게 이었을 때 b+a보다 큰 순으로 정렬
    });
    
    if (nums[0] == "0") {
        return "0";
    }
    
    string ans = "";
    
    for (const string& n : nums) {
        ans += n;
    }
    
    return ans;
}