#include <bits/stdc++.h>

using namespace std;

const int dy[4] = {1, -1, 0, 0};
const int dx[4] = {0, 0, 1, -1};
const int INF = 1e9;
int n;

struct Cor{
    int y, x, dir, cost;
    
    bool operator < (const Cor& o) const { // cost 낮은 순서 우선
        return cost > o.cost;
    }
};

// (0, 0) -> (N-1, N-1)
int dijkstra(vector<vector<int>>& board) {
    priority_queue<Cor> pq;
    vector<vector<vector<int>>> dist(n, vector<vector<int>>(n, vector<int>(4, INF))); // [y][x][방향] = 최저 cost
    
    for (int i=0; i<4; i++) {
        dist[0][0][i] = 0;
        pq.push({0, 0, i, 0});
    }

    while (!pq.empty()) {
        Cor cur = pq.top(); pq.pop();
        
        if (cur.cost > dist[cur.y][cur.x][cur.dir]) continue;
        
        if (cur.y == n-1 && cur.x == n-1) return cur.cost;
        
        for (int nextDir=0; nextDir<4; nextDir++) {
            int ny = cur.y + dy[nextDir];
            int nx = cur.x + dx[nextDir];
            
            if (ny<0 || ny>=n || nx<0 || nx>=n || board[ny][nx]==1) continue;
            
            int nextCost = cur.cost + 100;  // 이동 기본값
            if (cur.dir != nextDir) { // 방향 전환
                nextCost += 500;
            }
            
            if (nextCost < dist[ny][nx][nextDir]) {
                dist[ny][nx][nextDir] = nextCost;
                pq.push({ny, nx, nextDir, nextCost});
            }
        }
    }
    
    return -1;
}

int solution(vector<vector<int>> board) {
    n = board.size();
    return dijkstra(board);
}