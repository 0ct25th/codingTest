import java.io.*;
import java.util.*;

public class Main {

	static int N, M, K;
	static long[] minDist;
	static List<Node>[] adjList;
	static List<Integer> com;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 도시의 수
		M = Integer.parseInt(st.nextToken()); // 도로의 수
		K = Integer.parseInt(st.nextToken()); // 면접장의 수

		adjList = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++)
			adjList[i] = new ArrayList<>();

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			adjList[v].add(new Node(u, c));
		}

		com = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < K; i++)
			com.add(Integer.parseInt(st.nextToken()));

		dijkstra();
		int result = -1; // 번호
		long maxDist = -1; // 거리
		for (int i = 1; i <= N; i++) {
			if (minDist[i] > maxDist) {
				maxDist = minDist[i];
				result = i;
			} else if (minDist[i] == maxDist)
				result = Math.min(result, i);
		}

		System.out.println(result); // 도시 번호
		System.out.println(maxDist); // 면접장 거리
	}

	static void dijkstra() {
		Queue<Node> pq = new PriorityQueue<>((o1, o2) -> Long.compare(o1.d, o2.d));
		minDist = new long[N + 1];
		Arrays.fill(minDist, Long.MAX_VALUE);

		for (int start : com) {
			minDist[start] = 0;
			pq.offer(new Node(start, minDist[start]));
		}

		while (!pq.isEmpty()) {
			Node cur = pq.poll();

			if (cur.d > minDist[cur.n])
				continue;

			for (Node nxt : adjList[cur.n]) {
				if (minDist[nxt.n] <= minDist[cur.n] + nxt.d)
					continue;

				minDist[nxt.n] = minDist[cur.n] + nxt.d;
				pq.offer(new Node(nxt.n, minDist[nxt.n]));
			}
		}
	}

	static class Node {
		int n; // 번호
		long d; // 거리

		Node(int n, long d) {
			this.n = n;
			this.d = d;
		}
	}
}
