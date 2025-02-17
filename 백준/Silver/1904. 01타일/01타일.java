import java.io.*;

public class Main {
	
	static int N, dp[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		dp = new int[N + 1];
		dp[1] = 1;
		
		if(N > 1)
			dp[2] = 2;
		
		for(int i = 3; i <= N; i++)
			dp[i] = (dp[i - 2] + dp[i - 1]) % 15746;
		
		System.out.println(dp[N]);
	}
}
