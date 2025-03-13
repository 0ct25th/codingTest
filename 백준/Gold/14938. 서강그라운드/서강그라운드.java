import java.io.*;
import java.util.*;

public class Main {

	static int n, m, r, result;
	static int[] items;
	static List<Node>[] nodeList;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken()); // 지역의 개수 = 노드 개수
		m = Integer.parseInt(st.nextToken()); // 수색 범위
		r = Integer.parseInt(st.nextToken()); // 길의 개수 = 간선 개수

		items = new int[n + 1]; // 각 구역에 있는 아이템의 수
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++)
			items[i] = Integer.parseInt(st.nextToken());

		nodeList = new ArrayList[n + 1];
		for (int i = 1; i <= n; i++)
			nodeList[i] = new ArrayList<>();
		for (int i = 0; i < r; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()); // 지역 번호
			int b = Integer.parseInt(st.nextToken()); // 지역 번호
			int l = Integer.parseInt(st.nextToken()); // 길의 길이

			// 양방향 그래프
			nodeList[a].add(new Node(b, l));
			nodeList[b].add(new Node(a, l));
		}

		for (int i = 1; i <= n; i++)
			result = Math.max(result, dijkstra(i));

		System.out.println(result);
	}

	static int dijkstra(int start) {
		Queue<Node> pq = new PriorityQueue<>((o1, o2) -> (o1.d - o2.d));
		int[] minDist = new int[n + 1];
		Arrays.fill(minDist, Integer.MAX_VALUE);
		int sum = 0;

		// 시작 노드
		minDist[start] = 0;
		pq.offer(new Node(start, 0));

		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			int num = cur.num;
			int d = cur.d;

			for (Node nxt : nodeList[num]) {
				// 수색 범위를 넘어 서는 경우
				if (d + nxt.d > m)
					continue; // 넘기기

				// 최소 거리가 아닌 경우
				if (minDist[nxt.num] < minDist[num] + nxt.d)
					continue; // 넘기기

				// 최소 거리 갱신
				minDist[nxt.num] = minDist[num] + nxt.d;
				// 우선순위 큐 삽입
				pq.offer(new Node(nxt.num, minDist[nxt.num]));
			}
		}
		
		for(int i = 1; i <= n; i++)
			if(minDist[i] != Integer.MAX_VALUE)
				sum += items[i];

		return sum;
	}

	static class Node {
		int num, d;

		Node(int num, int d) {
			this.num = num;
			this.d = d;
		}
	}
}
