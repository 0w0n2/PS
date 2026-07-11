/**
- 양방향 간선 / 방향 별 가중치 동일
- 합승을 하다가 각각 다시 탐(아예 합승 안 해도 됨)
- s에서 출발해서 각각의 도착 지점까지 택시를 타고 갈 때, 최저 예상 택시요금 계산

- 각 노드에서 A, B, 출발 지점까지 가는 최단 거리 계산 
*/
#include <bits/stdc++.h>

using namespace std;

struct Node {
    int v, weight;
    
    bool operator < (const Node& o) const {
        return weight > o.weight; // w 작은 노드 우선 
    }
};

const int INF = 1e9;

// 특정 노드에서 다른 모든 노드로 가는 최단 거리 계산
vector<int> dijkstra(int start, const vector<vector<Node>>& graph) {
    int n = graph.size()-1;
    vector<int> dist(n+1, INF);
    priority_queue<Node> pq;
    
    dist[start] = 0;
    pq.push({start, 0});
    
    while(!pq.empty()) {
        Node cur = pq.top(); pq.pop();
        
        if (cur.weight > dist[cur.v]) continue; // 무효값 넘김
        
        for (const Node& g : graph[cur.v]) {
            int nextWeight = cur.weight + g.weight;
            if (dist[g.v] > nextWeight) {
                dist[g.v] = nextWeight;
                pq.push({g.v, nextWeight});
            }
        }
    }
    
    return dist;
}

int solution(int n, int s, int a, int b, vector<vector<int>> fares) {
    vector<vector<Node>> graph(n+1);
    for (const vector<int>& f : fares) {
        int u = f[0];
        int v = f[1];
        int w = f[2];
        
        graph[u].push_back({v, w});
        graph[v].push_back({u, w});
    }
    
    vector<int> distS = dijkstra(s, graph);
    vector<int> distA = dijkstra(a, graph);
    vector<int> distB = dijkstra(b, graph);
    
    int answer = INF;
    for (int i=1; i<=n; i++) {
        if (distS[i] == INF || distA[i] == INF || distB[i] == INF) continue;
        answer = min(answer, distS[i] + distA[i] + distB[i]);
    }
    return answer;
}