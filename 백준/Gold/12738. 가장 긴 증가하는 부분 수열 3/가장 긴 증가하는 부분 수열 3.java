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

		System.out.println(LIS());
	}

	static int LIS() {
		List<Long> lis = new ArrayList<>();
		lis.add(A[0]);

		for (int i = 1; i < N; i++) {
			long key = A[i];

			if (lis.get(lis.size() - 1) < key)
				lis.add(key);
			else {
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
			}
		}

		return lis.size();
	}

}
