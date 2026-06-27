#include <bits/stdc++.h>

using namespace std;

struct Node {
    int y, x, ct;
};

int n, m;
int dy[4] = {-1, 1, 0, 0}; // 상, 하, 좌, 우
int dx[4] = {0, 0, -1, 1};

Node goStraight(Node cur, int dir, const vector<string>& board) { // 벽(배열 밖 or 'D')이 나타날 때까지 직진
    while (true) {
        int ny = cur.y + dy[dir];
        int nx = cur.x + dx[dir];
        
        if (ny<0 || ny>=n || nx<0 || nx>=m || board[ny][nx] == 'D') {
            return {cur.y, cur.x, cur.ct + 1};
        }
        
        cur.y = ny;
        cur.x = nx;
    }
}

int solution(vector<string> board) {
    n = board.size();
    m = board[0].size();
    
    queue<Node> q;
    vector<vector<bool>> isVisited(n, vector<bool>(m, false));
    pair<int, int> goal;
    
    for (int y=0; y<n; y++) {
        for (int x=0; x<m; x++) {
            switch (board[y][x]) {
                case 'R': // 시작점
                    q.push({y, x, 0});
                    isVisited[y][x] = true;
                    break;
                case 'G':
                    goal = {y, x};
                    break;
            }
        }
    }
    
    while (!q.empty()) {
        Node cur = q.front();
        q.pop();
        
        if (cur.y == goal.first && cur.x == goal.second) { // 도착
            return cur.ct;
        }
        
        for (int dir=0; dir<4; dir++) {
            Node next = goStraight(cur, dir, board);
            
            if (isVisited[next.y][next.x]) {
                continue;
            }
            
            isVisited[next.y][next.x] = true;
            q.push(next);
        }
    }
    
    return -1;
}