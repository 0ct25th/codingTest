import java.io.*;
import java.util.*;

public class Main {

	static int N, X, arr[], max, cnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());

		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		slidingWindow();

		if (max == 0)
			System.out.println("SAD");
		else 
			System.out.println(max + "\n" + cnt);
	}

	static void slidingWindow() {
		int sum = 0;

		for (int i = 0; i < X; i++)
			sum += arr[i];

		max = sum;
		cnt = 1;

		for (int i = 1; i <= N - X; i++) {
			// 왼쪽 빼기
			sum -= arr[i - 1];
			// 오른쪽 더하기
			sum += arr[i + X - 1];

			if (sum > max) {
				max = sum;
				cnt = 1;
			} else if (sum == max)
				cnt++;
		}
	}
}
