class Solution {
    public int solution(int[][] t) {
        int N = t.length;
        int[][] dp = new int[N + 1][N + 1];
        int result = 0;
        
        dp[1][1] = t[0][0];
        
        for(int r = 2; r <= N; r++) {
            for(int c = 1; c <= r; c++) {
                dp[r][c] = Math.max(dp[r - 1][c - 1], dp[r - 1][c]) + t[r - 1][c - 1];
                result = Math.max(result, dp[r][c]);
             }
        }
        
        return result;
    }
}