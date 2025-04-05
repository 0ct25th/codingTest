import java.util.*;

class Solution {
    
    static int n, m, cnt;
    static int[][] map;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static boolean[][] isVisited;
    static Map<Integer, Integer> hash;
    
    public int solution(int[][] land) {
        int answer = Integer.MIN_VALUE;
        n = land.length;
        m = land[0].length;
        
        // 석유 덩어리 체크
        cnt = 1; // 석유 덩어리 번호
        map = new int[n][m]; // 석유 번호 저장할 맵
        isVisited = new boolean[n][m];
        hash = new HashMap<>();
        for(int r = 0; r < n; r++) {
            for(int c = 0; c < m; c++) {
                if(land[r][c] == 0 || isVisited[r][c])
                    continue;
                
                hash.put(cnt, bfs(r, c, cnt, land));
                cnt++;
            }
        }      
        
        // c열에 시추관 설치
        for(int c = 0; c < m; c++) {
            answer = Math.max(answer, setting(c));
        }
        
        return answer;
    }
    
    static int setting(int sc) {
        int sum = 0; // 총 석유 량
        boolean[] isChk = new boolean[cnt]; // 석유 덩어리 번호 체크
        
        for(int r = 0; r < n; r++) {
            // 시추관 체크
            if(map[r][sc] > 0 && !isChk[map[r][sc]]) {
                sum += hash.get(map[r][sc]); // 석유량 증가
                isChk[map[r][sc]] = true;
            }
        }
        
        return sum;
    }
    
    static int bfs(int sr, int sc, int cnt, int[][] land) {
        Queue<Coord> dq = new ArrayDeque<>();
        int v = 1; // 시작점도 석유
        
        isVisited[sr][sc] = true; // 시작점 방문 처리
        map[sr][sc] = cnt;
        dq.offer(new Coord(sr, sc));
        
        while(!dq.isEmpty()) {
            Coord cur = dq.poll();
            int r = cur.r;
            int c = cur.c;
            
            for(int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];
                
                // 유효범위 밖 || 이미 방문 || 석유가 아닌 경우
                if(!isValidCoord(nr, nc) || isVisited[nr][nc] || land[nr][nc] == 0)
                    continue;
                
                isVisited[nr][nc] = true;
                map[nr][nc] = cnt;
                v++;
                dq.offer(new Coord(nr, nc));
            }
        }
        
        return v;
    }
    
    static boolean isValidCoord(int r, int c) {
        return -1 < r && r < n && -1 < c && c < m;
    }
    
    static class Coord {
        int r, c;
        
        Coord(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}