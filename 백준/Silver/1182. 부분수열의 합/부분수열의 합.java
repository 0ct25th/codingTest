import java.io.*;
import java.util.*;

public class Main {

	static int N, S, result;
	static int[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());

		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			arr[i] = Integer.parseInt(st.nextToken());

		recursion(0, 0, 0);

		System.out.println(result);
	}

	static void recursion(int depth, int sum, int cnt) {
		if (depth == N) {
			if (sum == S && cnt > 0)
				result++;

			return;
		}

		recursion(depth + 1, sum + arr[depth], cnt + 1);
		recursion(depth + 1, sum, cnt);
	}
}
