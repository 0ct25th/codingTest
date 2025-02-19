import java.io.*;
import java.util.*;

public class Main {

	static int N, S, result = Integer.MAX_VALUE;
	static int[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// 첫째 줄에 N (10 ≤ N < 100,000)과 S (0 < S ≤ 100,000,000)가 주어진다.
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());

		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			arr[i] = Integer.parseInt(st.nextToken());

		///////////////// end of Input

		int end = 0, sum = 0;
		for (int start = 0; start < N; start++) {
			while (end < N && sum < S)
				sum += arr[end++];

			if (sum >= S)
				result = Math.min(result, end - start);
			sum -= arr[start];
		}

		System.out.println(result == Integer.MAX_VALUE ? 0 : result);
	}
}
