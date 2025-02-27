import java.io.*;
import java.util.*;

public class Main {

	static int N, K, arr[], result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		arr = new int[N + 1];
		for (int i = 0; i < N; i++)
			arr[i] = Integer.parseInt(st.nextToken());

		twoPointer();

		System.out.println(result);
	}

	static void twoPointer() {
		int end = 0;
		int[] isSelected = new int[100001];

		for (int start = 0; start < N; start++) {
			while (end < N && isSelected[arr[end]] < K) {
				isSelected[arr[end]]++;
				end++;
			}

			// 최대값 갱신
			result = Math.max(result, end - start);

			// start 제거
			isSelected[arr[start]]--;
		}
	}
}
