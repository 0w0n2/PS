#include <bits/stdc++.h>

using namespace std;

/**
- 1x1 크기의 칸들로 이루어진 직사각형 격자 형태의 미로에서 탈출
- 통로 또는 벽으로 구성(통로만 이동 가능)
- 문은 레버를 열어야 통과 가능(그냥 이동은 가능)
- 탈출하는데 필요한 최소 시간 리턴(불가능 시 -1)
- S: 시작지점, E: 출구, L: 레버, O: 통로, X: 벽
*/
int n, m;
const int dx[4] = {0, 0, -1, 1};
const int dy[4] = {-1, 1, 0, 0};

struct Cor {
    int y, x;
};

int bfs(Cor start, Cor goal, const vector<string>& maps) {
    vector<vector<int>> dist(n, vector<int>(m, -1)); // 방문 여부와 거리를 함께 관리
    queue<Cor> q;
    
    q.push(start);
    dist[start.y][start.x] = 0;
    
    while (!q.empty()) {
        Cor cur = q.front(); q.pop();
        
        if (cur.y == goal.y && cur.x == goal.x) {
            return dist[cur.y][cur.x];
        }
        
        for (int i=0; i<4; i++) {
            int ny = cur.y + dy[i];
            int nx = cur.x + dx[i];
            
            if (ny<0 || ny>=n || nx<0 || nx>=m || maps[ny][nx] == 'X' || dist[ny][nx] != -1) continue;
            
            dist[ny][nx] = dist[cur.y][cur.x] + 1;
            q.push({ny, nx});
        }
    }
    
    return -1; // 불가능
}

int solution(vector<string> maps) {
    Cor start, lever, goal;
    
    n = maps.size();
    m = maps[0].size();
    
    for (int i=0; i<n; i++) {
        for (int j=0; j<m; j++) {
            switch (maps[i][j]) {
                case 'S':
                    start = {i, j};
                    break;
                case 'L':
                    lever = {i, j};
                    break;
                case 'E':
                    goal = {i, j};
                    break;
            }
        }
    }
    
    int start2Lever = bfs(start, lever, maps);
    if (start2Lever == -1) { // 조기 종료(start->lever 도달 불가능)
        return -1;
    }
    
    int lever2Goal = bfs(lever, goal, maps);
    if (lever2Goal == -1) {
        return -1;
    }
    
    return start2Lever + lever2Goal;
}