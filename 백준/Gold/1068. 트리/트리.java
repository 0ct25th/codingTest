import java.io.*;
import java.util.*;

public class Main {

	static int N, start, remove;
	static List<Integer>[] adjList;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		adjList = new ArrayList[N];
		for (int i = 0; i < N; i++)
			adjList[i] = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int p = Integer.parseInt(st.nextToken());

			if (p == -1)
				start = i;
			else
				adjList[p].add(i);
		}

		remove = Integer.parseInt(br.readLine());
		adjList[remove] = new ArrayList<>();

		int result = bfs(start);
		System.out.println(result);
	}

	static int bfs(int start) {
		Queue<Integer> dq = new ArrayDeque<>();
		boolean[] isVisited = new boolean[N];
		int cnt = 0;

		isVisited[start] = true;
		dq.offer(start);

		while (!dq.isEmpty()) {
			int cur = dq.poll();

			if (cur == remove)
				continue;
			else if (adjList[cur].size() == 0)
				cnt++;
			else if (adjList[cur].size() == 1 && adjList[cur].get(0) == remove)
				cnt++;
			else {
				for (int nxt : adjList[cur]) {
					if (isVisited[nxt] || nxt == remove)
						continue;

					isVisited[nxt] = true;
					dq.offer(nxt);
				}
			}
		}

		return cnt;
	}
}
