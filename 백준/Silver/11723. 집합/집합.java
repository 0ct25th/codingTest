import java.io.*;
import java.util.*;

public class Main {

	static int M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		M = Integer.parseInt(br.readLine());

		int bit = 0;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			String op = st.nextToken();

			if (op.equals("add")) {
				int num = Integer.parseInt(st.nextToken());
				bit |= (1 << (num - 1));
			} else if (op.equals("remove")) {
				int num = Integer.parseInt(st.nextToken());
				bit = bit & ~(1 << (num - 1));
			} else if (op.equals("check")) {
				int num = Integer.parseInt(st.nextToken());
				sb.append((bit & (1 << (num - 1))) != 0 ? "1\n" : "0\n");
			} else if (op.equals("toggle")) {
				int num = Integer.parseInt(st.nextToken());
				bit ^= (1 << (num - 1));
			} else if (op.equals("all"))
				bit |= (~0);
			else
				bit &= 0;
		}

		bw.write(sb.toString());
		bw.flush();

		br.close();
		bw.close();
	}
}
