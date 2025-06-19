import java.io.*;
import java.util.*;

public class Main {

	static int N, M;
	static int[] num;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		num = new int[M];
		recursion(0, 1);

		bw.write(sb.toString());
		bw.flush();

		br.close();
		bw.close();
	}

	static void recursion(int depth, int start) {
		if (depth == M) {
			for (int i = 0; i < M; i++)
				sb.append(num[i]).append(" ");

			sb.append("\n");
			return;
		}

		for (int i = start; i <= N; i++) {
			num[depth] = i;
			recursion(depth + 1, i);
		}
	}
}
