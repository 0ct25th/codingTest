import java.io.*;
import java.util.*;

public class Main {

	static int N, L, result;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 지도 행렬 길이
		L = Integer.parseInt(st.nextToken()); // 경사로 연속 칸

		map = new int[N][N];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++)
				map[r][c] = Integer.parseInt(st.nextToken());
		}

		//////////// end of Input

		// 가로
		for (int r = 0; r < N; r++)
			if (chkRow(r))
				result++;

		// 세로
		for (int c = 0; c < N; c++)
			if (chkCol(c))
				result++;

		System.out.println(result);
	}

	static boolean chkCol(int c) {
		boolean[] isVisited = new boolean[N]; // 경사로 여부 체크

		for (int r = 0; r < N - 1; r++) {
			int height = map[r][c] - map[r + 1][c]; // 경사 높이 차이

			// 경사가 같은 경우
			if (height == 0)
				continue; // 넘기기

			// 높이 차가 절대값 1이 아닌 경우
			if (Math.abs(height) != 1)
				return false; // 지나갈 수 없음

			// 오르막인 경우
			if (height == -1) {
				// 경사로 길이만큼 반복
				for (int d = 0; d < L; d++) {
					int nr = r - d;

					// 유효범위 밖 || 이미 경사로 || 이전 높이와 다른 경우
					if (!isValidCoord(nr) || isVisited[nr] || map[nr][c] != map[r][c])
						return false; // 경사로 설치 불가
				}

				// 경사로 설치
				for (int d = 0; d < L; d++)
					isVisited[r - d] = true;
			}

			// 내리막인 경우
			else {
				// 경사로 길이만큼 반복
				for (int d = 1; d <= L; d++) {
					int nr = r + d;

					// 유효범위 밖 || 이미 경사로 || 이전 높이와 다른 경우
					if (!isValidCoord(nr) || isVisited[nr] || map[nr][c] != map[r + 1][c])
						return false; // 경사로 설치 불가
				}

				// 경사로 설치
				for (int d = 1; d <= L; d++)
					isVisited[r + d] = true;
			}
		}

		return true;
	}

	static boolean chkRow(int r) {
		boolean[] isVisited = new boolean[N]; // 경사로 여부 체크

		for (int c = 0; c < N - 1; c++) {
			int height = map[r][c] - map[r][c + 1]; // 경사 높이 차이

			// 경사가 같은 경우
			if (height == 0)
				continue; // 넘기기

			// 높이 차가 절대값 1이 아닌 경우
			if (Math.abs(height) != 1)
				return false; // 지나갈 수 없음

			// 올라가는 경사로
			if (height == -1) {
				// 경사로 길이만큼 반복
				for (int d = 0; d < L; d++) {
					int nc = c - d;

					// 유효범위 밖 || 이미 경사로 || 이전 높이가 같지 않은 경우
					if (!isValidCoord(nc) || isVisited[nc] || map[r][nc] != map[r][c])
						return false; // 경사로 설치 불가
				}

				// 경사로 설치
				for (int d = 0; d < L; d++)
					isVisited[c - d] = true;
			}

			// 내려가는 경사로
			else {
				// 경사로 길이만큼 반복
				for (int d = 1; d <= L; d++) {
					int nc = c + d;

					// 유효범위 밖 || 이미 경사로 || 다음 높이가 같지 않은 경우
					if (!isValidCoord(nc) || isVisited[nc] || map[r][nc] != map[r][c + 1])
						return false; // 경사로 설치 불가
				}

				// 경사로 설치
				for (int d = 1; d <= L; d++)
					isVisited[c + d] = true;
			}
		}

		return true;
	}

	static boolean isValidCoord(int x) {
		return -1 < x && x < N;
	}

	static class Coord {
		int r, c;
		int h; // 높이

		Coord(int r, int c, int h) {
			this.r = r;
			this.c = c;
			this.h = h;
		}
	}
}
