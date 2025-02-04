import java.io.*;
import java.util.*;

public class Main {

	static final int INF = Integer.MAX_VALUE;

	static int N, M;
	static int[] minDist;
	static List<City>[] adjList;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine()); // 도시의 개수 -> 정점 개수
		M = Integer.parseInt(br.readLine()); // 버스의 개수 -> 간선 개수

		adjList = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++)
			adjList[i] = new ArrayList<>();

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			adjList[start].add(new City(end, cost));
		}

		minDist = new int[N + 1];
		Arrays.fill(minDist, INF);

		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		dijkstra(start, end);

		System.out.println(minDist[end]);
	}

	static void dijkstra(int start, int end) {
		Queue<City> pq = new PriorityQueue<>();
		boolean[] isVisited = new boolean[N + 1];

		// 시작점
		minDist[start] = 0;
		pq.offer(new City(start, minDist[start]));

		while (!pq.isEmpty()) {
			City cur = pq.poll();

			// 이미 방문한 경우
			if (isVisited[cur.num])
				continue;

			// 방문 체크
			isVisited[cur.num] = true;

			for (City next : adjList[cur.num]) {
				// 미방문 && 현재 최소 비용보다 작은 비용인 경우
				if (!isVisited[next.num] && minDist[next.num] > minDist[cur.num] + next.cost) {
					minDist[next.num] = minDist[cur.num] + next.cost;
					pq.offer(new City(next.num, minDist[next.num]));
				}
			}
		}
	}

	static class City implements Comparable<City> {
		int num; // 도시 번호
		int cost; // 버스 비용

		City(int num, int cost) {
			this.num = num;
			this.cost = cost;
		}

		@Override
		public int compareTo(City o) {
			return Integer.compare(this.cost, o.cost);
		}
	}

}
