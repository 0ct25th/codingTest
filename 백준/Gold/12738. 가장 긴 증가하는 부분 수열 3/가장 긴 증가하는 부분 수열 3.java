import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static int[] A;
	static List<Integer> LIS;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		st = new StringTokenizer(br.readLine());
		A = new int[N];
		for (int i = 0; i < N; i++)
			A[i] = Integer.parseInt(st.nextToken());

		LIS = new ArrayList<>();
		LIS.add(A[0]);

		for (int i = 1; i < N; i++) {
			int key = A[i];

			if (LIS.get(LIS.size() - 1) < key)
				LIS.add(key);
			else {
				int start = 0;
				int end = LIS.size() - 1;

				while (start < end) {
					int mid = (start + end) / 2;

					if (LIS.get(mid) < key)
						start = mid + 1;
					else
						end = mid;
				}

				LIS.set(end, key);
			}
		}

		System.out.println(LIS.size());
	}
}
