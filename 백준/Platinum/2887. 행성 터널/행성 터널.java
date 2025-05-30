import java.io.*;
import java.util.*;

public class Main {

	static int N, result;
	static List<Planet> coordList;
	static List<Edge> edgeList;
	static int[] p;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine()); // 행성의 개수

		coordList = new ArrayList<>();
		// N개 줄에는 각 행성의 x, y, z좌표가 주어진다.
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());

			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());

			coordList.add(new Planet(i, x, y, z));
		}

		edgeList = new ArrayList<>();
		sort(0); // x축 기준으로 정렬
		sort(1); // y축 기준으로 정렬
		sort(2); // z축 기준으로 정렬

		System.out.println(kruskal());

	}

	static long kruskal() {
		long result = 0;
		int cnt = 0;
		Collections.sort(edgeList, (o1, o2) -> (o1.d - o2.d));
		init();

		for (Edge edge : edgeList) {
			if (!union(edge.a, edge.b))
				continue;
			result += edge.d;

			if (++cnt == N)
				break;
		}

		return result;
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
		if (x == p[x])
			return x;

		return p[x] = find(p[x]);
	}

	static void init() {
		p = new int[N + 1];

		for (int i = 1; i <= N; i++)
			p[i] = i;
	}

	static void sort(int axis) { // 축에 따라 정렬
		Collections.sort(coordList, (a, b) -> {
			if (axis == 0)
				return Integer.compare(a.x, b.x);
			else if (axis == 1)
				return Integer.compare(a.y, b.y);
			else
				return Integer.compare(a.z, b.z);
		});

		for (int i = 0; i < N - 1; i++) {
			int dist;

			if (axis == 0)
				dist = Math.abs(coordList.get(i).x - coordList.get(i + 1).x);
			else if (axis == 1)
				dist = Math.abs(coordList.get(i).y - coordList.get(i + 1).y);
			else
				dist = Math.abs(coordList.get(i).z - coordList.get(i + 1).z);

			edgeList.add(new Edge(coordList.get(i).n, coordList.get(i + 1).n, dist));
		}
	}

	static class Edge {
		int a, b; // 행성들 번호
		int d; // 거리

		Edge(int a, int b, int d) {
			this.a = a;
			this.b = b;
			this.d = d;
		}
	}

	static class Planet {
		int n; // 행성 번호
		int x, y, z;

		Planet(int n, int x, int y, int z) {
			this.n = n;
			this.x = x;
			this.y = y;
			this.z = z;
		}
	}
}
