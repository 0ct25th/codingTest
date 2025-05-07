import java.io.*;
import java.util.*;

public class Main {

	static int N, M;
	static int[] A;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());

		A = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			A[i] = Integer.parseInt(st.nextToken());

		Arrays.sort(A);

		M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			int num = Integer.parseInt(st.nextToken());

			sb.append(binarySearch(num) ? "1\n" : "0\n");
		}

		System.out.println(sb);
	}

	static boolean binarySearch(int x) {
		int start = 0;
		int end = N - 1;

		while (start <= end) {
			int mid = (start + end) / 2;

			if (A[mid] == x)
				return true;
			else if (A[mid] < x)
				start = mid + 1;
			else
				end = mid - 1;
		}

		return false;
	}
}
