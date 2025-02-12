import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int V; // 정점의 개수
	static int E; // 간선의 개수
	static int[] minEdge; // 최소 비용

	static ArrayList<Vertex>[] adjList; // 인접 리스트
	static boolean[] isVisited; // 방문 체크 배열

	static class Vertex implements Comparable<Vertex> {
		int num;
		int weight;

		public Vertex(int to, int weight) {
			super();
			this.num = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Vertex o) {
			return Integer.compare(this.weight, o.weight);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// 첫째 줄에 정점의 개수 V(1 ≤ V ≤ 10,000)와 간선의 개수 E(1 ≤ E ≤ 100,000)가 주어진다.
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		// 다음 E개의 줄에는 각 간선에 대한 정보를 나타내는 세 정수 A, B, C가 주어진다.
		// 이는 A번 정점과 B번 정점이 가중치 C인 간선으로 연결되어 있다는 의미이다.
		// C는 음수일 수도 있으며, 절댓값이 1,000,000을 넘지 않는다.
		adjList = new ArrayList[V + 1];
		for (int i = 1; i < V + 1; i++)
			adjList[i] = new ArrayList<>();

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());

			adjList[A].add(new Vertex(B, C));
			adjList[B].add(new Vertex(A, C));
		}

		////////// end of input

		// prim Algorithm
		// 자료구조 준비 및 초기화
		Queue<Vertex> pq = new PriorityQueue<>();
		isVisited = new boolean[V + 1];
		minEdge = new int[V + 1];
		Arrays.fill(minEdge, Integer.MAX_VALUE);

		// 임의의 정점 0을 시작점으로 잡고 최소 비용 초기화, 우선순위 큐 삽입
		minEdge[1] = 0;
		pq.offer(new Vertex(1, 0));

		// pq가 비어있을 때까지 실행
		long result = 0l;
		int cnt = 0;
		while (!pq.isEmpty()) {
			// pq에서 최소 비용 정점을 꺼내기
			Vertex minVertex = pq.poll();

			// 꺼낸 정점 방문 여부 체크
			if (isVisited[minVertex.num])
				continue;

			// 간선 비용 누적
			result += minEdge[minVertex.num];
			// 방문 체크
			isVisited[minVertex.num] = true;

			// 이미 MST가 만들어진 경우
			if (++cnt == V + 1)
				break;

			// 꺼낸 정점 인접 정점 최소 비용 갱신
			for (int i = 0; i < adjList[minVertex.num].size(); i++) {
				int next = adjList[minVertex.num].get(i).num;
				if (!isVisited[next] && minEdge[next] > adjList[minVertex.num].get(i).weight) {
					minEdge[next] = adjList[minVertex.num].get(i).weight;
					pq.offer(new Vertex(next, minEdge[next]));

				}
			} ///////////////// end of prim

		}

		System.out.println(result);

		// close
		br.close();
	}
}