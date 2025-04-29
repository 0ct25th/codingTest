import java.io.*;
import java.util.*;

public class Main {

	static int n;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		n = Integer.parseInt(br.readLine());

		Stack<Integer> stk = new Stack<>();
		int nxt = 1;
		boolean isPossible = true;
		for (int i = 0; i < n; i++) {
			int num = Integer.parseInt(br.readLine());

			while (nxt <= num) {
				stk.push(nxt++);
				sb.append("+\n");
			}

			if (!stk.isEmpty() && stk.peek() == num) {
				stk.pop();
				sb.append("-\n");
			} else {
				isPossible = false;
				break;
			}
		}

		if (isPossible)
			System.out.println(sb);
		else
			System.out.println("NO");
	}

}
