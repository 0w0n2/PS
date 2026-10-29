import java.util.*;

class Solution {
    final private int[] dx = new int[]{0, 0, 1, -1};
    final private int[] dy = new int[]{1, -1, 0, 0};
    
    private boolean[][] isVisited;
    private int n, m;
    
    public int[] solution(String[] maps) {
        n = maps.length;
        m = maps[0].length();
        
        isVisited = new boolean[n][m];
        ArrayList<Integer> ansList = new ArrayList<>();
        
        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {
                if (maps[i].charAt(j) == 'X' || isVisited[i][j]) {
                    continue;
                }
                
                ansList.add(bfs(i, j, maps));
            }
        }
        
        if (ansList.isEmpty()) {
            return new int[]{-1};
        }
        
        int[] ans = new int[ansList.size()];
        
        for (int i=0; i<ansList.size(); i++) {
            ans[i] = ansList.get(i);
        }
        
        Arrays.sort(ans);
        
        return ans;
    }
    
    private int bfs(int i, int j, String[] maps) {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{i, j});
        isVisited[i][j] = true;
        
        int totalSum = 0;
        
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            totalSum += maps[cur[0]].charAt(cur[1]) - '0';
            
            for (int k=0; k<4; k++) {
                int nx = cur[0] + dx[k];
                int ny = cur[1] + dy[k];
                
                if (nx<0 || nx>=n || ny<0 || ny>=m || isVisited[nx][ny] || maps[nx].charAt(ny) == 'X') {
                    continue;
                }
                
                isVisited[nx][ny] = true;
                q.offer(new int[]{nx, ny});
            }
        }
        
        return totalSum;
    }
}