import java.io.*;
import java.util.*;

public class Main {

	static int N, K, result;
	static int[] A;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		A = new int[N];
		for (int i = 0; i < N; i++)
			A[i] = Integer.parseInt(st.nextToken());

		slidingWindow();

		System.out.print(result);
	}

	static void slidingWindow() {
		for (int i = 0; i < K; i++)
			result += A[i];

		int sum = result;
		for (int i = 1; i < N; i++) {
			// 왼쪽 빼기
			sum -= A[i - 1];
			// 오른쪽 더하기
			sum += A[(i + K - 1) % N];

			result = Math.max(result, sum);
		}
	}
}
