#include <bits/stdc++.h>

using namespace std;

int solution(vector<string> want, vector<int> number, vector<string> discount) {
    unordered_map<string, int> cur;
    
    // 첫 10일 초기화
    for (int i=0; i<10; i++) {
        cur[discount[i]]++; 
    }
    
    
    int ans = 0;
    
    for (int start=0; start<=discount.size()-10; start++) {
        bool isPossible = true;
        
        for (int i=0; i<want.size(); i++) {
            if (cur[want[i]] != number[i]) {
                isPossible = false;
                break;
            }
        }
        
        if (isPossible) ans++;
        
        if (start + 10 < discount.size()) {
            cur[discount[start]]--;
            cur[discount[start + 10]]++;
        }
    }
    
    return ans;
}