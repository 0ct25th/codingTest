import java.io.*;

public class Main {
	
	static int N, dp[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		dp = new int[41][2];
		dp[0][0] = 1;
		dp[0][1] = 0;
		dp[1][0] = 0;
		dp[1][1] = 1;
		
		int T = Integer.parseInt(br.readLine());
		for(int t = 0; t < T; t++) {
			int N = Integer.parseInt(br.readLine());
			
			if(N > 1) {
				for(int i = 2; i <= N; i++) {
					dp[i][0] = dp[i - 1][0] + dp[i - 2][0];
					dp[i][1] = dp[i - 1][1] + dp[i - 2][1];
				}
			}
			
			// 각 테스트 케이스마다 0이 출력되는 횟수와 1이 출력되는 횟수를 공백으로 구분해서 출력한다.
			System.out.println(dp[N][0] + " " + dp[N][1]);
			
		} // end of TestCase
	}
}
