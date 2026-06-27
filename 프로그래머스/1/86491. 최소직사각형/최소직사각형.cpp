#include <bits/stdc++.h>

using namespace std;

int solution(vector<vector<int>> sizes) {
    int maxWidth = 0;
    int maxHeight = 0;
    
    for (vector<int> size : sizes) {
        int w = max(size[0], size[1]);
        int h = min(size[0], size[1]);
        
        maxWidth = max(w, maxWidth);
        maxHeight = max(h, maxHeight);
    }
    
    return maxWidth * maxHeight;
}