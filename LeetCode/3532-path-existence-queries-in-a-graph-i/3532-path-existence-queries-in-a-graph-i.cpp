#include <bits/stdc++.h>
class Solution {
public:
    vector<int> par;

    int find(int x) {
        if (par[x] < 0) return x;
        return par[x] = find(par[x]);
    }

    void merge(int a, int b) {
        a = find(a);
        b = find(b);

        if (a == b) return;

        par[b] = a;
    }
    vector<bool> pathExistenceQueries(int n, vector<int>& nums, int maxDiff,
    vector<vector<int>>& queries) {
        par.assign(n, -1);

        for (int i=0; i<n-1; i++) {
            if (nums[i+1]-nums[i] <= maxDiff) {
                // nums는 정렬되어 있으므로 abs() 필요 X
                merge(i+1, i);
            }
        }

        vector<bool> answer;

        for (const vector<int>& q : queries) answer.push_back(find(q[0]) == find(q[1]));

        return answer;
    }
};