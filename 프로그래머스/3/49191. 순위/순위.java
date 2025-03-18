import java.util.*;

class Solution {
    
    static int dist[][];
    
    public int solution(int n, int[][] results) {
        int answer = 0;
        
        dist = new int[n + 1][n + 1];
        for(int[] r: results) {
            int a = r[0];
            int b = r[1];
            
            dist[a][b] = 1; // a 승리
            dist[b][a] = -1; // b 패배
        }
        
        floydWarshall(n);
        
        for(int r = 1; r <= n; r++) {
            int cnt = 0;
            for(int c = 1; c <= n; c++) {
                if(dist[r][c] != 0)
                    cnt++;
            }
            
            if(cnt == n - 1)
                answer++;
        }
        
        return answer;
    }
    
    static void floydWarshall(int n) {
        for(int k = 1; k <= n; k++) {
            for(int i = 1; i <= n; i++) {
                for(int j = 1; j <= n; j++) {
                    if(dist[i][k] == 1 && dist[k][j] == 1) {
                        dist[i][j] = 1;
                        dist[j][i] = -1;
                    }
                    
                    if(dist[i][k] == -1 && dist[k][j] == -1) {
                        dist[i][j] = -1;
                        dist[j][i] = 1;
                    }
                }
            }
        }
    }
}