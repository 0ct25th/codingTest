import java.io.*;
import java.util.*;

public class Main {

	static int N, result;
	static int[] L, J;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		L = new int[N]; // 체력
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			L[i] = Integer.parseInt(st.nextToken());

		J = new int[N]; // 기쁨		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			J[i] = Integer.parseInt(st.nextToken());

		result = Integer.MIN_VALUE;
		dfs(0, 100, 0);

		System.out.println(result);
	}

	static void dfs(int depth, int hp, int joy) {
		// 가지 치기: 현재 체력이 0이나 음수가 된 경우
		if (hp <= 0) {
			return;
		}

		// 기저 조건: 모든 사람에게 감사한 경우
		if (depth == N) {
			result = Math.max(result, joy);

			return;
		}

		dfs(depth + 1, hp - L[depth], joy + J[depth]);
		dfs(depth + 1, hp, joy);
	}
}
