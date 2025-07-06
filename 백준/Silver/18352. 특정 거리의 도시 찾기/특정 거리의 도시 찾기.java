import java.io.*;
import java.util.*;

public class Main {

	static final int INF = Integer.MAX_VALUE;

	static int N, M, K, X;
	static List<Integer>[] adjList;
	static int[] minDist;
	static Queue<Integer> cityPQ;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 도시 개수
		M = Integer.parseInt(st.nextToken()); // 도로 개수
		K = Integer.parseInt(st.nextToken()); // 거리 정보
		X = Integer.parseInt(st.nextToken()); // 출발 도시 번호

		adjList = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++)
			adjList[i] = new ArrayList<>();

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());

			// 단방향 도로
			adjList[A].add(B);
		}

		dijkstra(X);

		cityPQ = new PriorityQueue<>();
		int result = calc();

		if (result != -1) {
			while (!cityPQ.isEmpty())
				System.out.println(cityPQ.poll());
		} else
			System.out.println(-1);
	}

	static int calc() {
		int cnt = 0;

		for (int i = 1; i <= N; i++) {
			if (minDist[i] != K)
				continue;

			cnt++;
			cityPQ.offer(i);
		}

		return cnt == 0 ? -1 : cnt;
	}

	static void dijkstra(int start) {
		Queue<Node> pq = new PriorityQueue<>((o1, o2) -> (o1.d - o2.d));
		minDist = new int[N + 1];
		Arrays.fill(minDist, INF);

		// 시작점
		minDist[start] = 0;
		pq.offer(new Node(start, minDist[start]));

		int cnt = N; // 거리 측정
		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			int n = cur.n;
			int d = cur.d;

			// 모든 도시 측정한 경우
			if (cnt == 0)
				return;

			for (int nxt : adjList[n]) {
				if (minDist[nxt] < d + 1)
					continue;

				cnt--;
				minDist[nxt] = d + 1;
				pq.offer(new Node(nxt, minDist[nxt]));
			}
		}
	}

	static class Node {
		int n; // 노드 번호
		int d; // 거리

		Node(int n, int d) {
			this.n = n;
			this.d = d;
		}
	}

}
