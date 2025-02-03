import java.io.*;
import java.util.*;

public class Main {

	static int N, M, sum;
	static int[] money;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine()); // 지방의 수

		money = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			money[i] = Integer.parseInt(st.nextToken());
			sum += money[i];
		}

		M = Integer.parseInt(br.readLine()); // 총 예산

		Arrays.sort(money);

		if (sum <= M)
			System.out.println(money[N - 1]);
		else
			System.out.println(binarySearch());
	}

	static int binarySearch() {
		int start = 0;
		int end = money[N - 1];

		while (start < end) {
			int mid = (start + end) / 2;
			int sum = 0;

			// 나눈 금액 더하기
			for (int i = 0; i < N; i++) {
				if (money[i] >= mid)
					sum += mid;
				else
					sum += money[i];
			}

			if (sum > M)
				end = mid;
			else
				start = mid + 1;

		}

		return start - 1;
	}
}
