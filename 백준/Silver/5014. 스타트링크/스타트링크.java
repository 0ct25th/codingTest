import java.io.*;
import java.util.*;

public class Main {

	static int F, S, G;
	static int[] dx;
	static boolean isVisited[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		dx = new int[2];
		st = new StringTokenizer(br.readLine());
		F = Integer.parseInt(st.nextToken()); // 총 건물 층
		S = Integer.parseInt(st.nextToken()); // 현재 위치
		G = Integer.parseInt(st.nextToken()); // 목표 층
		dx[0] = Integer.parseInt(st.nextToken()); // 위
		dx[1] = -Integer.parseInt(st.nextToken()); // 아래

		isVisited = new boolean[F + 1];
		int result = bfs(S);
		System.out.println(result == -1 ? "use the stairs" : result);
	}

	static int bfs(int s) {
		Queue<Gangho> dq = new ArrayDeque<>();

		isVisited[s] = true;
		dq.offer(new Gangho(s, 0));

		while (!dq.isEmpty()) {
			Gangho cur = dq.poll();
			int x = cur.x;
			int cnt = cur.cnt;

			if (x == G)
				return cnt;

			for (int d = 0; d < 2; d++) {
				int nx = x + dx[d];
				if (!isValidCoord(nx) || isVisited[nx])
					continue;

				isVisited[nx] = true;
				dq.offer(new Gangho(nx, cnt + 1));
			}
		}

		return -1;
	}

	static boolean isValidCoord(int x) {
		return 0 < x && x <= F;
	}

	static class Gangho {
		int x; // 위치
		int cnt; // 누른 버튼 수

		Gangho(int x, int cnt) {
			this.x = x;
			this.cnt = cnt;
		}
	}
}
