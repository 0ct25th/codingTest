import java.io.*;
import java.util.*;

public class Main {

	static final int INF = Integer.MAX_VALUE;

	static int V, E, K;
	static List<Node>[] adjList;
	static int[] minDist;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken()); // 정점의 개수
		E = Integer.parseInt(st.nextToken()); // 간선의 개수

		K = Integer.parseInt(br.readLine()); // 시작 정점의 번호

		adjList = new List[V + 1];
		for (int i = 1; i <= V; i++)
			adjList[i] = new ArrayList<>();

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			adjList[u].add(new Node(v, w));
		}

		dijkstra(K);
		for (int i = 1; i <= V; i++) {
			if (minDist[i] == INF)
				sb.append("INF\n");
			else
				sb.append(minDist[i]).append("\n");
		}

		System.out.println(sb);
	}

	static void dijkstra(int start) {
		Queue<Node> pq = new PriorityQueue<>();
		minDist = new int[V + 1];
		Arrays.fill(minDist, INF);
		boolean[] isVisited = new boolean[V + 1];

		minDist[start] = 0;
		pq.offer(new Node(start, minDist[start]));

		while (!pq.isEmpty()) {
			Node cur = pq.poll();

			if (isVisited[cur.n])
				continue;
			isVisited[cur.n] = true;

			for (Node nxt : adjList[cur.n]) {
				if (minDist[nxt.n] < minDist[cur.n] + nxt.w)
					continue;

				minDist[nxt.n] = minDist[cur.n] + nxt.w;
				pq.offer(new Node(nxt.n, minDist[nxt.n]));
			}
		}
	}

	static class Node implements Comparable<Node> {
		int n; // 번호
		int w; // 가중치

		Node(int n, int w) {
			this.n = n;
			this.w = w;
		}

		@Override
		public int compareTo(Node o) {
			return (this.w - o.w);
		}
	}
}
