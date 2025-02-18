import java.io.*;
import java.util.*;

public class Main {

    static int N, map[][], result;
    static List<Coord> land;
    static boolean[][] isVisited;
    static int[] dr = { -1, 1, 0, 0 };
    static int[] dc = { 0, 0, -1, 1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        isVisited = new boolean[N][N]; // N 초기화 후 배열 생성
        land = new ArrayList<>();
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());

                if (map[r][c] == 1)
                    land.add(new Coord(r, c));
            }
        }
        ///////////// end of Input

        int idx = 2;
        for (Coord coord : land) {
            if (map[coord.r][coord.c] == 1) {
                bfs(coord.r, coord.c, idx);
                idx++;
            }
        }

        result = Integer.MAX_VALUE;
        
        // 모든 육지 좌표에서 다리 건설 시도
        for (Coord coord : land) {
            result = Math.min(result, calc(coord.r, coord.c));
        }

        System.out.println(result);
    }

    static int calc(int sr, int sc) {
        Queue<Coord> dq = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][N]; // 각 시작점마다 새로운 방문 배열

        visited[sr][sc] = true;
        dq.offer(new Coord(sr, sc, 0));
        int startLand = map[sr][sc]; // 시작 대륙 번호 저장

        while (!dq.isEmpty()) {
            Coord cur = dq.poll();
            int r = cur.r;
            int c = cur.c;
            int cnt = cur.d;

            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];

                // 유효범위 밖 || 이미 방문
                if (!isValidCoord(nr, nc) || visited[nr][nc])
                    continue; // 넘기기

                // 다른 대륙 찾은 경우
                if (map[nr][nc] != 0 && map[nr][nc] != startLand)
                    return cnt;
                
                // 바다인 경우 (거리 증가)
                if (map[nr][nc] == 0) {
                    visited[nr][nc] = true;
                    dq.offer(new Coord(nr, nc, cnt + 1));
                }
                
                // 같은 대륙인 경우 (거리 증가 없음)
                else if (map[nr][nc] == startLand) {
                    visited[nr][nc] = true;
                    dq.offer(new Coord(nr, nc, cnt));
                }
            }
        }

        return Integer.MAX_VALUE;
    }

    static void bfs(int sr, int sc, int idx) {
        Queue<Coord> dq = new ArrayDeque<>();

        map[sr][sc] = idx;
        dq.offer(new Coord(sr, sc));

        while (!dq.isEmpty()) {
            Coord cur = dq.poll();
            int r = cur.r;
            int c = cur.c;

            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];

                // 유효범위 밖 || 방문 완료(다른 번호) || 바다
                if (!isValidCoord(nr, nc) || map[nr][nc] != 1)
                    continue; // 넘기기

                map[nr][nc] = idx; // 대륙 번호 변경
                dq.offer(new Coord(nr, nc));
            }
        }
    }

    static boolean isValidCoord(int r, int c) {
        return -1 < r && r < N && -1 < c && c < N;
    }

    static class Coord{
        int r, c;
        int d; // 거리

        Coord(int r, int c) {
            this.r = r;
            this.c = c;
        }

        Coord(int r, int c, int d) {
            this.r = r;
            this.c = c;
            this.d = d;
        }
    }
}