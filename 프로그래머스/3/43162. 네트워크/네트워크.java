import java.util.*;

class Solution {
    static int par[], height[];
    
    public int solution(int n, int[][] computers) {
        par = new int[n];
        height = new int[n];
        
        Arrays.fill(par, -1);
        Arrays.fill(height, 1);
        
        for (int i=0;i<n;i++) for (int j=i+1;j<n;j++) {
            if (computers[i][j]==1) {
                merge(i, j);
            }
        }
        
        int answer = 0;
        for (int p:par) {
            if (p==-1) answer++;
        }

        return answer;
    }
    
    private static int find(int k) {
        if (par[k]==-1) return k;
        return par[k] = find(par[k]);
    }
    
    private static void merge(int a, int b){
        a = find(a);
        b = find(b);
        if (a==b) return;
        
        // 트리의 높이가 낮은 쪽을 높은 쪽 아래에 붙임
        if (height[a]>height[b]) {
            par[b] = a;
        }
        else {
            par[a] = b;
            if (height[a]==height[b]) height[b]++;
        }
    }
}