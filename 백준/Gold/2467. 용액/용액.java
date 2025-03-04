import java.io.*;
import java.util.*;

public class Main {

	static int N, arr[];
	static long result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());

		st = new StringTokenizer(br.readLine());
		arr = new int[N];
		for (int i = 0; i < N; i++)
			arr[i] = Integer.parseInt(st.nextToken());

		result = Long.MAX_VALUE;
		int start = 0, end = N - 1;
		int minStart = 0, minEnd = 0;
		while (start < end) {
			long sum = arr[start] + arr[end];

			if (result > Math.abs(sum)) {
				result = Math.abs(sum);
				minStart = start;
				minEnd = end;
			}

			if (sum >= 0)
				end--;
			else
				start++;

		}

		sb.append(arr[minStart]).append(" ").append(arr[minEnd]);
		System.out.println(sb);
	}
}
