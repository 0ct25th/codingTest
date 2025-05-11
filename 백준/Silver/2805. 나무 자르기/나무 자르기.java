import java.io.*;
import java.util.*;

public class Main {

	static int N, M;
	static long h[], result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 나무의 수
		M = Integer.parseInt(st.nextToken()); // 가져가려는 나무의 길이

		h = new long[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			h[i] = Long.parseLong(st.nextToken());

		Arrays.sort(h);
		binarySearch();
		System.out.println(result);
	}

	static void binarySearch() {
		long start = 0;
		long end = h[N - 1];

		while (start <= end) {
			long mid = (start + end) / 2;

			if (cut(mid) >= M) {
				result = mid;
				start = mid + 1;
			} else
				end = mid - 1;
		}
	}

	static long cut(long x) {
		long mod = 0;

		for (int i = 0; i < N; i++) {
			if (h[i] - x > 0)
				mod += h[i] - x;
		}

		return mod;
	}
}
