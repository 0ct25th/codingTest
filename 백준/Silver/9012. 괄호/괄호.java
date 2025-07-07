import java.io.*;
import java.util.*;

public class Main {

	static String str;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			str = br.readLine();

			sb.append(VPS() ? "YES\n" : "NO\n");
		}

		bw.write(sb.toString());
		bw.flush();

		br.close();
		bw.close();
	}

	static boolean VPS() {
		Stack<Character> stk = new Stack<>();

		for (char ch : str.toCharArray()) {
			if (ch == '(')
				stk.add('(');
			else {
				if (stk.isEmpty())
					return false;

				stk.pop();
			}
		}

		if (!stk.isEmpty())
			return false;

		return true;
	}
}
