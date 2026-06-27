#include <bits/stdc++.h>

using namespace std;

/* 치환 가능한지 확인(단어 간 다른 char 1개) */
bool isChangeable(const string& begin, const string& target) {
    // string은 복사 비용이 있으니 읽기만 할 거면 참조로 받기
    
    int diff = 0;
    
    for (int i=0; i<begin.size(); i++) {
        if (begin[i] != target[i]) {
            diff++;
            
            if (diff > 1) {
                return false;
            }
        }
    }
    
    return true;
}

int solution(string begin, string target, vector<string> words) {
    // BFS
    queue<pair<string, int>> q; // 단어, 변환 횟수
    // pair: 값 2개를 묶는 자료형
    
    q.push({begin, 0});
    
    vector<bool> isVisited(words.size(), false);
    
    while(!q.empty()) {
        pair<string, int> cur = q.front();
        q.pop();
        
        if (cur.first == target) {
            return cur.second;
        }
        
        for (int i=0; i<words.size(); i++) {
            if (isVisited[i] || !isChangeable(cur.first, words[i])) continue;
            
            isVisited[i] = true;
            q.push({words[i], cur.second + 1});
        }
    }
    
    return 0;
}