import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static Stack<Integer> stk;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());

		stk = new Stack<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int op = Integer.parseInt(st.nextToken());

			if (op == 1) {
				int num = Integer.parseInt(st.nextToken());

				stk.add(num);
			} else if (op == 2) {
				if (stk.isEmpty())
					sb.append("-1\n");
				else
					sb.append(stk.pop()).append("\n");
			} else if (op == 3) {
				sb.append(stk.size()).append("\n");
			} else if (op == 4) {
				if (stk.isEmpty())
					sb.append("1\n");
				else
					sb.append("0\n");
			} else if (op == 5) {
				if (stk.isEmpty())
					sb.append("-1\n");
				else
					sb.append(stk.peek()).append("\n");
			}
		}

		bw.write(sb.toString());
		bw.flush();

		br.close();
		bw.close();
	}
}
