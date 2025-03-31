import java.io.*;
import java.util.*;

public class Main {

	static int N, M, K;
	static int[][] arr, dp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N + 1][M + 1];
		dp = new int[N + 1][M + 1];
		for(int r = 1; r <= N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 1; c <= M; c++) 
				arr[r][c] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 1; i <= N; i++) 
		    for(int j = 1; j <= M; j++) 
		        dp[i][j] = arr[i][j] + dp[i-1][j] + dp[i][j-1] - dp[i-1][j-1];
		
		K = Integer.parseInt(br.readLine());
		for(int k = 0; k < K; k++) {
			st = new StringTokenizer(br.readLine());
			int i = Integer.parseInt(st.nextToken());
			int j = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			int result = dp[x][y] - dp[i-1][y] - dp[x][j-1] + dp[i-1][j-1];
			System.out.println(result);
		}
	}
}

