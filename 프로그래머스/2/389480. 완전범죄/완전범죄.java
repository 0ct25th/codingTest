import java.util.*;

class Solution {
    public int solution(int[][] info, int n, int m) {
        int[][] dp = new int[info.length + 1][m]; // A의 최소 흔적 합 저장
        for(int i = 1; i<= info.length; i++)
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        
        dp[0][0] = 0;
        for(int r = 1; r <= info.length; r++) {
            int a = info[r - 1][0];
            int b = info[r - 1][1];
            
            for(int c = 0; c < m; c++) {
                // A 도둑이 물건을 훔치는 경우
                dp[r][c] = Math.min(dp[r][c], dp[r - 1][c] + a);
                
                // B 도둑이 물건을 훔치는 경우
                if(c + b < m) // B가 경찰에 붙잡히지 않은 경우
                    dp[r][c + b] = Math.min(dp[r][c + b], dp[r - 1][c]);
            }
        }
        
        int answer = n;
        for(int c = 0; c < m; c++)
            answer = Math.min(answer, dp[info.length][c]);
        
        return answer >= n ? -1 : answer;
    }
}