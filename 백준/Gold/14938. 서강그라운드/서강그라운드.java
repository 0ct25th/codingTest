import java.io.*;
import java.util.*;

public class Main {

	static final int INF = Integer.MAX_VALUE;

	static int n, m, r, result;
	static int[] items, minDist;
	static List<Coord>[] adjList;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken()); // 지역 개수
		m = Integer.parseInt(st.nextToken()); // 수색 범위
		r = Integer.parseInt(st.nextToken()); // 길 개수

		items = new int[n + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++)
			items[i] = Integer.parseInt(st.nextToken());

		adjList = new ArrayList[n + 1];
		for (int i = 1; i <= n; i++)
			adjList[i] = new ArrayList<>();
		for (int i = 0; i < r; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int l = Integer.parseInt(st.nextToken());

			adjList[a].add(new Coord(b, l));
			adjList[b].add(new Coord(a, l));
		}

		minDist = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			Arrays.fill(minDist, INF);
			result = Math.max(result, dijkstra(i));
		}

		System.out.println(result);
	}

	static int dijkstra(int start) {
		Queue<Coord> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.dist, o2.dist));
		minDist[start] = 0;
		pq.offer(new Coord(start, minDist[start]));

		while (!pq.isEmpty()) {
			Coord cur = pq.poll();
			int n = cur.num;
			int d = cur.dist;

			for (Coord nxt : adjList[n]) {
				if (d + nxt.dist > m || minDist[nxt.num] <= d + nxt.dist)
					continue;

				minDist[nxt.num] = d + nxt.dist;
				pq.offer(new Coord(nxt.num, minDist[nxt.num]));
			}
		}

		int item = 0;
		for (int i = 1; i <= n; i++) {
			if (minDist[i] == INF)
				continue;

			item += items[i];
		}

		return item;
	}

	static class Coord {
		int num; // 지역 번호
		int dist; // 거리

		Coord(int n, int d) {
			this.num = n;
			this.dist = d;
		}
	}
}
