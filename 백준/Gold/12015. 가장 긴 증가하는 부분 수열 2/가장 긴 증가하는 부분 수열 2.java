import java.io.*;
import java.util.*;

public class Main {

	static int N, A[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		A = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			A[i] = Integer.parseInt(st.nextToken());

		System.out.println(LIS());
	}

	static int LIS() {
		List<Integer> lis = new ArrayList<>();
		lis.add(A[0]);

		for (int i = 1; i < N; i++) {
			if (A[i] > lis.get(lis.size() - 1))
				lis.add(A[i]);
			else {
				int start = 0;
				int end = lis.size() - 1;

				while (start < end) {
					int mid = (start + end) / 2;

					if (lis.get(mid) < A[i])
						start = mid + 1;
					else
						end = mid;
				}

				lis.set(end, A[i]);
			}
		}

		return lis.size();
	}
}
