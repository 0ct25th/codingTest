import java.io.*;
import java.util.*;

public class Main {

	static int n, m, s, t;
	static int[] minDist;
	static List<Node>[] nodeList;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken()); // 정점 개수
		m = Integer.parseInt(st.nextToken()); // 간선 개수

		nodeList = new ArrayList[n + 1];
		for (int i = 1; i <= n; i++)
			nodeList[i] = new ArrayList<>();

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			// 무방향 그래프
			nodeList[a].add(new Node(b, c));
			nodeList[b].add(new Node(a, c));
		}

		st = new StringTokenizer(br.readLine());
		s = Integer.parseInt(st.nextToken());
		t = Integer.parseInt(st.nextToken());

		dijkstra(s);
		System.out.println(minDist[t]);
	}

	static void dijkstra(int start) {
		Queue<Node> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.weight, o2.weight));
		minDist = new int[n + 1];
		Arrays.fill(minDist, Integer.MAX_VALUE);

		minDist[start] = 0;
		pq.offer(new Node(start, minDist[start]));

		while (!pq.isEmpty()) {
			Node cur = pq.poll();

			if (cur.num == t)
				return;
			
			if(cur.weight > minDist[cur.num])
				continue;

			for (Node nxt : nodeList[cur.num]) {
				if (minDist[nxt.num] <= minDist[cur.num] + nxt.weight)
					continue;

				minDist[nxt.num] = minDist[cur.num] + nxt.weight;
				pq.offer(new Node(nxt.num, minDist[nxt.num]));
			}
		}
	}

	static class Node {
		int num, weight;

		Node(int num, int weight) {
			this.num = num;
			this.weight = weight;
		}
	}
}
