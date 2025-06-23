import java.io.*;
import java.util.*;

public class Main {

	static int N, M, result;
	static boolean[] isVisited;
	static List<Integer>[] list;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 정점의 개수
		M = Integer.parseInt(st.nextToken()); // 간선의 개수

		list = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++)
			list[i] = new ArrayList<>();

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			list[a].add(b);
			list[b].add(a);
		}

		isVisited = new boolean[N + 1];
		for (int i = 1; i <= N; i++) {
			if (isVisited[i])
				continue;

			bfs(i);
			result++;
		}

		System.out.println(result);
	}

	static void bfs(int start) {
		Queue<Integer> dq = new ArrayDeque<>();

		isVisited[start] = true;
		dq.offer(start);

		while (!dq.isEmpty()) {
			int cur = dq.poll();

			for (int nxt : list[cur]) {
				if (isVisited[nxt])
					continue;

				isVisited[nxt] = true;
				dq.offer(nxt);
			}
		}
	}
}
