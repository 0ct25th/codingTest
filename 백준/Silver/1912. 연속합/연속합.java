import java.io.*;
import java.util.*;

public class Main {

	static int n;
	static int[] arr, dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());

		arr = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) 
			arr[i] = Integer.parseInt(st.nextToken());
		

		dp = new int[n];
		dp[0] = arr[0];
		int result = dp[0];
		
		for (int i = 1; i < n; i++) {
			dp[i] = Math.max(arr[i], dp[i - 1] + arr[i]);
			result = Math.max(result, dp[i]);
		}

		System.out.println(result);
	}
}
