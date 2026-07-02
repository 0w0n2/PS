#include <bits/stdc++.h>

using namespace std;

// (0,0)->(m-1, n-1) 이동(4방)
// grid[i][j]=1 에 닿으면 피 1 깎임
// 피>0 인 채로 (m-1, n-1) 갈 수 있으면 true 리턴, 못 가면 false
class Solution {
public:
    struct People {
        int y, x, health;
    };

    bool findSafeWalk(vector<vector<int>>& grid, int health) {
        int dy[4] = {-1, 1, 0, 0};
        int dx[4] = {0, 0, -1, 1};

        int m = grid.size();
        int n = grid[0].size();

        int startHealth = health - grid[0][0];

        if (startHealth <= 0) {
            return false;
        }

        // 해당 칸을 방문할 수 있는 최대 피 상태
        vector<vector<int>> dp(m, vector<int>(n, -1)); 
        queue<People> q;

        q.push({0, 0, startHealth});
        dp[0][0] = startHealth;

        while (!q.empty()) {
            People cur = q.front(); q.pop();
            
            if (cur.y == m-1 && cur.x == n-1) {
                return true;
            }

            for (int dir=0; dir<4; dir++) {
                int ny = cur.y + dy[dir];
                int nx = cur.x + dx[dir];

                if (ny<0 || ny>=m || nx<0 || nx>=n) {
                    continue;
                }

                int nextHealth = cur.health - grid[ny][nx];

                if (nextHealth <= 0 || dp[ny][nx] >= nextHealth) {
                    continue;
                }

                dp[ny][nx] = nextHealth;
                q.push({ny, nx, nextHealth});    
            }
        }

        return false;
    }
};