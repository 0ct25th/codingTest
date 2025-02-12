import java.io.*;
import java.util.*;

public class Main {

	static int N, M;
	static int[] p, schedule;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine()); // 도시의 수
		M = Integer.parseInt(br.readLine()); // 여행 계획에 속한 도시들의 수

		// 다음 N개의 줄에는 N개의 정수가 주어진다. i번째 줄의 j번째 수는 i번 도시와 j번 도시의 연결 정보를 의미한다.
		make();
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				int c = Integer.parseInt(st.nextToken());

				// 연결되지 않은 경우
				if (c == 0)
					continue;

				union(i, j);
			}
		}

		// 마지막 줄에는 여행 계획이 주어진다. 도시의 번호는 1부터 N까지 차례대로 매겨져 있다.
		schedule = new int[M];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++)
			schedule[i] = Integer.parseInt(st.nextToken());

		System.out.println(possible());
	}

	static String possible() {
		for (int i = 0; i < M - 1; i++) {
			if (!isUnion(schedule[i], schedule[i + 1]))
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
			return x;

		return p[x] = find(p[x]);
	}

	static void make() {
		p = new int[N + 1];

		for (int i = 1; i <= N; i++)
			p[i] = i;
	}
}
