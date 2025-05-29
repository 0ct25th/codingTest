import java.io.*;
import java.util.*;

public class Main {

	static int N, M, K;
	static List<Edge> edgeList;
	static int[] p;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 도시의 개수
		M = Integer.parseInt(st.nextToken()); // 설치 가능 케이블의 개수
		K = Integer.parseInt(st.nextToken()); // 발전소의 개수

		init();
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < K; i++)
			p[Integer.parseInt(st.nextToken())] = -1;

		edgeList = new ArrayList<>();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			edgeList.add(new Edge(u, v, w));
		}

		Collections.sort(edgeList, (o1, o2) -> (o1.w - o2.w));
		int result = 0; // 최소 비용
		for (Edge e : edgeList) {
			if (!union(e.u, e.v))
				continue;

			result += e.w;
			if (isAllConnected())
				break;
		}

		System.out.println(result);
	}

	static boolean isAllConnected() {
		for (int i = 1; i <= N; i++)
			if (p[i] != -1)
				return false;

		return true;
	}

	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);

		if (aRoot == bRoot)
			return false;
		else if (aRoot == -1)
			p[bRoot] = -1;
		else if (bRoot == -1)
			p[aRoot] = -1;
		else
			p[bRoot] = aRoot;

		return true;
	}

	static int find(int x) {
		if (p[x] == -1)
			return -1;
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
		int u, v; // 도시 번호
		int w; // 비용

		Edge(int u, int v, int w) {
			this.u = u;
			this.v = v;
			this.w = w;
		}
	}
}
