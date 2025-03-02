import java.io.*;
import java.util.*;

public class Main {

	static int N, D;
	static int[] minDist;
	static boolean[] isVisited;
	static List<Road>[] roads;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());

		roads = new ArrayList[10001];
		for (int i = 0; i < 10001; i++)
			roads[i] = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int distance = Integer.parseInt(st.nextToken());

			if(end > D)
				continue;
			
			roads[start].add(new Road(end, distance));
		}

		dijkstra(0);

		System.out.println(minDist[D]);
	}

	static void dijkstra(int start) {
		// 자료구조 초기화
		Queue<Coord> pq = new PriorityQueue<>();
		minDist = new int[10001];
		for (int i = 0; i < 10001; i++)
			minDist[i] = i;

		// 시작점
		minDist[start] = 0;
		pq.offer(new Coord(start, minDist[start]));

		while (!pq.isEmpty()) {
			Coord cur = pq.poll();
			int x = cur.x;
			int d = cur.d;
			
			// 도착한 경우
			if(x == D)
				return;

			// 한 칸 앞으로 전진
			int nx = x + 1;

			// 유효범위 밖 || 최소 경로 아닌 경우
			if (!isValidCoord(nx) || minDist[nx] < d + 1)
				continue; // 넘기기

			minDist[nx] = d + 1;
			pq.offer(new Coord(nx, minDist[nx]));

			// 지름길 있는 경우
			for (Road r : roads[x]) {
				// 최소 경로 아닌 경우
				if (minDist[r.end] < d + r.distance)
					continue; // 넘기기

				minDist[r.end] = d + r.distance; // 최소 경로 갱신
				pq.offer(new Coord(r.end, minDist[r.end])); // 우선순위 큐 삽입
			}
		}
	}

	static boolean isValidCoord(int x) {
		return -1 < x && x < 10001;
	}

	static class Coord implements Comparable<Coord> {
		int x, d;

		Coord(int x, int d) {
			this.x = x;
			this.d = d;
		}

		@Override
		public int compareTo(Coord o) {
			return Integer.compare(this.d, o.d);
		}
	}

	static class Road {
		int end, distance;

		Road(int end, int distance) {
			this.end = end;
			this.distance = distance;
		}
	}
}
