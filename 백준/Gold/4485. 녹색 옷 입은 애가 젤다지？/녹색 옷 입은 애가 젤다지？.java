import java.io.*;
import java.util.*;

public class Main {

	static final int INF = Integer.MAX_VALUE;

	static int N, result;
	static int[][] map, minCost;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		int testCase = 1;
		while (true) {
			N = Integer.parseInt(br.readLine());

			if (N == 0)
				break;

			map = new int[N][N];
			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			}

			result = dijkstra(0, 0);
			sb.append("Problem ").append(testCase++).append(": ").append(result).append("\n");
		}

		bw.write(sb.toString());
		bw.flush();

		br.close();
		bw.close();
	}

	static int dijkstra(int sr, int sc) {
		Queue<Coord> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.cost, o2.cost));
		minCost = new int[N][N];
		for (int r = 0; r < N; r++)
			Arrays.fill(minCost[r], INF);

		minCost[sr][sc] = map[sr][sc];
		pq.offer(new Coord(sr, sc, minCost[sr][sc]));

		while (!pq.isEmpty()) {
			Coord cur = pq.poll();
			int r = cur.r;
			int c = cur.c;
			int cost = cur.cost;
			
			if(cost > minCost[r][c])
				continue;

			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];

				if (!isValidCoord(nr, nc) || minCost[nr][nc] <= cost + map[nr][nc])
					continue;
				
				minCost[nr][nc] = cost + map[nr][nc];
				pq.offer(new Coord(nr ,nc, minCost[nr][nc]));
			}
		}

		return minCost[N - 1][N - 1];
	}

	static boolean isValidCoord(int r, int c) {
		return -1 < r && r < N && -1 < c && c < N;
	}

	static class Coord {
		int r, c;
		int cost;

		Coord(int r, int c, int cost) {
			this.r = r;
			this.c = c;
			this.cost = cost;
		}
	}
}
