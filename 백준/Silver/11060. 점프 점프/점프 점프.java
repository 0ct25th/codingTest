import java.io.*;
import java.util.*;

public class Main {

	static int N, A[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine()); // 1 ~ 1000

		A = new int[N + 1]; // 1부터 시작
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++)
			A[i] = Integer.parseInt(st.nextToken());

		System.out.println(bfs(1));
	}

	static int bfs(int start) {
		Queue<Coord> dq = new ArrayDeque<>();
		boolean[] isVisited = new boolean[N + 1];

		// 시작점
		isVisited[start] = true;
		dq.offer(new Coord(start, 0));

		while (!dq.isEmpty()) {
			Coord cur = dq.poll();
			int x = cur.x;
			int cnt = cur.cnt;

			// 가장 오른쪽으로 간 경우
			if (x == N)
				return cnt;

			for (int i = 1; i <= A[x]; i++) {
				int nx = x + i;

				// 유효범위 밖 || 이미 들린 경우
				if (!isValidCoord(nx) || isVisited[nx])
					continue; // 넘기기

				isVisited[nx] = true;
				dq.offer(new Coord(nx, cnt + 1));
			}
		}

		return -1; // 가장 오른쪽으로 갈 수 없는 경우
	}

	static boolean isValidCoord(int x) {
		return 0 < x && x <= N;
	}

	static class Coord {
		int x, cnt;

		Coord(int x, int cnt) {
			this.x = x;
			this.cnt = cnt;
		}
	}
}
