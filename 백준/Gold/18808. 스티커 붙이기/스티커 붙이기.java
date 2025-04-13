import java.io.*;
import java.util.*;

public class Main {

	static int N, M, K, result;
	static List<Sticker> stickers;
	static boolean[][] isVisited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 세로 길이
		M = Integer.parseInt(st.nextToken()); // 가로 길이
		K = Integer.parseInt(st.nextToken()); // 스티커 개수

		stickers = new ArrayList<>();
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int R = Integer.parseInt(st.nextToken()); // 행의 개수
			int C = Integer.parseInt(st.nextToken()); // 열의 개수

			int[][] shape = new int[R][C];
			for (int r = 0; r < R; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < C; c++)
					shape[r][c] = Integer.parseInt(st.nextToken());
			}

			stickers.add(new Sticker(R, C, shape));
		}
		//////////////////////// end of Input

		isVisited = new boolean[N][M];
		search(0, 0);

		System.out.println(result);
	}

	static void search(int depth, int sum) {
		// 기저 조건: 모든 스티커를 붙인 경우
		if (depth == K) {
			result = Integer.max(result, sum);

			return;
		}

		// 현재 고려할 스티커
		Sticker cur = stickers.get(depth);

		// 회전을 하지 않고 스티커 붙이기
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				// 유효 범위를 벗어난 경우
				if (!isValidCoord(cur.R, cur.C, i, j))
					continue;

				// 현재 스티커 붙여보기
				int tmp = attach(i, j, cur);

				// 현재 스티커를 붙일 수 없는 경우
				if (tmp == -1)
					continue;

				// 다음 스티커 붙이기
				search(depth + 1, sum + tmp);
			}
		}

		// 회전해서 스티커 붙이기
		for (int d = 0; d < 3; d++) {
			// 스티커 회전
			rotate(cur);

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					// 유효 범위를 벗어난 경우
					if (!isValidCoord(cur.R, cur.C, i, j))
						continue;

					// 현재 스티커 붙여보기
					int tmp = attach(i, j, cur);

					// 현재 스티커를 붙일 수 없는 경우
					if (tmp == -1)
						continue;

					// 다음 스티커 붙이기
					search(depth + 1, sum + tmp);
				}
			}
		}

		// 현재 스티커 붙이지 않고 버리기
		search(depth + 1, sum);
	}

	static void rotate(Sticker cur) {
		// 90도 회전한 스티커 저장 배열
		int[][] tmpShape = new int[cur.C][cur.R];

		// 90도 회전
		for (int i = 0; i < cur.R; i++)
			for (int j = 0; j < cur.C; j++)
				tmpShape[j][cur.R - i - 1] = cur.shape[i][j];

		// 행, 열 스왑
		int tmp = cur.R;
		cur.R = cur.C;
		cur.C = tmp;

		// 돌린 배열 변경
		cur.shape = tmpShape;
	}

	static int attach(int r, int c, Sticker cur) {
		int cnt = 0; // 스티커 붙인 칸 수

		for (int i = 0; i < cur.R; i++) {
			for (int j = 0; j < cur.C; j++) {
				// 스티커 붙어 있지 않은 경우
				if (cur.shape[i][j] == 0)
					continue;

				// 이미 방문한 경우
				if (isVisited[r + i][c + j])
					return -1;

				cnt++;
			}
		}

		// 스티커 모두 붙인 후 방문 체크
		for (int i = 0; i < cur.R; i++) {
			for (int j = 0; j < cur.C; j++) {
				// 스티커 붙어 있지 않은 경우
				if (cur.shape[i][j] == 0)
					continue;

				isVisited[r + i][c + j] = true; // 방문 체크
			}
		}

		return cnt;
	}

	static boolean isValidCoord(int R, int C, int i, int j) {
		return R <= N - i && C <= M - j;
	}

	static class Sticker {
		int R, C;
		int[][] shape; // 0: 스티커 붙지 않은 칸, 1: 스티커 붙은 칸

		Sticker(int R, int C, int[][] shape) {
			this.R = R;
			this.C = C;
			this.shape = shape;
		}
	}
}
