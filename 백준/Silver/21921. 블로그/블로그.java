import java.io.*;
import java.util.*;

public class Main {

	static int N, X, result, cnt;
	static int[] v;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());

		v = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			v[i] = Integer.parseInt(st.nextToken());

		slidingWindow();

		if (result > 0) {
			System.out.println(result);
			System.out.println(cnt);
		} else
			System.out.println("SAD");
	}

	static void slidingWindow() {
		for (int i = 0; i < X; i++)
			result += v[i];

		cnt = 1;
		int sum = result;
		for (int i = 1; i <= N - X; i++) {
			sum -= v[i - 1];
			sum += v[i + X - 1];

			if (result == sum)
				cnt++;
			else if (result < sum) {
				result = sum;
				cnt = 1;
			}
		}
	}
}
