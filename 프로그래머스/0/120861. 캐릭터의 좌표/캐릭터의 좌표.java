import java.util.*;

class Solution {
    final int[] dy = new int[]{0, 0, -1, 1}; // 상, 하, 좌, 우
    final int[] dx = new int[]{1, -1, 0, 0};
    
    public int[] solution(String[] keyinput, int[] board) {
        int[] pos = new int[]{0, 0};
        int r = board[0] / 2;
        int c = board[1] / 2;
        
        for (String ki : keyinput) {
            int ny = pos[0];
            int nx = pos[1];
            switch (ki) {
                case "up":
                    ny += dy[0];
                    nx += dx[0];
                    break;
                case "down":
                    ny += dy[1];
                    nx += dx[1];
                    break;
                case "left":
                    ny += dy[2];
                    nx += dx[2];
                    break;
                case "right":
                    ny += dy[3];
                    nx += dx[3];
                    break;
            }
            
            if (ny < -r || ny > r || nx < -c || nx > c) continue;
            pos[0] = ny;
            pos[1] = nx;
        }
        return pos;
    }
}