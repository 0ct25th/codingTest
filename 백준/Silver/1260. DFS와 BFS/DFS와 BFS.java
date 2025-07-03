import java.io.*;
import java.util.*;

public class Main {

	static int N, M, V;
	static boolean[] isVisited;
	static List<Integer>[] adjList;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 정점 개수
		M = Integer.parseInt(st.nextToken()); // 간선 개수
		V = Integer.parseInt(st.nextToken()); // 정점 번호

		adjList = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++)
			adjList[i] = new ArrayList<>();

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			adjList[a].add(b);
			adjList[b].add(a);
		}
		
		for(int i = 1; i <= N; i++)
			Collections.sort(adjList[i]);

		isVisited = new boolean[N + 1];
		dfs(V);
		sb.append("\n");

		isVisited = new boolean[N + 1];
		bfs(V);

		bw.write(sb.toString());
		bw.flush();

		br.close();
		bw.close();
	}

	static void dfs(int cur) {
		isVisited[cur] = true;
		sb.append(cur).append(" ");

		for (int nxt : adjList[cur]) {
			if (isVisited[nxt])
				continue;

			dfs(nxt);
		}
	}

	static void bfs(int start) {
		Queue<Integer> dq = new ArrayDeque<>();

		sb.append(start).append(" ");
		isVisited[start] = true;
		dq.offer(start);

		while (!dq.isEmpty()) {
			int cur = dq.poll();

			for (int nxt : adjList[cur]) {
				if (isVisited[nxt])
					continue;

				sb.append(nxt).append(" ");
				isVisited[nxt] = true;
				dq.offer(nxt);
			}
		}
	}
}
