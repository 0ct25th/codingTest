import java.io.*;
import java.util.*;

public class Main {

	static int N, M;
	static int[] one;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			N = Integer.parseInt(br.readLine());

			one = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++)
				one[i] = Integer.parseInt(st.nextToken());
			Arrays.sort(one);

			M = Integer.parseInt(br.readLine());

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				if (binarySearch(Integer.parseInt(st.nextToken())))
					sb.append(1).append("\n");

				else
					sb.append(0).append("\n");
			}
		}

		System.out.println(sb);
	}

	static boolean binarySearch(int x) {
		int start = 0;
		int end = N - 1;

		while (start <= end) {
			int mid = (start + end) / 2;

			if (one[mid] < x)
				start = mid + 1;
			else if (one[mid] > x)
				end = mid - 1;
			else
				return true;
		}

		return false;
	}
}
