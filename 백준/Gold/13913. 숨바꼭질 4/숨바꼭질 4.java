import java.io.*;
import java.util.*;

public class Main {

	static int N, K;
	static int[] isVisited, order;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 수빈 위치
		K = Integer.parseInt(st.nextToken()); // 동생 위치

		isVisited = new int[100_001];
		order = new int[100_001];
		int result = bfs(N);
		System.out.println(result);

		Stack<Integer> stk = new Stack<>();
		int idx = K;
		while (idx != N) {
		    stk.push(idx);
		    idx = order[idx];
		}
		stk.push(N);
		while (!stk.isEmpty())
			System.out.printf("%d ", stk.pop());
	}

	static int bfs(int start) {
		Queue<Coord> dq = new ArrayDeque<>();
		Arrays.fill(isVisited, Integer.MAX_VALUE);

		isVisited[start] = 0;
		dq.offer(new Coord(start, isVisited[start]));

		while (!dq.isEmpty()) {
			Coord cur = dq.poll();
			int x = cur.x;
			int t = cur.t;

			if (x == K)
				return t;

			// 순간이동
			if (isValidCoord(2 * x) && isVisited[2 * x] > t + 1) {
				order[2 * x] = x;
				isVisited[2 * x] = t + 1;
				dq.offer(new Coord(2 * x, isVisited[2 * x]));
			}

			// 한 칸 뒤
			if (isValidCoord(x - 1) && isVisited[x - 1] > t + 1) {
				order[x - 1] = x;
				isVisited[x - 1] = t + 1;
				dq.offer(new Coord(x - 1, isVisited[x - 1]));
			}

			// 한 칸 앞
			if (isValidCoord(x + 1) && isVisited[x + 1] > t + 1) {
				order[x + 1] = x;
				isVisited[x + 1] = t + 1;
				dq.offer(new Coord(x + 1, isVisited[x + 1]));
			}
		}

		return -1;
	}

	static boolean isValidCoord(int x) {
		return -1 < x && x < 100_001;
	}

	static class Coord {
		int x; // 위치
		int t; // 시간

		Coord(int x, int t) {
			this.x = x;
			this.t = t;
		}
	}
}
