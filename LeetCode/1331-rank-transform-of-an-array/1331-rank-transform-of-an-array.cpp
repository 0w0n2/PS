#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    vector<int> arrayRankTransform(vector<int>& arr) {
        vector<int> sorted = arr;
        sort(sorted.begin(), sorted.end());

        unordered_map<int, int> rank;
        int curRank = 1;
        for (int num : sorted) {
            if (rank.count(num)) continue;
            rank[num] = curRank;
            curRank++;
        }
        
        vector<int> ans;
        for (int num : arr) {
            ans.push_back(rank[num]);
        }

        return ans;
    }
};