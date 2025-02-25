import java.io.*;
import java.util.*;

public class Main {

	static int N, M;
	static double result;
	static int[] p;
	static List<Coord> coords;
	static List<Edge> edgeList;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 우주신들의 개수
		M = Integer.parseInt(st.nextToken()); // 이미 연결된 신들과의 통로 개수

		// 우주신들의 좌표 주어짐
		coords = new ArrayList<>();
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			long x = Long.parseLong(st.nextToken());
			long y = Long.parseLong(st.nextToken());

			coords.add(new Coord(i, x, y));
		}

		// 이미 연결된 통로들이 주어짐
		edgeList = new ArrayList<>();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			edgeList.add(new Edge(a, b, 0));
		}

		// 거리 계산
		distCalc();

		// 크루스컬 알고리즘 시행
		kruskal();

		result = Math.round(result * 100) / 100.0;
		System.out.println(String.format("%.2f", result));
	}

	static void kruskal() {
		// 전처리
		Collections.sort(edgeList);
		make();

		int cnt = 0;
		result = 0;

		for (Edge e : edgeList) {
			if (cnt == N - 1)
				return;

			if (union(e.from, e.to))
				continue;

			result += e.dist;
			cnt++;
		}
	}

	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);

		if (aRoot == bRoot)
			return true;

		p[bRoot] = aRoot;
		return false;
	}

	static int find(int x) {
		if (x == p[x])
			return x;

		return p[x] = find(p[x]);
	}

	static void distCalc() {
		for (int i = 0; i < coords.size(); i++) {
			Coord cur = coords.get(i);
			for (int j = i + 1; j < coords.size(); j++) {
				Coord nxt = coords.get(j);

				double dist = Math.sqrt(Math.pow(cur.x - nxt.x, 2) + Math.pow(cur.y - nxt.y, 2));

				edgeList.add(new Edge(cur.num, nxt.num, dist));
			}
		}
	}

	static void make() {
		p = new int[N + 1];
		for (int i = 1; i <= N; i++)
			p[i] = i;
	}

	static class Edge implements Comparable<Edge> {
		int from, to;
		double dist;

		Edge(int from, int to, double dist) {
			this.from = from;
			this.to = to;
			this.dist = dist;
		}

		@Override
		public int compareTo(Edge o) {
			return Double.compare(this.dist, o.dist);
		}
	}

	static class Coord {
		int num;
		long x, y;

		Coord(int num, long x, long y) {
			this.num = num;
			this.x = x;
			this.y = y;
		}
	}
}
