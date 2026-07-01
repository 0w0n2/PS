#include <bits/stdc++.h>

using namespace std;

// 양방향으로 통행할 수 있는 도로로 연결
// 도로별로 걸리는 시간(1~2000)이 다름
// 1번 마을에서 각 마을(1~50개)로 음식 배달을 한다
// 각 마을로부터 K 시간이하로 배달이 가능한 마을에서만 주문을 받는다
// return: 음식 주문을 받을 수 있는 마을의 개수

struct Edge {
    int to, cost;
};

struct Node {
    int node, dist;
    
    bool operator<(const Node& o) const {
        return dist > o.dist; // dist가 더 작은 것이 앞으로
    }
};

vector<int> dijkstra(const vector<vector<Edge>>& map, int n, int K) {
    const int INF = 1e9;
    
    vector<int> dist(n+1, INF);
    
    priority_queue<Node> pq; 
    pq.push({1, 0});
    dist[1] = 0;
    
    while (!pq.empty()) {
        Node cur = pq.top(); pq.pop();
        
        if (cur.dist > dist[cur.node]) {
            continue;
        }
        
        for (const Edge& edge : map[cur.node]) {
            int nextDist = edge.cost + cur.dist;
            if (dist[edge.to] > nextDist && nextDist <= K) {
                dist[edge.to] = nextDist;
                pq.push({edge.to, nextDist});
            } 
        }
    }
    
    return dist;
}

int solution(int N, vector<vector<int> > road, int K) {
    
    vector<vector<Edge>> map(N+1);
    for (const vector<int>& r : road) {
        int u = r[0];
        int v = r[1];
        int w = r[2];
        
        // 양방향 도로
        map[u].push_back({v, w});
        map[v].push_back({u, w});
    }
    
    vector<int> dist = dijkstra(map, N, K);
    
    int ct = 0;
    for (int i=1; i<=N; i++) {
        if (dist[i] <= K) ct++;
    }

    return ct;
}
