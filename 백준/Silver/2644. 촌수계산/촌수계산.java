import java.io.*;
import java.util.*;

public class Main {

	static int n, m, a, b, result;
	static List<Integer>[] adjList;
	static boolean[] isVisited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// 첫째 줄에는 전체 사람의 수 n이 주어지고,
		n = Integer.parseInt(br.readLine());

		// 둘째 줄에는 촌수를 계산해야 하는 서로 다른 두 사람의 번호가 주어진다.
		st = new StringTokenizer(br.readLine());
		a = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());

		// 셋째 줄에는 부모 자식들 간의 관계의 개수 m이 주어진다.
		m = Integer.parseInt(br.readLine());

		adjList = new List[n + 1];
		for (int i = 1; i <= n; i++)
			adjList[i] = new ArrayList<>();

		// 넷째 줄부터는 부모 자식간의 관계를 나타내는 두 번호 x,y가 각 줄에 나온다.
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			// 양방향 그래프
			adjList[x].add(y);
			adjList[y].add(x);
		}
		///////////////////////////// end of Input

		isVisited = new boolean[n + 1];
		System.out.println(bfs(a));
	}

	static int bfs(int node) {
		Queue<Integer> dq = new ArrayDeque<>();

		isVisited[node] = true;
		dq.offer(node);

		while (!dq.isEmpty()) {
			int size = dq.size();

			for (int i = 0; i < size; i++) {
				int cur = dq.poll();

				if (cur == b)
					return result;

				for (int next : adjList[cur]) {
					if (isVisited[next])
						continue;

					isVisited[next] = true;
					dq.offer(next);
				}
			}

			result++;
		}

		return -1;
	}
}
