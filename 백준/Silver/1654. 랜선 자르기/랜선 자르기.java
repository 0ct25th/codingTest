import java.io.*;
import java.util.*;

public class Main {

	static int K, N;
	static long result;
	static long[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		K = Integer.parseInt(st.nextToken()); // 랜선의 개수
		N = Integer.parseInt(st.nextToken()); // 필요한 랜선의 개수

		arr = new long[K];
		for (int i = 0; i < K; i++)
			arr[i] = Long.parseLong(br.readLine());

		Arrays.sort(arr);
		binarySearch();

		System.out.println(result);
	}

	static void binarySearch() {
		long start = 1;
		long end = arr[K - 1]; // 가장 긴 랜선의 길이

		while (start <= end) {
			long mid = (start + end) / 2;

			if (cut(mid) >= N) {
				result = Math.max(result, mid);
				start = mid + 1;
			} else
				end = mid - 1;
		}
	}

	static int cut(long x) {
		int cnt = 0;

		for (int i = 0; i < K; i++)
			cnt += arr[i] / x;

		return cnt;
	}
}
