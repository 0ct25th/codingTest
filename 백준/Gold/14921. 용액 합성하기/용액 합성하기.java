import java.io.*;
import java.util.*;

public class Main {

	static int N, result;
	static int[] A;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		A = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			A[i] = Integer.parseInt(st.nextToken());

		int start = 0;
		int end = N - 1;
		result = Integer.MAX_VALUE;
		int origin = 0;
		while (start < end) {
			int value = A[start] + A[end];

			if (result > Math.abs(value)) {
				result = Math.abs(value);
				origin = value;
			}

			if (value < 0)
				start++;
			else
				end--;
		}

		System.out.println(origin);
	}
}
