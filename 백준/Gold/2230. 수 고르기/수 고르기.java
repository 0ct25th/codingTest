import java.io.*;
import java.util.*;

public class Main {

	static int N, M, result;
	static int[] A;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 수열 A의 길이
		M = Integer.parseInt(st.nextToken()); // M 이상 차이

		A = new int[N];
		for (int i = 0; i < N; i++)
			A[i] = Integer.parseInt(br.readLine());

		///////////////////////////////////////////// end of Input

		Arrays.sort(A);

		result = Integer.MAX_VALUE;
		twoPointer();

		System.out.println(result);
	}

	static void twoPointer() {
		int end = 0;

		for (int start = 0; start < N; start++) {
			while (end < N && A[end] - A[start] < M)
				end++;

			if (end == N)
				break;

			result = Math.min(result, A[end] - A[start]);
		}

	}

}
