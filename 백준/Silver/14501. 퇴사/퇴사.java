import java.io.*;
import java.util.*;

public class Main {

	static int N, T[], P[], result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		T = new int[N];
		P = new int[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			T[i] = Integer.parseInt(st.nextToken());
			P[i] = Integer.parseInt(st.nextToken());
		}
		////////////// end of Input

		result = Integer.MIN_VALUE;
		recursion(0, 0);

		System.out.println(result);
	}

	static void recursion(int depth, int sum) {
		// 기저 조건: 퇴사 일정을 넘는 경우
		if (depth > N) {
			return;
		}

		// 기저 조건: 모든 일수 고려 완료
		if (depth == N) {
			result = Math.max(result, sum);
			return;
		}

		// 해당 상담을 선택 한 경우
		recursion(depth + T[depth], sum + P[depth]);

		// 해당 상담을 선택하지 않은 경우
		recursion(depth + 1, sum);
	}
}
