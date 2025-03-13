import java.io.*;
import java.util.*;

public class Main {

	static int N, M, result;
	static int[] minDist;
	static List<Node>[] nodeList;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		nodeList = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++)
			nodeList[i] = new ArrayList<>();

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken()); // 헛간 번호
			int B = Integer.parseInt(st.nextToken()); // 헛간 번호
			int C = Integer.parseInt(st.nextToken()); // 소 마리 수

			nodeList[A].add(new Node(B, C));
			nodeList[B].add(new Node(A, C));
		}

		minDist = new int[N + 1];
		Arrays.fill(minDist, Integer.MAX_VALUE);
		dijkstra(1);

		System.out.println(minDist[N]);
	}

	static void dijkstra(int start) {
		Queue<Node> pq = new PriorityQueue<>();

		// 시작 노드
		minDist[start] = 0;
		pq.offer(new Node(start, minDist[start]));

		while (!pq.isEmpty()) {
			Node cur = pq.poll();

			for (Node nxt : nodeList[cur.num]) {
				// 최소 거리 아닌 경우
				if (minDist[nxt.num] <= minDist[cur.num] + nxt.cows)
					continue; // 넘기기

				minDist[nxt.num] = minDist[cur.num] + nxt.cows; // 갱신
				pq.offer(new Node(nxt.num, minDist[nxt.num])); // 큐 삽입
			}
		}
	}

	static class Node implements Comparable<Node> {
		int num;
		int cows;

		Node(int num, int cows) {
			this.num = num;
			this.cows = cows;
		}

		@Override
		public int compareTo(Node o) {
			return (this.cows - o.cows);
		}
	}
}
