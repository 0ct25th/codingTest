import java.io.*;
import java.util.*;

public class Main {

	static int M, N;
	static long start, mid, end, result;
	static int[] snacks;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken()); // 조카의 수
		N = Integer.parseInt(st.nextToken()); // 과자의 수

		st = new StringTokenizer(br.readLine());
		snacks = new int[N];
		for (int i = 0; i < N; i++)
			snacks[i] = Integer.parseInt(st.nextToken()); // 과자 길이

		Arrays.sort(snacks);

		System.out.println(binarySearch());
	}

	static long binarySearch() {
		start = 1;
		end = snacks[N - 1];

		while (start <= end) {
			int count = 0; // 나눠줄 수 있는 과자 수
			mid = (start + end) / 2;

			// 줄 수 있는 조카 수 계산
			for (int i = 0; i < N; i++)
				count += snacks[i] / mid;

			// 과자 수가 조카 수보다 많거나 같은 경우
			if (count >= M) {
				start = mid + 1;

				if (result < mid)
					result = mid; // 막대 과자의 최대 길이 조정
			} else
				end = mid - 1;
		}

		return result;
	}

}
