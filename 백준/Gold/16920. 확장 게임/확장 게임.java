import java.io.*;
import java.util.*;

public class Main {

	static int N, M, P;
	static int map[][], S[];
	static List<Coord>[] player;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		// 첫째 줄에 격자판의 크기 N, M과 플레이어의 수 P가 주어진다.
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());

		// 둘째 줄에는 S1, S2, ...SP가 주어진다.
		S = new int[P + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= P; i++)
			S[i] = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		player = new ArrayList[P + 1];
		for (int i = 1; i <= P; i++)
			player[i] = new ArrayList<>();

		// 다음 N개의 줄에는 게임판의 상태가 주어진다.
		// '.'는 빈 칸, '#'는 벽, '1', '2', ..., '9'는 각 플레이어의 성이다.
		for (int r = 0; r < N; r++) {
			String str = br.readLine();
			for (int c = 0; c < M; c++) {
				char ch = str.charAt(c);

				switch (ch) {
				case '.': // 빈칸인 경우
					map[r][c] = 0;
					break;
					
				case '#': // 벽인 경우
					map[r][c] = 10;
					break;

				default: // 플레이어 소유 성인 경우
					int i = ch - '0';
					map[r][c] = i;
					player[i].add(new Coord(r, c, false));
					break;
				}
			}
		}
		///////////// end of Input

		// 모든 플레이어가 더 이상 확장을 할 수 없을 때 게임이 끝난다.
		while (true) {
			int expandCnt = 0; // 확장한 지역 개수

			for (int i = 1; i <= P; i++) {
				int before = player[i].size(); // 이전 성의 수 저장
				player[i] = bfs(player[i], i); // 성 확장
				expandCnt += player[i].size() - before;
			}

			// 확장한 지역이 더이상 없는 경우 종료
			if (expandCnt == 0)
				break;
		}

		// 플레이어 1이 가진 성의 수, 2가 가진 성의 수, ..., P가 가진 성의 수를 공백으로 구분해 출력한다.
		for (int i = 1; i <= P; i++)
			sb.append(player[i].size()).append(" ");

		System.out.println(sb);

		br.close();
	}

	static List<Coord> bfs(List<Coord> curPlayer, int idx) {
		Queue<Coord> dq = new ArrayDeque<>();

		// 현재 자신이 갖고 있는 성 bfs 탐색
		for (Coord cur : curPlayer) {
			// 이미 확장 완료한 좌표
			if(cur.isChk)
				continue; // 넘기기
			
			cur.isChk = true; // 확장 완료 표시
			dq.offer(new Coord(cur.r, cur.c));
		}
		
		 // S[idx]번 이동할 때까지 BFS 수행
	    for (int move = 1; move <= S[idx] && !dq.isEmpty(); move++) {
	        int size = dq.size(); // 현재 덱의 사이즈
	        
	        // 현재 깊이의 모든 지점을 처리
	        for (int i = 0; i < size; i++) {
	            Coord cur = dq.poll();
	            int r = cur.r;
	            int c = cur.c;
	            
	            // 4방향 탐색
	            for (int d = 0; d < 4; d++) {
	                int nr = r + dr[d];
	                int nc = c + dc[d];
	                
	                // 유효범위 밖 || 빈칸 아닌 경우
	                if (!isValidCoord(nr, nc) || map[nr][nc] != 0) 
	                	continue; // 넘기기
	                
	                map[nr][nc] = idx; // 본인 성으로 확장
	                curPlayer.add(new Coord(nr, nc, false));
	                dq.offer(new Coord(nr, nc));
	            }
	        }
	    }
	    
		return curPlayer;
	}
	
	static boolean isValidCoord(int r, int c) {
		return -1 < r && r < N &&  -1 < c && c < M;
	}

	static class Coord {
		int r, c;
		boolean isChk; // 성 확장 여부

		Coord(int r, int c, boolean isChk) {
			this.r = r;
			this.c = c;
			this.isChk = isChk;
		}

		Coord(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
