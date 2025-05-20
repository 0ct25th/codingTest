import java.io.*;
import java.util.*;

public class Main {

	static int N, A[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		A = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			A[i] = Integer.parseInt(st.nextToken());

		System.out.println(LIS());
	}

	static int LIS() {
		int result = 0;
		int[] sum = new int[N]; // 최고 합 저장

		for (int i = 0; i < N; i++) {
			sum[i] = A[i];

			for (int j = 0; j < i; j++)
				if (A[j] < A[i])
					sum[i] = Math.max(sum[i], sum[j] + A[i]);

			result = Math.max(result, sum[i]);
		}

		return result;
	}
}
