import java.io.*;
import java.util.*;

public class Main {

	static int N, max;
	static int[] A, LIS;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		st = new StringTokenizer(br.readLine());
		A = new int[N];
		for (int i = 0; i < N; i++)
			A[i] = Integer.parseInt(st.nextToken());

		////////////////////// end of Input

		LIS = new int[N];
		dp();

		System.out.println(max);

	}

	static void dp() {
		LIS[0] = 0;

		for (int i = 0; i < N; i++) {
			LIS[i] = 1;
			for (int j = 0; j < i; j++) {
				if (A[j] < A[i] && LIS[i] < LIS[j] + 1)
					LIS[i] = LIS[j] + 1;
			}

			max = Math.max(max, LIS[i]);
		}
	}
}
