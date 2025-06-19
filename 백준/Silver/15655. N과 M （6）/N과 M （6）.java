import java.io.*;
import java.util.*;

public class Main {

	static int N, M;
	static int[] num, order;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		num = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			num[i] = Integer.parseInt(st.nextToken());

		Arrays.sort(num);
		order = new int[M];
		recursion(0, 0);

		bw.write(sb.toString());
		bw.flush();

		br.close();
		bw.close();
	}

	static void recursion(int depth, int start) {
		if (depth == M) {
			for (int i = 0; i < M; i++)
				sb.append(order[i]).append(" ");

			sb.append("\n");
			return;
		}

		for (int i = start; i < N; i++) {
			order[depth] = num[i];
			recursion(depth + 1, i + 1);
		}
	}
}