import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int l, result;
	static int[] start, end;
	static int[][] isVisited;
	static int[] dr = { -2, -2, -1, -1, 1, 1, 2, 2 };
	static int[] dc = { -1, 1, -2, 2, -2, 2, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		// 입력의 첫 줄에는 테스트 케이스의 개수 T가 주어진다.
		int T = Integer.parseInt(br.readLine().trim());
		for(int t = 0; t < T; t++) {
			// 첫째 줄에는 체스판의 한 변의 길이 l(4 ≤ l ≤ 300)이 주어진다.
			l = Integer.parseInt(br.readLine().trim());
			
			// 둘째 줄과 셋째 줄에는 나이트가 현재 있는 칸, 나이트가 이동하려고 하는 칸이 주어진다.
			start = new int[2];
			st = new StringTokenizer(br.readLine().trim());
			start[0] = Integer.parseInt(st.nextToken());
			start[1] = Integer.parseInt(st.nextToken());
			
			end = new int[2];
			st = new StringTokenizer(br.readLine().trim());
			end[0] = Integer.parseInt(st.nextToken());
			end[1] = Integer.parseInt(st.nextToken());
			
			//////////////////////////////////////////////////// end of Input
			
			isVisited = new int[l][l];
			bfs(start[0], start[1]);
		
			// 결과 넣기
			sb.append(result).append("\n");
			
		} // end of TestCase
		
		// 출력
		bw.write(sb.toString());
		bw.flush();

		// close
		br.close();
		bw.close();
	} // end of main
	
	static void bfs(int sr, int sc) {
		Queue<Coord> dq = new ArrayDeque<>();
		isVisited[sr][sc] = 1;
		dq.offer(new Coord(sr, sc, 1));
		
		while(!dq.isEmpty()) {
			Coord cur = dq.poll();
			int r = cur.r;
			int c = cur.c;
			int v = cur.value;
			
			if(r == end[0] && c == end[1]) {
				result = v -1;
				
				return;
			}
			
			for (int d = 0; d < 8; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				if(!isValidCoord(nr, nc) || isVisited[nr][nc] == 1)
					continue;
				
				isVisited[nr][nc] = 1;
				dq.offer(new Coord(nr, nc, v + 1));
			}
		}
	}
	
	static boolean isValidCoord(int r, int c) {
		return -1 < r && r < l && -1 < c && c < l;
	}
	
	static class Coord {
		int r;
		int c;
		int value;
		
		public Coord(int r, int c, int value) {
			super();
			this.r = r;
			this.c = c;
			this.value = value;
		}
	}
}