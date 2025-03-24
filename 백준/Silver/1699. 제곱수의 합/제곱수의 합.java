import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static int[] dp = new int[1000001];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		dp[1] = 1;
		for(int i = 2; i <= N; i++) {
			dp[i] = i;
			
			for(int j = 1; j * j <= i; j++)
				dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
		}
		System.out.println(dp[N]);
	}
}
