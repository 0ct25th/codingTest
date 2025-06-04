import java.io.*;
import java.util.*;

public class Main {

	static int n, result;
	static int[] s;
	static boolean[] isTeam, isVisited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			n = Integer.parseInt(br.readLine());
			result = n;

			s = new int[n + 1];
			isTeam = new boolean[n + 1];
			isVisited = new boolean[n + 1];

			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= n; i++)
				s[i] = Integer.parseInt(st.nextToken());

			for (int i = 1; i <= n; i++) {
				if (!isTeam[i])
					dfs(i);
			}

			sb.append(result).append("\n");
		}

		System.out.println(sb);
	}

	static void dfs(int cur) {
		isVisited[cur] = true;
		int next = s[cur];

		if (!isVisited[next])
			dfs(next);

		else if (!isTeam[next]) { // 사이클 발견!
			// 사이클에 속한 학생 수만큼 result 감소
			int cnt = 1;
			for (int i = next; i != cur; i = s[i])
				cnt++;
			result -= cnt;
		}

		isTeam[cur] = true; // 탐색이 끝난 학생은 팀 편성 완료
	}
}
