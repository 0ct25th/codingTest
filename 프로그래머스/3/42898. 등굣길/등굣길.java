import java.util.*;

class Solution {
    public int solution(int m, int n, int[][] puddles) {
        int[][] map = new int[n + 1][m + 1]; // 지역 상태 저장
        int[][] dp = new int[n + 1][m + 1]; // (1, 1)에서 (r, c)에 도달하는 최단 경로 개수 저장
        
        // 물에 잠긴 지역 표시
        for(int[] puddle: puddles)
            map[puddle[1]][puddle[0]] = 1;
        
        dp[1][1] = 1;
        for(int r = 2; r <= n; r++) {
            if(map[r][1] == 1)
                continue;
            
            dp[r][1] = dp[r - 1][1];
        }
        
        for(int c = 2; c <= m; c++) {
            if(map[1][c] == 1)
                continue;
            
            dp[1][c] = dp[1][c - 1];
        }
        
        for(int r = 2; r <= n; r++) {
            for(int c = 2; c <= m; c++) {
                if(map[r][c] == 1)
                    continue;
                
                dp[r][c] = (dp[r - 1][c] + dp[r][c - 1]) % 1_000_000_007;
            }
        }
        
        
        return dp[n][m];
    }
}