import java.io.*;
import java.util.*;

public class Main {

	static int N, result;
	static List<Coord> coordList;
	static List<Edge> edgeList;
	static int[] p;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine()); // 행성의 개수

		coordList = new ArrayList<>();
		// N개 줄에는 각 행성의 x, y, z좌표가 주어진다.
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());

			// 좌표 저장
			coordList.add(new Coord(i, x, y, z));
		}
		/////////////////////////// end of Input

		// x, y, z 각각에 대해서 정렬하고 각 행성의 번호와 비용을 edgeList에 추가
		edgeList = new ArrayList<>();
		sort(0); // x축 기준으로 정렬
		sort(1); // y축 기준으로 정렬
		sort(2); // z축 기준으로 정렬

		kruskal();

		System.out.println(result);

	}

	static void kruskal() {
		// 간선 비용 오름차순 정렬
		Collections.sort(edgeList);

		// 전처리
		make();

		int cnt = 0;
		for (Edge edge : edgeList) {
			// 같은 트리인 경우
			if (union(edge.from, edge.to))
				continue; // 넘기기

			// 최소 비용 더하기
			result += edge.weight;

			// 모든 행성을 터널로 연결 완료
			if (++cnt == N)
				break;
		}
	}

	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);

		if (aRoot == bRoot)
			return true;

		p[aRoot] = bRoot;
		return false;
	}

	static int find(int x) {
		if (x == p[x])
			return x;

		return p[x] = find(p[x]);
	}

	static void make() {
		p = new int[N];

		for (int i = 0; i < N; i++)
			p[i] = i;
	}

	static void sort(int axis) {
		// 축에 따라 정렬
		Collections.sort(coordList, (a, b) -> {
			if (axis == 0)
				return Integer.compare(a.x, b.x);
			else if (axis == 1)
				return Integer.compare(a.y, b.y);
			else
				return Integer.compare(a.z, b.z);
		});

		// 인접한 행성들 간의 간선 추가
		for (int i = 0; i < N - 1; i++) {
			int weight;
			if (axis == 0)
				weight = Math.abs(coordList.get(i).x - coordList.get(i + 1).x);
			else if (axis == 1)
				weight = Math.abs(coordList.get(i).y - coordList.get(i + 1).y);
			else
				weight = Math.abs(coordList.get(i).z - coordList.get(i + 1).z);

			edgeList.add(new Edge(coordList.get(i).num, coordList.get(i + 1).num, weight));
		}
	}

	static class Edge implements Comparable<Edge> {
		int from, to, weight;

		Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}
	}

	static class Coord {
		int num; // 좌표 번호
		int x, y, z;

		Coord(int num, int x, int y, int z) {
			this.num = num;
			this.x = x;
			this.y = y;
			this.z = z;
		}
	}
}
