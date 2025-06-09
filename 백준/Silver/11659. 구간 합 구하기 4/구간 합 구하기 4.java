import java.io.*;
import java.util.*;

public class Main {

	static int N, M;
	static int[] arr, sum;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++)
			arr[i] = Integer.parseInt(st.nextToken());

		sum = new int[N + 1];
		sum[1] = arr[1];
		for (int i = 2; i <= N; i++)
			sum[i] = sum[i - 1] + arr[i];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			
			System.out.println(sum[e] - sum[s - 1]);
		}
	}
}
