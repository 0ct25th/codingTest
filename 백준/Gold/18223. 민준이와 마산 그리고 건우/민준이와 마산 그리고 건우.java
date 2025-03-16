import java.io.*;
import java.util.*;

public class Main {

	static int V, E, P;
	static long[] minDist;
	static List<Node>[] nodeList;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken()); // 정점 개수
		E = Integer.parseInt(st.nextToken()); // 간선 개수
		P = Integer.parseInt(st.nextToken()); // 건우가 존재하는 정점

		nodeList = new ArrayList[V + 1];
		for (int i = 1; i <= V; i++)
			nodeList[i] = new ArrayList<>();

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()); // 정점 번호
			int b = Integer.parseInt(st.nextToken()); // 정점 번호
			int c = Integer.parseInt(st.nextToken()); // 거리

			// 양방향 그래프
			nodeList[a].add(new Node(b, c));
			nodeList[b].add(new Node(a, c));
		}

		long save = dijkstra(1, P) + dijkstra(P, V);
		long unSave = dijkstra(1, V);

		System.out.println(save <= unSave ? "SAVE HIM" : "GOOD BYE");
	}

	static long dijkstra(int start, int end) {
		Queue<Node> pq = new PriorityQueue<>();
		minDist = new long[V + 1];
		Arrays.fill(minDist, Long.MAX_VALUE);

		minDist[start] = 0;
		pq.offer(new Node(start, minDist[start]));

		while (!pq.isEmpty()) {
			Node cur = pq.poll();

			if (cur.num == end)
				return cur.dist;

			if (cur.dist > minDist[cur.num])
				continue;

			for (Node nxt : nodeList[cur.num]) {
				if (minDist[nxt.num] <= minDist[cur.num] + nxt.dist)
					continue;

				minDist[nxt.num] = minDist[cur.num] + nxt.dist;
				pq.offer(new Node(nxt.num, minDist[nxt.num]));
			}
		}
		
		return 0;
	}

	static class Node implements Comparable<Node> {
		int num;
		long dist;

		Node(int num, long dist) {
			this.num = num;
			this.dist = dist;
		}

		@Override
		public int compareTo(Node o) {
			return Long.compare(this.dist, o.dist);
		}
	}
}
