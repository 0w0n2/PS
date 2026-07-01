#include <bits/stdc++.h>

using namespace std;

class Solution {
public:
    struct Node {
        int y, x, safeCt;

        Node(int y, int x, int safeCt) {
            this->y = y;
            this->x = x;
            this->safeCt = safeCt;
        }

        bool operator<(const Node& o) const {
            // 다른 Node o와 비교하는데 o도 수정하지 않고, 나 자신도 수정하지 않음
            return safeCt < o.safeCt;
            // 큰 safeCt부터 나오게 정렬
        }
    };

    int maximumSafenessFactor(vector<vector<int>>& grid) {
        int n = grid.size();

        int dy[4] = {-1, 1, 0, 0};
        int dx[4] = {0, 0, -1, 1};

        // 칸 별 가장 가까운 도둑과의 거리 계산
        vector<vector<int>> dist(n, vector<int>(n, -1));
        
        queue<pair<int, int>> q; // {y, x}

        for (int y=0; y<n; y++) {
            for (int x=0; x<n; x++) {
                if (grid[y][x] == 1) {
                    dist[y][x] = 0;
                    q.push({y, x});
                }
            }
        }

        while (!q.empty()) {
            auto [y, x] = q.front(); q.pop();

            for (int dir=0; dir<4; dir++) {
                int ny = y + dy[dir];
                int nx = x + dx[dir];

                if (ny<0 || ny>=n || nx<0 || nx>=n || dist[ny][nx]!=-1) {
                    continue;
                }

                dist[ny][nx] = dist[y][x] + 1;
                q.push({ny, nx});
            }
        }

        // 도둑과의 거리가 최대가 되는 경로 탐색
        priority_queue<Node> pq;
        vector<vector<bool>> isVisited(n, vector<bool>(n, false));

        pq.push({0, 0, dist[0][0]});
        isVisited[0][0] = true;

        while (!pq.empty()) {
            Node cur = pq.top(); pq.pop();

            if (cur.y == n-1 && cur.x == n-1) {
                return cur.safeCt;
            }

            for (int dir=0; dir<4; dir++) {
                int ny = cur.y + dy[dir];
                int nx = cur.x + dx[dir];

                if (ny<0 || ny>=n || nx<0 || nx>=n || isVisited[ny][nx]) {
                    continue;
                }

                isVisited[ny][nx] = true;

                int nextSafeCt = min(cur.safeCt, dist[ny][nx]);
                pq.push({ny, nx, nextSafeCt});
            }
        }

        return 0;
    }
};