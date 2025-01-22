import java.util.*;

class Solution {
    
    static int N, M;
    static boolean[][] isVisited;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    
    public int solution(int[][] maps) {
        N = maps.length;
        M = maps[0].length;
        
        isVisited = new boolean[N][M];
        int answer = bfs(maps, 0, 0);
        
        return answer;
    }
    
    static int bfs(int[][] maps, int sr, int sc) {
        Queue<Coord> dq = new ArrayDeque<>();
        isVisited[sr][sc] = true;
        dq.offer(new Coord(sr, sc, 1));
        
        while(!dq.isEmpty()) {
            Coord cur = dq.poll();
            int r = cur.r;
            int c = cur.c;
            int cnt = cur.cnt;
            
            // 도착한 경우
            if(r == N - 1 && c == M - 1) 
                return cnt;
            
            // 4방향 탐색
            for(int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];
                
                // 유효범위 외 || 이미 방문 || 못가는 곳
                if(!isValidCoord(nr, nc) || isVisited[nr][nc] || maps[nr][nc] == 0)
                    continue;   // 넘김
                
                isVisited[nr][nc] = true;   // 방문 체크
                dq.offer(new Coord(nr, nc, cnt + 1));
            }
        }
        
        return -1;
    }
    
    static boolean isValidCoord(int r, int c) {
        return -1 < r && r < N && -1 < c && c < M;
    }
    
    static class Coord {
        int r, c, cnt;
        
        Coord(int r, int c, int cnt) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
        }
    }
}