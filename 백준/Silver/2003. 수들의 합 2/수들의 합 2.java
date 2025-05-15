import java.io.*;
import java.util.*;

public class Main {

	static int N, M, A[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		A = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			A[i] = Integer.parseInt(st.nextToken());

		System.out.println(twoPointer());
	}

	static int twoPointer() {
		int cnt = 0;

		for (int start = 0; start < N; start++) {
			int end = start;
			int sum = 0;

			while (end < N && sum < M) {
				sum += A[end];
				end++;

				if (sum == M)
					cnt++;
			}
		}
		return cnt;
	}
}
