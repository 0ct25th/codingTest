import java.io.*;
import java.util.*;

public class Main {

	static int N, K, result;
	static int[] a;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		a = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			a[i] = Integer.parseInt(st.nextToken());

		twoPointer();

		System.out.println(result);
	}

	static void twoPointer() {
		int end = 0;
		int[] isSelected = new int[100_001];

		for (int start = 0; start < N; start++) {
			while (end < N && isSelected[a[end]] < K) {
				isSelected[a[end]]++;
				end++;
			}

			result = Math.max(result, end - start);
			isSelected[a[start]]--;
		}
	}
}
