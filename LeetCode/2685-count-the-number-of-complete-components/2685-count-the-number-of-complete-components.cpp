#include <bits/stdc++.h>

using namespace std;

class Solution {
public:
    // 노드가 k개인 graph의 간선 수 = k*(k-1)/2
    // degree 합 = 간선 수 * 2
    // 순환 조건: degree 합 == k*(k-1)  
    int countCompleteComponents(int n, vector<vector<int>>& edges) {
       vector<vector<int>> graph(n);
       for (const vector<int>& edge : edges) {
        int u = edge[0];
        int v = edge[1];

        graph[u].push_back(v);
        graph[v].push_back(u);
       } 

       vector<bool> isVisited(n, false);
       int ans = 0;

       for (int i=0; i<n; i++) {
            if (isVisited[i]) continue;

            int nodeCt = 0;
            int degreeCt = 0;

            queue<int> q;
            q.push(i);
            isVisited[i] = true;

            while (!q.empty()) {
                int cur = q.front(); q.pop();

                nodeCt++;
                degreeCt += graph[cur].size();

                for (int next : graph[cur]) {
                    if (isVisited[next]) continue;
                    isVisited[next] = true;
                    q.push(next);
                }
            }

            if (degreeCt == nodeCt * (nodeCt - 1)) ans++;
       }
       
       return ans;
    }
};