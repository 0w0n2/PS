#include <bits/stdc++.h>

using namespace std;

int goHome(const vector<int>& money, int start, int end) {
    int f1 = 0; // dp[i-1]
    int f2 = 0; // dp[i-2]
    
    for (int i=start; i<=end; i++) {
        int cur = max(f1, f2 + money[i]); // 이 집을 안 턴다 : 턴다
        f2 = f1;
        f1 = cur;
    }
    
    return f1;
}

int solution(vector<int> money) {
    return max(goHome(money, 0, money.size()-2), goHome(money, 1, money.size()-1)); // 첫번째 집부터 털거나 두번째 집부터 털거나
}