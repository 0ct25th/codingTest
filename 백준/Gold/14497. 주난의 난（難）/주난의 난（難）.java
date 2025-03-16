import java.io.*;
import java.util.*;

public class Main {
	
	static int N, M, map[][];
	
	static int[] start, end;
	static int[][] minDist;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		start = new int[2];
		end = new int[2];
		st = new StringTokenizer(br.readLine());
		start[0] = Integer.parseInt(st.nextToken());
		start[1] = Integer.parseInt(st.nextToken());
		end[0] = Integer.parseInt(st.nextToken());
		end[1] = Integer.parseInt(st.nextToken());
		
		map = new int[N + 1][M + 1];
		for(int r = 1; r <= N; r++) {
			String str = br.readLine();
			for(int c = 1; c <= M; c++) {
				char cur = str.charAt(c - 1);
				
				switch(cur) {
				case '0':
				case '1':
					map[r][c] = cur - '0';
					break;
				case '*':
					map[r][c] = 0;
					break;
				case '#':
					map[r][c] = 1; 
					break;
				}
			}
		}
		//////////////// end of Input
		
		System.out.println(dijkstra(start[0], start[1]));
	}
	
	static int dijkstra(int sr, int sc) {
		Queue<Coord> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.cnt, o2.cnt));
		minDist = new int[N + 1][M + 1];
		for(int i = 1; i <= N; i++)
			Arrays.fill(minDist[i], Integer.MAX_VALUE);
		
		// 시작점
		minDist[sr][sc] = 0;
		pq.offer(new Coord(sr, sc, 0));
		
		while(!pq.isEmpty()) {
			Coord cur = pq.poll();
			
			if(cur.r == end[0] && cur.c == end[1])
				return cur.cnt;
			
			for(int d = 0; d < 4; d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];
				
				if(!isValidCoord(nr, nc) || minDist[nr][nc] <= minDist[cur.r][cur.c] + 1)
					continue;
				
				// 빈 공간인 경우
				if(map[nr][nc] == 0) {
					minDist[nr][nc] = minDist[cur.r][cur.c] + 1;
					pq.offer(new Coord(nr, nc, cur.cnt)); // 점프 횟수 그대로
				}
				
				// 친구인 경우
				else {
					minDist[nr][nc] = minDist[cur.r][cur.c] + 1;
					pq.offer(new Coord(nr, nc, cur.cnt + 1)); // 점프 횟수 증가
				}
			}
		}
		
		return 0;
	}
	
	static boolean isValidCoord(int r, int c) {
		return 0 < r && r <= N && 0 < c && c <= M;
	}
	
	static class Coord {
		int r, c; // 좌표
		int cnt; // 점프 횟수
		
		Coord(int r, int c, int cnt) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}
	}
}
