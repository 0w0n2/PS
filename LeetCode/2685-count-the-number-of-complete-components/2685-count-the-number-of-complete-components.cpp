#include <bits/stdc++.h>

using namespace std;

class Solution {
public:
    // 노드가 k개인 graph의 간선 수 = k*(k-1)/2
    // degree 합 = 간선 수 * 2
    // 순환 조건: degree 합 == k*(k-1)  
    vector<int> par;

    int find(int x) {
        if (par[x] < 0) {
            return x;
        }
        return par[x] = find(par[x]);
    }

    void merge(int a, int b) {
        a = find(a);
        b = find(b);

        if (a==b) {
            return;
        }

        par[b] = a;
    }

    int countCompleteComponents(int n, vector<vector<int>>& edges) {
        par.assign(n, -1);
        
        for (const vector<int>& e : edges) {
            merge(e[0], e[1]);
        }

        vector<int> nodeCt(n, 0);
        vector<int> degreeCt(n, 0);

        for (int i=0; i<n; i++) {
            int root = find(i);
            nodeCt[root]++;
        }

        for (const vector<int>& e : edges) {
            int root = find(e[0]);
            degreeCt[root] += 2;
        }

        int ans = 0;
        for (int i=0; i<n; i++) {
            if (nodeCt[i] == 0) continue;
            if (degreeCt[i] == nodeCt[i] * (nodeCt[i]-1)) ans++;
        }

        return ans;
    }
};