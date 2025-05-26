import java.io.*;
import java.util.*;

public class Main {

	static final int INF = 200_000_000;

	static int N, E, stopover1, stopover2;
	static List<Node>[] adjList;
	static int[] minDist;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 정점의 개수
		E = Integer.parseInt(st.nextToken()); // 간선의 개수

		adjList = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++)
			adjList[i] = new ArrayList<>();

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			adjList[a].add(new Node(b, c));
			adjList[b].add(new Node(a, c));
		}

		st = new StringTokenizer(br.readLine());
		stopover1 = Integer.parseInt(st.nextToken());
		stopover2 = Integer.parseInt(st.nextToken());

		int route1 = dijkstra(1, stopover1) + dijkstra(stopover1, stopover2) + dijkstra(stopover2, N);
		int route2 = dijkstra(1, stopover2) + dijkstra(stopover2, stopover1) + dijkstra(stopover1, N);

		if (route1 >= INF && route2 >= INF)
			System.out.println(-1);
		else
			System.out.println(Math.min(route1, route2));
	}

	static int dijkstra(int start, int end) {
		Queue<Node> pq = new PriorityQueue<>((o1, o2) -> (o1.d - o2.d));
		minDist = new int[N + 1];
		Arrays.fill(minDist, INF);

		minDist[start] = 0;
		pq.offer(new Node(start, minDist[start]));

		while (!pq.isEmpty()) {
			Node cur = pq.poll();

			if (cur.d > minDist[cur.n])
				continue;

			for (Node nxt : adjList[cur.n]) {
				if (minDist[nxt.n] <= cur.d + nxt.d)
					continue;

				minDist[nxt.n] = cur.d + nxt.d;
				pq.offer(new Node(nxt.n, minDist[nxt.n]));
			}
		}

		return minDist[end];
	}

	static class Node {
		int n; // 번호
		int d; // 거리

		Node(int n, int d) {
			this.n = n;
			this.d = d;
		}
	}
}
