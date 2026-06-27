#include<bits/stdc++.h>
using namespace std;

int dy[4] = {1, -1, 0, 0};
int dx[4] = {0, 0, -1, 1};

int solution(vector<vector<int> > maps) {    
    int n = maps.size();
    int m = maps[0].size();
    queue<pair<int, int>> q; // {y, x}
    q.push({0, 0}); // {0, 0}에서 시작
    
    while (!q.empty()) {
        int y = q.front().first;
        int x = q.front().second;
        q.pop();
        
        // 자리 이동
        for (int dir=0; dir<4; dir++) {
            int ny = y + dy[dir];
            int nx = x + dx[dir];

            if (ny < 0 || ny >=n || nx < 0 || nx >= m) {
                continue;
            }  
            
            if (maps[ny][nx] != 1) { // 0: 벽, 1>: 이미 방문
                continue;
            }

            maps[ny][nx] = maps[y][x] + 1;
            q.push({ny, nx});
        }
    }
    
    return (maps[n-1][m-1] == 1) ? -1 : maps[n-1][m-1];
}