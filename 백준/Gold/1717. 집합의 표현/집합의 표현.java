import java.io.*;
import java.util.*;

public class Main {

	static int n, m;
	static int[] p;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// 첫째 줄에 n, m이 주어진다.
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken()); // 연산의 개수

		// 다음 m개의 줄에는 각각의 연산이 주어진다.
		make(); // 전처리
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());

			int flag = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			if (flag == 0)
				union(flag, a, b);
			else
				System.out.println(union(flag, a, b));
		}
	}

	static String union(int flag, int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);

		if (aRoot == bRoot)
			return flag == 0 ? "" : "YES";

		if (flag == 0) {
			p[bRoot] = aRoot;
			return "";
		} else
			return "NO";

	}

	static int find(int x) {
		if (x == p[x])
			return x;

		return p[x] = find(p[x]);
	}

	static void make() {
		p = new int[1000001];

		for (int i = 1; i <= 1000000; i++)
			p[i] = i;
	}
}
