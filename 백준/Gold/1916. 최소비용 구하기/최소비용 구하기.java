import java.io.*;
import java.util.*;

public class Main {

	static int N, M, start, end;
	static List<Node>[] adjList;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine()); // 도시의 개수
		M = Integer.parseInt(br.readLine()); // 버스의 개수

		adjList = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++)
			adjList[i] = new ArrayList<>();

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			adjList[a].add(new Node(b, w));
		}

		st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());

		System.out.println(dijkstra(start, end));
	}

	static int dijkstra(int start, int end) {
		Queue<Node> pq = new PriorityQueue<>((o1, o2) -> (o1.w - o2.w));
		int[] minDist = new int[N + 1];
		Arrays.fill(minDist, Integer.MAX_VALUE);

		minDist[start] = 0;
		pq.offer(new Node(start, minDist[start]));

		while (!pq.isEmpty()) {
			Node cur = pq.poll();

			if (cur.w > minDist[cur.n])
				continue;

			for (Node nxt : adjList[cur.n]) {
				if (minDist[nxt.n] <= minDist[cur.n] + nxt.w)
					continue;

				minDist[nxt.n] = minDist[cur.n] + nxt.w;
				pq.offer(new Node(nxt.n, minDist[nxt.n]));
			}
		}

		return minDist[end];
	}

	static class Node {
		int n; // 도시 번호
		int w; // 비용

		Node(int n, int w) {
			this.n = n;
			this.w = w;
		}
	}
}
