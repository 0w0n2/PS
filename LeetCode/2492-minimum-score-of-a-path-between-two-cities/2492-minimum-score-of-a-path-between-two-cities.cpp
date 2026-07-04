#include <bits/stdc++.h>

using namespace std;

class Solution {
public:
    struct Node {
        int v, dist;
    };

    int minScore(int n, vector<vector<int>>& roads) {
        vector<vector<Node>> map(n+1);

        for (const vector<int>& r : roads) {
            int u = r[0];
            int v = r[1];
            int d = r[2];

            map[u].push_back({v, d});
            map[v].push_back({u, d});
        }

        int ans = INT_MAX;
        vector<bool> isVisited(n+1, false);
        isVisited[1] = true;

        queue<int> q;
        q.push(1);

        // 갈 수 있는 모든 경로를 탐색하면서 최솟값 갱신
        while (!q.empty()) {
            int cur = q.front(); q.pop();

            for (const Node& nextNode : map[cur]) {
                ans = min(ans, nextNode.dist);

                if (isVisited[nextNode.v]) {
                    continue;
                }

                isVisited[nextNode.v] = true;
                q.push(nextNode.v);
            }
        }

        return ans;
    }
};