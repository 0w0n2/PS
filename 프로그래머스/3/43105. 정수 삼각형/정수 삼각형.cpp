#include <vector>
#include <algorithm>

using namespace std;

int solution(vector<vector<int>> triangle) {
    int n = triangle. size();
    
    // 각 위치에 도달하기까지 최댓값을 저장
    for (int r=1; r<n; r++) {
        for (int c=0; c<=r; c++) {
            if (c==0) { // 매 행의 제일 왼쪽
                triangle[r][c] += triangle[r-1][c];
            } else if (c==r) { // 매 행의 제일 오른쪽
                triangle[r][c] += triangle[r-1][c-1];
            } else {
                triangle[r][c] += max(triangle[r-1][c-1], triangle[r-1][c]); // 양 옆 중 최대
            }
        }
    }
    
    // 막 행 중 최댓값
    return *max_element(triangle[n-1].begin(), triangle[n-1].end());
}