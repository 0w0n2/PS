#include <bits/stdc++.h>

using namespace std;

class Solution {
public:
    vector<int> parent;

    int find(int x) {
        if (parent[x] == -1) {
            return x;
        }

        return parent[x] = find(parent[x]);
    }

    void merge(int a, int b) {
        a = find(a);
        b = find(b);

        if (a==b) {
            return;
        }

        parent[b] = a;
    }

    int minScore(int n, vector<vector<int>>& roads) {
        parent.resize(n+1);

        for (int i=1;i<=n;i++) {
            parent[i] = -1;
        }

        for (const vector<int>& r : roads) {
            merge(r[0], r[1]);
        }

        int root = find(1);
        int ans = INT_MAX;

        for (const vector<int>& r : roads) {
            if (find(r[0]) == root || find(r[1]) == root) {
                ans = min(ans, r[2]);
            }
        }

        return ans;
    }
};