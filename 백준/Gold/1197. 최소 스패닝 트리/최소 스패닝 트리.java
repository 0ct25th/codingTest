import java.io.*;
import java.util.*;

public class Main {

	static final int INF = 987654321;
	static int V, E, result;
	static List<Vertex>[] adjList;
	static int[] minDist;
	static boolean[] isVisited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken()); // 정점의 개수
		E = Integer.parseInt(st.nextToken()); // 간선의 개수

		// 인접 리스트 선언
		adjList = new ArrayList[V + 1];
		for (int i = 1; i <= V; i++)
			adjList[i] = new ArrayList<>();

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());

			int A = Integer.parseInt(st.nextToken()); // 정점
			int B = Integer.parseInt(st.nextToken()); // 정점
			int C = Integer.parseInt(st.nextToken()); // 가중치

			// 무방향 그래프
			adjList[A].add(new Vertex(B, C));
			adjList[B].add(new Vertex(A, C));
		}
		////////////////////// end of Input

		prim(1);

		System.out.println(result);

	}

	static void prim(int start) {
		Queue<Vertex> pq = new PriorityQueue<>();
		minDist = new int[V + 1]; // 간선 비용 최소값 저장 배열
		Arrays.fill(minDist, INF);
		isVisited = new boolean[V + 1];

		// 시작점
		minDist[start] = 0;
		pq.offer(new Vertex(1, 0));

		int cnt = 0; // 방문한 정점 개수
		while (!pq.isEmpty()) {
			// pq에서 최소 비용 정점 꺼내기
			Vertex cur = pq.poll();

			// 이미 방문한 정점인 경우
			if (isVisited[cur.num])
				continue;

			isVisited[cur.num] = true; // 방문 처리
			result += minDist[cur.num]; // 비용 누적

			// 모든 정점 방문한 경우
			if (++cnt == V)
				return;

			for (Vertex next : adjList[cur.num]) {
				// 이미 방문한 경우 || 최소 비용이 더 작은 경우
				if (isVisited[next.num] || minDist[next.num] <= next.weight)
					continue;

				minDist[next.num] = next.weight;
				pq.offer(new Vertex(next.num, minDist[next.num]));
			}
		}

	}

	static class Vertex implements Comparable<Vertex> {
		int num, weight;

		Vertex(int num, int weight) {
			this.num = num;
			this.weight = weight;
		}

		@Override
		public int compareTo(Vertex o) {
			return Integer.compare(this.weight, o.weight);
		}
	}
}
