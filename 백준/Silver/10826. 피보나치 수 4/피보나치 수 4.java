import java.io.*;
import java.math.*;

public class Main {

	static int n;
	static BigInteger[] dp = new BigInteger[10001];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());

		dp[0] = BigInteger.ZERO;
		dp[1] = BigInteger.ONE;

		for (int i = 2; i <= n; i++)
			dp[i] = dp[i - 1].add(dp[i - 2]);

		System.out.println(dp[n]);
	}
}
