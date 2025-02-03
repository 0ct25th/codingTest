import java.io.*;
import java.util.*;

public class Main {

	static int N, M;
	static int[] trees;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 나무의 수
		M = Integer.parseInt(st.nextToken()); // 가져갈 나무의 길이

		trees = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			trees[i] = Integer.parseInt(st.nextToken());

		Arrays.sort(trees);

		System.out.println(binarySearch());
	}

	static long binarySearch() {
		long start = 0;
		long end = trees[N - 1];

		while (start < end) {
			long mid = (start + end) / 2;
			long sum = 0;

			for (int i = 0; i < N; i++)
				if (trees[i] - mid > 0)
					sum += trees[i] - mid;

			if (sum < M)
				end = mid;
			else
				start = mid + 1;
		}

		return start - 1;
	}
}
