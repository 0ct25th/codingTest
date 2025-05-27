import java.io.*;
import java.util.*;

public class Main {

	static int N, M;
	static int[] p, city;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine()); // 도시의 수
		M = Integer.parseInt(br.readLine()); // 여행 계획에 속한 도시들의 수

		init();
		for (int r = 1; r <= N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 1; c <= N; c++) {
				int con = Integer.parseInt(st.nextToken());

				if (con == 0)
					continue;

				union(r, c);
			}
		}

		city = new int[M];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++)
			city[i] = Integer.parseInt(st.nextToken());

		System.out.println(possible());
	}

	static String possible() {
		for (int i = 0; i < M - 1; i++) {
			if (!isUnion(city[i], city[i + 1]))
				return "NO";
		}

		return "YES";
	}

	static boolean isUnion(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);

		if (aRoot == bRoot)
			return true;
		else
			return false;
	}

	static void union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);

		if (aRoot == bRoot)
			return;

		p[bRoot] = aRoot;
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
}
