import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static int N;

	static boolean[] col;
	static boolean[] lCross;
	static boolean[] rCross;
	static int result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 첫째 줄에 N이 주어진다. (1 ≤ N < 15)
		N = Integer.parseInt(br.readLine());

		// 함수 호출
		col = new boolean[N];
		lCross = new boolean[1 << N];
		rCross = new boolean[1 << N];
		queen(0);
		
		// 결과 출력
		System.out.println(result);
		
		// close
		br.close();
	}

	private static void queen(int cnt) {
		// 기저 조건
		if (cnt == N) {
			result++;
			return;
		}

		for (int i = 0; i < N; i++) {
			if (col[i] || lCross[cnt + i] || rCross[cnt - i + N - 1])
				continue;
			
			// 퀸을 해당 좌표(cnt, i)에 놓음
			col[i] = true;
			lCross[cnt + i] = true;
			rCross[cnt - i + N - 1] = true;
			queen(cnt + 1);
			// 퀸을 해당 좌표에서 뺌 -> 원상복구
			col[i] = false;
			lCross[cnt + i] = false;
			rCross[cnt - i + N - 1] = false;
		}
	}
}
