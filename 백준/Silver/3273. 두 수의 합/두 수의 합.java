import java.io.*;
import java.util.*;

public class Main {

	static int n, x, result;
	static int[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		n = Integer.parseInt(br.readLine()); // 수열의 크기

		arr = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++)
			arr[i] = Integer.parseInt(st.nextToken());

		x = Integer.parseInt(br.readLine());

		Arrays.sort(arr);

		binarySearch();

		System.out.println(result);
	}

	static void binarySearch() {
		int start = 0;
		int end = n - 1;

		while (start < end) {
			int num = arr[start] + arr[end];

			if (num == x)
				result++;

			if (num <= x)
				start++;
			else
				end--;
		}
	}
}
