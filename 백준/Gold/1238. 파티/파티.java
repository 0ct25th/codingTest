import java.io.*;
import java.util.*;

public class Main {

	static final int INF = Integer.MAX_VALUE;

	static int N, M, X, result;
	static List<Node>[] adjList;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 학생 수
		M = Integer.parseInt(st.nextToken()); // 도로 수
		X = Integer.parseInt(st.nextToken()); // 파티 마을

		adjList = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++)
			adjList[i] = new ArrayList<>();

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());

			adjList[a].add(new Node(b, t));
		}

		for (int i = 1; i <= N; i++) {
			if (i == X)
				continue;

			result = Math.max(result, dijkstra(i, X) + dijkstra(X, i));
		}

		System.out.println(result);
	}

	static int dijkstra(int start, int end) {
		Queue<Node> pq = new PriorityQueue<>((o1, o2) -> (o1.t - o2.t));
		int[] minDist = new int[N + 1];
		Arrays.fill(minDist, INF);
		boolean[] isVisited = new boolean[N + 1];

		minDist[start] = 0;
		pq.offer(new Node(start, minDist[start]));

		while (!pq.isEmpty()) {
			Node cur = pq.poll();

			if (isVisited[cur.n])
				continue;
			isVisited[cur.n] = true;

			for (Node nxt : adjList[cur.n]) {
				if (minDist[nxt.n] <= cur.t + nxt.t)
					continue;

				minDist[nxt.n] = cur.t + nxt.t;
				pq.offer(new Node(nxt.n, minDist[nxt.n]));
			}
		}

		return minDist[end];
	}

	static class Node {
		int n; // 도시 번호
		int t; // 소요 시간

		Node(int n, int t) {
			this.n = n;
			this.t = t;
		}
	}
}
