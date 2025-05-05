import java.io.*;
import java.util.*;

public class Main {

	static int N, K;
	static boolean[] isVisited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 수빈 위치
		K = Integer.parseInt(st.nextToken()); // 동생 위치

		isVisited = new boolean[100_001];
		System.out.println(bfs(N));
	}

	static int bfs(int start) {
		Queue<Coord> dq = new ArrayDeque<>();
		isVisited[start] = true;
		dq.offer(new Coord(start, 0));

		while (!dq.isEmpty()) {
			Coord cur = dq.poll();
			int x = cur.x;
			int now = cur.t;

			if (x == K)
				return now;

			// 2 * X 위치로 이동한 경우
			if (isValidCoord(2 * x) && !isVisited[2 * x]) {
				isVisited[2 * x] = true;
				dq.offer(new Coord(2 * x, now));
			}

			// X - 1 위치로 이동한 경우
			if (isValidCoord(x - 1) && !isVisited[x - 1]) {
				isVisited[x - 1] = true;
				dq.offer(new Coord(x - 1, now + 1));
			}

			// X + 1 위치로 이동한 경우
			if (isValidCoord(x + 1) && !isVisited[x + 1]) {
				isVisited[x + 1] = true;
				dq.offer(new Coord(x + 1, now + 1));
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
