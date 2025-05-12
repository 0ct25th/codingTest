import java.io.*;
import java.util.*;

public class Main {

	static int N, K;
	static int[] t;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 전체 날짜의 수
		K = Integer.parseInt(st.nextToken()); // 연속적인 날짜의 수

		t = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			t[i] = Integer.parseInt(st.nextToken());

		System.out.println(slidingWindow());
	}

	static int slidingWindow() {
		int result = 0;
		for (int i = 0; i < K; i++)
			result += t[i];

		int sum = result;
		for (int i = 1; i <= N - K; i++) {
			sum -= t[i - 1];
			sum += t[i + K - 1];

			result = Math.max(result, sum);
		}

		return result;
	}
}
