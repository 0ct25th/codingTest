import java.io.*;
import java.util.*;

public class Main {

	static int A, K;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		A = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		System.out.println(bfs());
	}

	static int bfs() {
		Queue<Coord> dq = new ArrayDeque<>();
		boolean[] isVisited = new boolean[K + 1];

		isVisited[A] = true;
		dq.offer(new Coord(A, 0));

		while (!dq.isEmpty()) {
			Coord cur = dq.poll();

			if (cur.num == K)
				return cur.cnt;

			 // 1을 더한다.
            if (cur.num + 1 <= K && !isVisited[cur.num + 1]) {
                isVisited[cur.num + 1] = true;
                dq.offer(new Coord(cur.num + 1, cur.cnt + 1));
            }

            // 2를 곱한다.
            if (cur.num * 2 <= K && !isVisited[cur.num * 2]) {
                isVisited[cur.num * 2] = true;
                dq.offer(new Coord(cur.num * 2, cur.cnt + 1));
            }
		}

		return 0;
	}

	static class Coord {
		int num;
		int cnt;

		Coord(int num, int cnt) {
			this.num = num;
			this.cnt = cnt;
		}
	}
}
