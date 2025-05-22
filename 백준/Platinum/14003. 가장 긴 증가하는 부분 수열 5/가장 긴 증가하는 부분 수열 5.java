import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static long A[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		A = new long[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			A[i] = Long.parseLong(st.nextToken());

		LIS();
	}

	static void LIS() {
		StringBuilder sb = new StringBuilder();
		List<Long> lis = new ArrayList<>();
		int[] order = new int[N];
		Stack<Long> stk = new Stack<>();

		lis.add(A[0]);
		for (int i = 1; i < N; i++) {
			long key = A[i];

			if (lis.get(lis.size() - 1) < key) {
				lis.add(key);
				order[i] = lis.size() - 1;
			} else {
				int start = 0;
				int end = lis.size() - 1;

				while (start < end) {
					int mid = (start + end) / 2;

					if (lis.get(mid) < key)
						start = mid + 1;
					else
						end = mid;
				}

				lis.set(end, key);
				order[i] = end;
			}
		}

		int idx = lis.size() - 1;
		for (int i = N - 1; i > -1; i--) {
			if (idx != order[i])
				continue;

			stk.push(A[i]);
			idx--;
		}

		sb.append(lis.size()).append("\n");
		while (!stk.isEmpty())
			sb.append(stk.pop()).append(" ");

		System.out.println(sb);
	}
}
