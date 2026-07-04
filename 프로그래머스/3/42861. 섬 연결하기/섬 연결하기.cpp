#include <bits/stdc++.h>

using namespace std;

// mst

vector<int> par;

int find(int x) {
    if (par[x] < 0) return x;
    return par[x] = find(par[x]);
}

bool merge(int a, int b) {
    a = find(a);
    b = find(b);
    
    if (a==b) {
        return false;
    }
    
    par[b] = a;
    
    return true;
}

struct Edge {
    int u, v, w;
    
    Edge(int u, int v, int w) {
        this->u = u;
        this->v = v;
        this->w = w;
    }
    
    bool operator<(const Edge& O) const {
        return w < O.w;
    }
};

int solution(int n, vector<vector<int>> costs) {
    vector<Edge> edges;
    
    for (const vector<int>& cost : costs) {
        edges.push_back(Edge(cost[0], cost[1], cost[2]));
    }
    
    sort(edges.begin(), edges.end());
    
    par.assign(n, -1);
    
    int result = 0, ct=0;
    
    for (const Edge& edge : edges) {
        if (merge(edge.u, edge.v)) {
            result += edge.w;
            ct++;
            
            if (ct==n-1) {
                break;
            }
        }
    }
    
    return result;
}