import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

	static int N; // 집의 개수, 정점
	static int M; // 길의 개수, 간선
	static ArrayList<Edge> edgeList; // 간선 리스트
	static int[] p; // 대표자 저장 배열

	static class Edge implements Comparable<Edge> {
		int from;
		int to;
		int weight;

		public Edge(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// 첫째 줄에 집의 개수 N, 길의 개수 M이 주어진다.
		// N은 2이상 100,000이하인 정수이고, M은 1이상 1,000,000이하인 정수이다.
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		// 그 다음 줄부터 M줄에 걸쳐 길의 정보가 A B C 세 개의 정수로 주어지는데
		// A번 집과 B번 집을 연결하는 길의 유지비가 C (1 ≤ C ≤ 1,000)라는 뜻이다.
		edgeList = new ArrayList<>();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());

			edgeList.add(new Edge(A, B, C));
		}

		// 간선 리스트 오름차순 정렬
		Collections.sort(edgeList);

		p = new int[N + 1];
		make();

		// 크루스컬 알고리즘
		int cnt = 0;
		long weight = 0;
		if (N != 2) {
			for (Edge e : edgeList) {
				if (!union(e.from, e.to))
					continue;

				weight += e.weight;
				if (++cnt == N - 2)
					break;
			}
		}

		System.out.println(weight);
	}

	static void make() {
		for (int i = 1; i < N + 1; i++)
			p[i] = i;
	}

	static int find(int x) {
		if (x == p[x])
			return x;

		return p[x] = find(p[x]);
	}

	static boolean union(int x, int y) {
		x = find(x);
		y = find(y);

		if (x == y)
			return false;

		p[y] = x;
		return true;
	}
}
