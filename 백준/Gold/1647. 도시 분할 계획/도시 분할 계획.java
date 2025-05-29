import java.io.*;
import java.util.*;

public class Main {

	static int N, M;
	static List<Edge> edgeList;
	static Queue<Edge> connectEdgeList;
	static int[] p;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 집의 개수
		M = Integer.parseInt(st.nextToken()); // 길의 개수

		edgeList = new ArrayList<>();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			edgeList.add(new Edge(a, b, c));
		}

		Collections.sort(edgeList, (o1, o2) -> (o1.w - o2.w));
		init();

		long result = 0;
		int cnt = 0;
		connectEdgeList = new PriorityQueue<>((o1, o2) -> -(o1.w - o2.w));
		for (Edge e : edgeList) {
			if (!union(e.a, e.b))
				continue;

			connectEdgeList.offer(e);
			result += e.w;
			if (++cnt == N)
				break;
		}

		result -= connectEdgeList.poll().w;

		System.out.println(result);
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
			return p[x];

		return p[x] = find(p[x]);
	}

	static void init() {
		p = new int[N + 1];

		for (int i = 1; i <= N; i++)
			p[i] = i;
	}

	static class Edge {
		int a, b; // 집 번호
		int w; // 유지비

		Edge(int a, int b, int w) {
			this.a = a;
			this.b = b;
			this.w = w;
		}
	}
}
