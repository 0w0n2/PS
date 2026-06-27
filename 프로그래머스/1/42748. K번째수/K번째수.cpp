#include <bits/stdc++.h>

using namespace std;

vector<int> solution(vector<int> array, vector<vector<int>> commands) {
    vector<int> answer;
    
    for (vector<int> cmd : commands) {        
        int start = cmd[0] - 1;
        int end = cmd[1]; // 포함하지 않는 끝 위치
        int k = cmd[2] - 1;
        
        vector<int> sliced(array.begin() + start, array.begin() + end);
        sort(sliced.begin(), sliced.end());
        
        answer.push_back(sliced[k]);
    }
    
    return answer;
}