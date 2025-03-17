import java.io.*;
import java.util.*;

public class Main {

	static int N, M, K;
	static int[] friends, ans, minDist;
	static List<Node>[] nodeList;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 방의 개수(정점)
			M = Integer.parseInt(st.nextToken()); // 비밀 통로의 개수(간선)

			nodeList = new ArrayList[N + 1];
			for (int i = 1; i <= N; i++)
				nodeList[i] = new ArrayList<>();

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken()); // 방 번호
				int b = Integer.parseInt(st.nextToken()); // 방 번호
				int c = Integer.parseInt(st.nextToken()); // 비밀 통로 길이

				// 양방향
				nodeList[a].add(new Node(b, c));
				nodeList[b].add(new Node(a, c));
			}

			K = Integer.parseInt(br.readLine());

			friends = new int[K];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < K; i++)
				friends[i] = Integer.parseInt(st.nextToken());
			////////////////////////// end of Input

			ans = new int[N + 1];
			for (int i = 0; i < K; i++) {
				dijkstra(friends[i]);

				for (int j = 1; j <= N; j++)
					ans[j] += minDist[j];
			}

			int result = Integer.MAX_VALUE;
			int minRoom = 0;
			for (int i = 1; i <= N; i++) {
				if (ans[i] < result) {
					result = ans[i];
					minRoom = i;
				}
			}

			sb.append(minRoom).append("\n");
		}

		System.out.println(sb);
	}

	static void dijkstra(int start) {
		Queue<Node> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.dist, o2.dist));
		minDist = new int[N + 1];
		Arrays.fill(minDist, Integer.MAX_VALUE);

		minDist[start] = 0;
		pq.offer(new Node(start, minDist[start]));

		while (!pq.isEmpty()) {
			Node cur = pq.poll();

			for (Node nxt : nodeList[cur.num]) {
				if (minDist[nxt.num] <= minDist[cur.num] + nxt.dist)
					continue;

				minDist[nxt.num] = minDist[cur.num] + nxt.dist;
				pq.offer(new Node(nxt.num, minDist[nxt.num]));

			}
		}
	}

	static class Node {
		int num, dist;

		Node(int num, int dist) {
			this.num = num;
			this.dist = dist;
		}
	}
}
