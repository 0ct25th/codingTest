import java.io.*;
import java.util.*;

public class Main {

	static int n, m, a, b, result;
	static boolean[] isVisited;
	static List<Integer>[] adjList;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		n = Integer.parseInt(br.readLine()); // 사람 수

		st = new StringTokenizer(br.readLine()); // 촌수 계산 번호
		a = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());

		m = Integer.parseInt(br.readLine()); // 관계 수

		adjList = new ArrayList[n + 1];
		for (int i = 1; i <= n; i++)
			adjList[i] = new ArrayList<>();

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			adjList[x].add(y);
			adjList[y].add(x);
		}

		result = -1;
		isVisited = new boolean[n + 1];
		dfs(a, 0);

		System.out.println(result);
	}

	static void dfs(int cur, int cnt) {
		if (cur == b) {
			result = cnt;

			return;
		}

		isVisited[cur] = true;

		for (int nxt : adjList[cur]) {
			if (isVisited[nxt])
				continue;

			dfs(nxt, cnt + 1);
		}
	}
}
