import java.io.*;
import java.util.*;

public class Main {

	static int n, m;
	static int[] p;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		init();
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());

			int op = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			if (op == 0) {
				union(a, b);
			} else if (op == 1) {
				if(find(a) == find(b))
					System.out.println("YES");
				else
					System.out.println("NO");
			}
		}
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
		p = new int[n + 1];

		for (int i = 1; i <= n; i++)
			p[i] = i;
	}
}
