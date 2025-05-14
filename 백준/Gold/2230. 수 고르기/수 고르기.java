import java.io.*;
import java.util.*;

public class Main {

	static int N, M;
	static long A[], result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		A = new long[N];
		for (int i = 0; i < N; i++)
			A[i] = Long.parseLong(br.readLine());

		result = 2_000_000_000;
		Arrays.sort(A);
		twoPointer();
		System.out.println(result);
	}

	static void twoPointer() {
		int left = 0;
		
		for(int right = 0; right < N; right++) {
			while(left <= right && A[right] - A[left] >= M) {
				result = Math.min(result, A[right] - A[left]);
				left++;
			}
		}
	}
}
