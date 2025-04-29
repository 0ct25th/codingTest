import java.io.*;
import java.util.*;

public class Main {

	static long N, result;
	static Stack<Long> stk;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Long.parseLong(br.readLine());

		stk = new Stack<>();
		for (int i = 0; i < N; i++) {
			long cur = Long.parseLong(br.readLine()); // 현재 빌딩 높이

			while (!stk.isEmpty()) {
				long prev = stk.peek(); // 이전 빌딩 높이

				if (prev <= cur)
					stk.pop();
				else
					break;
			}

			result += stk.size();
			stk.push(cur);
		}

		System.out.println(result);
	}
}
