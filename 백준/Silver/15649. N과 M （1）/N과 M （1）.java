import java.io.*;
import java.util.*;

public class Main {

	static int N, M, num[];
	static boolean[] isVisited;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		isVisited = new boolean[N + 1];
		num = new int[M];
		recursion(0);

		bw.write(sb.toString());
		bw.flush();

		br.close();
		bw.close();
	}

	static void recursion(int depth) {
		if (depth == M) {
			for (int i = 0; i < M; i++)
				sb.append(num[i]).append(" ");
			sb.append("\n");
			return;
		}

		for (int i = 1; i <= N; i++) {
			if (isVisited[i])
				continue;

			isVisited[i] = true;
			num[depth] = i;
			recursion(depth + 1);
			isVisited[i] = false;
		}
	}
}
