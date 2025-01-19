import java.io.*;
import java.util.*;

public class Main {

	static int N, M;
	static int[] picks;
	static StringBuilder sb;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st;
		sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		picks = new int[M];
		permutation(0, 0);

		bw.write(sb.toString());
		bw.flush();

		br.close();
		bw.close();
	}

	static void permutation(int depth, int start) {
		// 기저조건: M개를 모두 선택한 경우
		if (depth == M) {
			for (int pick : picks)
				sb.append(pick).append(" ");

			sb.append("\n");

			return;
		}

		for (int i = start; i < N; i++) {
			picks[depth] = i + 1;
			permutation(depth + 1, i);
		}
	}
}
