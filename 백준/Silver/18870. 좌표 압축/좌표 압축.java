import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static int[] X, arr;
	static Set<Integer> set;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());

		X = new int[N];
		set = new HashSet<>();
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());
			set.add(num);
			X[i] = num;
		}

		arr = new int[set.size()];
		int idx = 0;
		for (int i : set)
			arr[idx++] = i;
		Arrays.sort(arr);

		for (int i = 0; i < N; i++)
			sb.append(binarySearch(X[i])).append(" ");

		System.out.println(sb);
	}

	static int binarySearch(int x) {
		int start = 0;
		int end = set.size() - 1;

		while (start <= end) {
			int mid = (start + end) / 2;

			if (arr[mid] == x)
				return mid;
			else if (arr[mid] < x)
				start = mid + 1;
			else
				end = mid - 1;
		}

		return -1;
	}
}
