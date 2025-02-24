import java.io.*;
import java.util.*;

public class Main {

	static int N, p[];
	static long result;
	static List<Edge> list;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		list = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int c = Integer.parseInt(st.nextToken());

				list.add(new Edge(i, j, c));
			}
		}
		////////// end of Input

		// 유지비용 오름차순 정렬
		Collections.sort(list);

		kruskal();

		System.out.println(result);
	}

	static void kruskal() {
		// 전처리
		make();

		int cnt = 1;
		for (Edge now : list) {
			if (!union(now.from, now.to))
				continue;

			result += now.w;
			if (++cnt == N)
				break;
		}
	}

	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);

		if (aRoot == bRoot)
			return false;

		p[bRoot] = aRoot;
		return true;
	}

	static int find(int x) {
		if (p[x] == x)
			return x;

		return p[x] = find(p[x]);
	}

	static void make() {
		p = new int[N + 1];

		for (int i = 1; i <= N; i++)
			p[i] = i;
	}

	static class Edge implements Comparable<Edge> {
		int from, to;
		int w;

		Edge(int from, int to, int w) {
			this.from = from;
			this.to = to;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.w, o.w);
		}
	}
}
