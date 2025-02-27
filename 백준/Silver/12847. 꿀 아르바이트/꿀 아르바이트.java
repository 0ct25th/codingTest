import java.io.*;
import java.util.*;

public class Main {

	static int n, m, T[];
	static long result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		T = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			T[i] = Integer.parseInt(st.nextToken());
		}

		//////////////// end of Input

		slidingWindow();

		System.out.println(result);
	}

	static void slidingWindow() {
		result = 0;

		// 첫번째 윈도우
		long window = 0;
		for (int i = 0; i < m; i++)
			window += T[i];
		result = Long.max(result, window);

		for (int i = 1; i < n - m + 1; i++) {
			window -= T[i - 1];
			window += T[i + m - 1];
			result = Long.max(result, window);
		}
	}
}
