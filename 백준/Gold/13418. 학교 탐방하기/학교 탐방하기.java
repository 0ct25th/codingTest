import java.io.*;
import java.util.*;

public class Main {

	static int N, M;
	static List<Edge> edgeList, ascList;
	static int[] p;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 건물의 개수
		M = Integer.parseInt(st.nextToken()); // 도로의 개수

		edgeList = new ArrayList<>();
		for (int i = 0; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			edgeList.add(new Edge(a, b, c));
		}

		// best 계산
		init();
		Collections.sort(edgeList, (o1, o2) -> -(o1.c - o2.c)); // 내리막길순 정렬
		ascList = new ArrayList<>();
		int cnt = 0;

		for (Edge e : edgeList) {
			if (!union(e.a, e.b))
				continue;

			if (e.c == 0) // 오르막길인 경우
				ascList.add(e);

			if (++cnt == N)
				break;
		}
		int best = (int) Math.pow(ascList.size(), 2);

		// worst 계산
		init();
		Collections.sort(edgeList, (o1, o2) -> (o1.c - o2.c)); // 오르막순 정렬
		ascList = new ArrayList<>();
		cnt = 0;

		for (Edge e : edgeList) {
			if (!union(e.a, e.b))
				continue;
			
			if (e.c == 0) // 오르막길인 경우
				ascList.add(new Edge(e.a, e.b, e.c));

			if (++cnt == N)
				break;
		}
		int worst = (int) Math.pow(ascList.size(), 2);

		System.out.println(worst - best);
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

		for (int i = 0; i <= N; i++)
			p[i] = i;
	}

	static class Edge {
		int a, b; // 번호
		int c; // 경사도

		Edge(int a, int b, int c) {
			this.a = a;
			this.b = b;
			this.c = c;
		}
	}
}
