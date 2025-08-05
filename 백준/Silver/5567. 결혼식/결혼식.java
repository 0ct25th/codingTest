import java.io.*;
import java.util.*;

public class Main {

	static int n, m;
	static List<Integer>[] adjList;
	static boolean[] friends;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());

		adjList = new ArrayList[n + 1];
		for (int i = 1; i <= n; i++)
			adjList[i] = new ArrayList<>();
		friends = new boolean[n + 1];
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			adjList[a].add(b);
			adjList[b].add(a);

			if (a == 1 || b == 1) {
				friends[a] = true;
				friends[b] = true;
			}
		}

		System.out.println(bfs(1));
	}

	static int bfs(int start) {
		Queue<Integer> dq = new ArrayDeque<>();
		boolean[] isVisited = new boolean[n + 1];
		int result = 0;

		isVisited[start] = true;
		dq.offer(start);

		while (!dq.isEmpty()) {
			int cur = dq.poll();

			if (!friends[cur])
				continue;

			for (int nxt : adjList[cur]) {
				if (isVisited[nxt])
					continue;

				result++;
				isVisited[nxt] = true;
				dq.offer(nxt);
			}
		}

		return result;
	}

}
