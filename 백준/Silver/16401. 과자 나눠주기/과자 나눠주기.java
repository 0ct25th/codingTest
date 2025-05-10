import java.io.*;
import java.util.*;

public class Main {

	static int N, M, result;
	static int[] L;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken()); // 조카의 수
		N = Integer.parseInt(st.nextToken()); // 과자의 수

		L = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			L[i] = Integer.parseInt(st.nextToken());

		Arrays.sort(L);
		share();
		System.out.println(result);
	}

	static void share() {
		int start = 1;
		int end = L[N - 1];

		while (start <= end) {
			int mid = (start + end) / 2;

			if (cut(mid) >= M) {
				result = mid;
				start = mid + 1;
			} else
				end = mid - 1;
		}
	}

	static int cut(int x) {
		int cnt = 0;

		for (int i = 0; i < N; i++)
			cnt += (L[i] / x);

		return cnt;
	}
}
