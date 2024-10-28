import java.util.*;

class Solution {
    int n = 0, m = 0;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static boolean[][] isVisited;
    
    public int solution(int[][] maps) {
        n = maps.length;
        m = maps[0].length;
        isVisited = new boolean[n][m];
        
        return bfs(0, 0, maps);
    }
    
    public int bfs(int sr, int sc, int[][] maps) {
        Queue<Coord> dq = new ArrayDeque<>();
        isVisited[sr][sc] = true;
        dq.offer(new Coord(sr, sc, 1));
        
        while(!dq.isEmpty()) {
            Coord cur = dq.poll();
            int r = cur.r;
            int c = cur.c;
            int v = cur.v;
            
            // 도착한 경우
            if(r == n - 1 && c == m - 1)
                return v;
            
            // 4방향 탐색
            for(int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];
                
                if(!isValidCoord(nr, nc) || isVisited[nr][nc] || maps[nr][nc] == 0)
                    continue;
                
                isVisited[nr][nc] = true;
                dq.offer(new Coord(nr, nc, v + 1));
            }
        }
        
        // 도착하지 못한 경우
        return -1;
    }
    
    public boolean isValidCoord(int r, int c) {
        return -1 < r && r < n && -1 < c && c < m;
    }
    
    public static class Coord {
        int r;
        int c;
        int v;
        
        Coord(int r, int c, int v) {
            this.r = r;
            this.c = c;
            this.v = v;
        }
    }
}