import java.io.*;
import java.util.*;

public class Main {

	static int N, M, result;
	static List<Integer>[] adjList;
	static boolean[] isVisited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		adjList = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++)
			adjList[i] = new ArrayList<>();

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());

			// 양방향 그래프
			adjList[u].add(v);
			adjList[v].add(u);
		}

		isVisited = new boolean[N + 1];
		for (int i = 1; i <= N; i++) {
			// 방문 안한 경우 그래프 탐색
			if (isVisited[i])
				continue;

			result++;
			search(i);
		}

		System.out.println(result);
	}

	static void search(int node) {
		Queue<Integer> dq = new ArrayDeque<>();

		isVisited[node] = true;
		dq.offer(node);

		while (!dq.isEmpty()) {
			int cur = dq.poll();

			for (Integer next : adjList[cur]) {
				if (isVisited[next])
					continue;

				isVisited[next] = true;
				dq.offer(next);
			}
		}
	}
}
