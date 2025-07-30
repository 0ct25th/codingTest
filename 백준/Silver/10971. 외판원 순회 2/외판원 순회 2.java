import java.io.*;
import java.util.*;

public class Main {

	static int N, result;
	static int[][] w;
	static boolean[] isVisited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		w = new int[N][N];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++)
				w[r][c] = Integer.parseInt(st.nextToken());
		}

		result = Integer.MAX_VALUE;
		isVisited = new boolean[N];
		isVisited[0] = true;
		tsp(0, 1, 0);

		System.out.println(result);
	}

	static void tsp(int now, int cnt, int cost) {
		if (cnt == N) {
			if (w[now][0] != 0)
				result = Math.min(result, cost + w[now][0]);

			return;
		}

		for (int nxt = 0; nxt < N; nxt++) {
			if (isVisited[nxt] || w[now][nxt] == 0)
				continue;

			isVisited[nxt] = true;
			tsp(nxt, cnt + 1, cost + w[now][nxt]);
			isVisited[nxt] = false;
		}
	}
}
