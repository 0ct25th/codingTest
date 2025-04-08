import java.io.*;
import java.util.*;

public class Main {

	static int r, c, k, rLen, cLen;
	static int[][] A;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken()); // 행
		c = Integer.parseInt(st.nextToken()); // 열
		k = Integer.parseInt(st.nextToken()); // A[r][c]에 들어 있을 값

		A = new int[101][101];

		for (int i = 1; i <= 3; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= 3; j++)
				A[i][j] = Integer.parseInt(st.nextToken());
		}
		//////////// end of Input

		rLen = 3; // 행 개수
		cLen = 3; // 열 개수

		System.out.println(solution());
	}

	static int solution() {
		for (int t = 0; t <= 100; t++) {
			// A[r][c] 값이 k가 된 경우
			if (A[r][c] == k)
				return t;

			operating();
		}

		return -1;
	}

	static void operating() {
		// 행의 개수 ≥ 열의 개수인 경우
		if (rLen >= cLen) {
			// R 연산: 배열 A의 모든 행에 대해서 정렬을 수행
			for (int i = 1; i <= rLen; i++)
				R(i);
		}
		// 행의 개수 < 열의 개수인 경우
		else {
			// C 연산: 배열 A의 모든 열에 대해서 정렬을 수행
			for (int i = 1; i <= cLen; i++)
				C(i);
		}
	}

	static void C(int col) {
		Queue<Pair> pq = new PriorityQueue<>();
		Map<Integer, Integer> hash = new HashMap<>();

		for (int i = 1; i <= rLen; i++) {
			if (A[i][col] == 0)
				continue;

			hash.compute(A[i][col], (num, cnt) -> cnt == null ? 1 : cnt + 1);
		}

		hash.forEach((key, value) -> pq.add(new Pair(key, value)));
		int i = 1;
		while (!pq.isEmpty()) {
			Pair cur = pq.poll();
			A[i++][col] = cur.num;
			A[i++][col] = cur.cnt;
		}

		rLen = Math.max(rLen, i);
		while (i <= 99) {
			A[i++][col] = 0;
			A[i++][col] = 0;
		}
	}

	static void R(int row) {
		Queue<Pair> pq = new PriorityQueue<>();
		Map<Integer, Integer> hash = new HashMap<>();

		for (int i = 1; i <= cLen; i++) {
			if (A[row][i] == 0)
				continue;

			hash.compute(A[row][i], (num, cnt) -> cnt == null ? 1 : cnt + 1);
		}

		hash.forEach((key, value) -> pq.add(new Pair(key, value)));
		int j = 1;
		while (!pq.isEmpty()) {
			Pair cur = pq.poll();
			A[row][j++] = cur.num;
			A[row][j++] = cur.cnt;
		}

		cLen = Math.max(cLen, j);
		while (j <= 99) {
			A[row][j++] = 0;
			A[row][j++] = 0;
		}
	}

	static class Pair implements Comparable<Pair> {
		int num; // 번호
		int cnt; // 번호 등장 횟수

		Pair(int num, int cnt) {
			this.num = num;
			this.cnt = cnt;
		}

		@Override
		public int compareTo(Pair o) {
			if (this.cnt == o.cnt)
				return Integer.compare(this.num, o.num);

			return Integer.compare(this.cnt, o.cnt);
		}
	}
}
