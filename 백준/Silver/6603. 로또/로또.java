import java.io.*;
import java.util.*;

public class Main {

	static int k;
	static int[] S, n;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		while (true) {
			st = new StringTokenizer(br.readLine());

			k = Integer.parseInt(st.nextToken());
			if (k == 0)
				break;

			S = new int[k];
			for (int i = 0; i < k; i++)
				S[i] = Integer.parseInt(st.nextToken());

			n = new int[6];
			recursion(0, 0);
			sb.append("\n");
		}

		bw.write(sb.toString());
		bw.flush();

		br.close();
		bw.close();
	}

	static void recursion(int depth, int start) {
		if (depth == 6) {
			for (int i : n)
				sb.append(i).append(" ");

			sb.append("\n");

			return;
		}

		for (int i = start; i < k; i++) {
			n[depth] = S[i];
			recursion(depth + 1, i + 1);
		}
	}
}
