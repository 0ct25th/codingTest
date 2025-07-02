import java.io.*;
import java.util.*;

public class Main {

	static int N, p[];
	static List<Integer>[] adjList;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		adjList = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++)
			adjList[i] = new ArrayList<>();
		for (int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			adjList[a].add(b);
			adjList[b].add(a);
		}

		p = new int[N + 1];
		bfs(1);

		for (int i = 2; i <= N; i++)
			System.out.println(p[i]);
	}

	static void bfs(int start) {
		Queue<Integer> dq = new ArrayDeque<>();
		boolean[] isVisited = new boolean[N + 1];

		isVisited[start] = true;
		dq.offer(start);

		while (!dq.isEmpty()) {
			int cur = dq.poll();

			for (int nxt : adjList[cur]) {
				if (isVisited[nxt])
					continue;

				p[nxt] = cur;
				isVisited[nxt] = true;
				dq.offer(nxt);
			}
		}
	}
}
