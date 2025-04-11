import java.io.*;
import java.util.*;

public class Main {

	static int N, M, K, result;
	static int[] order;
	static boolean[] isChk;
	static int[][] A;
	static List<Operation> ops;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		A = new int[N + 1][M + 1];
		for (int r = 1; r <= N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 1; c <= M; c++)
				A[r][c] = Integer.parseInt(st.nextToken());
		}

		ops = new ArrayList<>();
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());

			ops.add(new Operation(r, c, s));
		}
		////////////// end of Input

		result = Integer.MAX_VALUE;
		isChk = new boolean[K];
		order = new int[K];
		dfs(0);

		System.out.println(result);
	}

	static void dfs(int depth) {
		// 기저 조건: 명령어 배치가 끝난 경우
		if (depth == K) {
			result = Math.min(result, calc());

			return;
		}

		for (int i = 0; i < K; i++) {
			if (isChk[i])
				continue;

			order[depth] = i;
			isChk[i] = true;
			dfs(depth + 1);
			isChk[i] = false;
		}
	}

	static int calc() {
		int minValue = Integer.MAX_VALUE;
		int[][] tmp = new int[N + 1][M + 1]; // 복사한 배열
		for (int i = 1; i <= N; i++)
			for (int j = 1; j <= M; j++)
				tmp[i][j] = A[i][j];

		for (int i = 0; i < K; i++) {
			Operation op = ops.get(order[i]);

			tmp = move(op.r, op.c, op.s, tmp);
		}

		for (int i = 1; i <= N; i++) {
			int sum = 0;
			for (int j = 1; j <= M; j++)
				sum += tmp[i][j];

			minValue = Math.min(minValue, sum);
		}

		return minValue;
	}

	static int[][] move(int r, int c, int s, int[][] tmp) {
		for (int layer = 0; layer < s; layer++) {
			// 현재 레이어의 시작 위치 (왼쪽 위 모서리)
			int startRow = r - s + layer;
			int startCol = c - s + layer;
			// 현재 레이어의 종료 위치 (오른쪽 아래 모서리)
			int endRow = r + s - layer;
			int endCol = c + s - layer;

			// 현재 레이어의 윗 행 가장 왼쪽 값 저장
			int v = tmp[startRow][startCol];

			// 왼쪽 열, 위로 이동
			for (int i = startRow; i < endRow; i++)
				tmp[i][startCol] = tmp[i + 1][startCol];

			// 아래 행, 왼쪽으로 이동
			for (int j = startCol; j < endCol; j++)
				tmp[endRow][j] = tmp[endRow][j + 1];

			// 오른쪽 열, 아래쪽으로 이동
			for (int i = endRow; i > startRow; i--)
				tmp[i][endCol] = tmp[i - 1][endCol];

			// 위 행, 오른쪽으로 이동
			for (int j = endCol; j > startCol; j--)
				tmp[startRow][j] = tmp[startRow][j - 1];

			// 윗 행 가장 왼쪽 위치의 다음 위치에 저장해둔 값 채우기
			tmp[startRow][startCol + 1] = v;
		}

		return tmp;
	}

	static class Operation {
		int r, c, s;

		Operation(int r, int c, int s) {
			this.r = r;
			this.c = c;
			this.s = s;
		}
	}
}
