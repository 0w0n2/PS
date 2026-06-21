#include <string>
#include <vector>

using namespace std;

// 오른쪽/아래쪽으로만 움직일 수 있으면 뒤로 돌아가는 우회가 없음
// 따라서 (1, 1)에서 (m, n)까지 도착하는 모든 경로는 무조건 
// 오른쪽 (m-1)번, 아래쪽 (n-1)을 사용하므로 *최단경로*라는 걸 구하는 건 무용지물
int solution(int m, int n, vector<vector<int>> puddles) {
    const int MOD = 1000000007;
    
    vector<vector<int>> dp(n+1, vector<int>(m+1, 0));
        vector<vector<bool>> isBlocked(n+1, vector<bool>(m+1, false));
        
        for (vector<int> puddle : puddles) {
            isBlocked[puddle[1]][puddle[0]] = true; // (y, x) 
        }
        
        dp[1][1] = 1; // 집이 있는 좌표
        for (int y=1; y<=n; y++) {
            for (int x=1; x<=m; x++) {
                if (isBlocked[y][x]) { // 물웅덩이 위치
                    dp[y][x] = 0;
                    continue;
                }
                
                if (y==1 && x==1) {
                    continue;
                }
                
                dp[y][x] = (dp[y-1][x] + dp[y][x-1]) % MOD ;
            }
        }
        
        return dp[n][m];
}