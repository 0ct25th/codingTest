import java.io.*;
import java.util.*;

public class Main {

	static int N, result;
	static int[] L, J;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		L = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			L[i] = Integer.parseInt(st.nextToken());

		J = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			J[i] = Integer.parseInt(st.nextToken());

		dfs(0, 0, 100);

		System.out.println(result);

	}

	static void dfs(int depth, int sum, int stamina) {
		// 기저 조건: 모든 사람을 고려한 경우
		if (depth == N) {
			result = Math.max(result, sum);
			return;
		}

		// 체력이 되는 경우 인사
		if (stamina - L[depth] > 0)
			dfs(depth + 1, sum + J[depth], stamina - L[depth]);

		dfs(depth + 1, sum, stamina);
	}
}
