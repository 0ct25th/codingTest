import java.io.*;
import java.util.*;

public class Main {

	static int n, m;
	static int[] p;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken()); // 점의 개수
		m = Integer.parseInt(st.nextToken()); // 진행된 차례의 수

		init();

		int order;
		boolean isEnd = false;
		for (order = 1; order <= m; order++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			if (find(a) == find(b)) {
				isEnd = true;
				break;
			}

			union(a, b);
		}

		if (isEnd) {
			System.out.println(order);
		} else
			System.out.println(0);
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
		p = new int[n];

		for (int i = 0; i < n; i++)
			p[i] = i;
	}
}
