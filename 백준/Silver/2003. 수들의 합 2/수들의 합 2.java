import java.io.*;
import java.util.*;

public class Main {

	static int N, M, result;
	static int[] A;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 수열 개수
		M = Integer.parseInt(st.nextToken()); // 합계

		st = new StringTokenizer(br.readLine());
		A = new int[N];
		for (int i = 0; i < N; i++)
			A[i] = Integer.parseInt(st.nextToken());

		result = 0;
		twoPointer();

		System.out.println(result);
	}

	static void twoPointer() {

		for (int start = 0; start < N; start++) {
			int end = start;
			int sum = 0;

			while (end < N) {
				sum += A[end];

				if (sum == M) {
					result++;
					break;
				} else if (sum > M)
					break;

				end++;
			}
		}
	}

}
