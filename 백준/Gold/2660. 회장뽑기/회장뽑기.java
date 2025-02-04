import java.io.*;
import java.util.*;

public class Main {

	static final int INF = 987654321;
	static int N, minScore = INF;
	static int[] scores;
	static int[][] arr;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		arr = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++)
			for (int j = 1; j <= N; j++)
				if (i != j)
					arr[i][j] = INF;

		while (true) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			if (a == -1 && b == -1) {
				break;
			}

			// 양방향 그래프
			arr[a][b] = arr[b][a] = 1;
		}

		// 플로이드 와샬 알고리즘 수행
		for (int k = 1; k <= N; k++)
			for (int i = 1; i <= N; i++)
				for (int j = 1; j <= N; j++)
					if (arr[i][j] > arr[i][k] + arr[k][j])
						arr[i][j] = arr[i][k] + arr[k][j];

		// 친구 거리
		scores = new int[N + 1]; // 친구 점수 목록
		for (int i = 1; i <= N; i++) {
			int score = 0;

			for (int j = 1; j <= N; j++)
				if (arr[i][j] != INF)
					score = Math.max(score, arr[i][j]);

			scores[i] = score;
			minScore = Math.min(minScore, score);
		}

		// 후보 찾기
		int count = 0; // 같은 점수가진 후보 수
		StringBuilder second = new StringBuilder();
		for (int i = 1; i <= N; i++) {
			if (minScore == scores[i]) {
				count++;
				second.append(i).append(" ");
			}
		}

		StringBuilder first = new StringBuilder();
		first.append(minScore + " ");
		first.append(count).append("\n");

		bw.write(first.toString());
		bw.write(second.toString());

		bw.flush();
		bw.close();
		br.close();
	}

}
