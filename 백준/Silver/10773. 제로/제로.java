import java.io.*;
import java.util.*;

public class Main {

	static int K;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		K = Integer.parseInt(br.readLine());
		Stack<Integer> stk = new Stack<>();
		for (int k = 0; k < K; k++) {
			int num = Integer.parseInt(br.readLine());

			if (num == 0) {
				if (stk.isEmpty())
					continue;

				stk.pop();
			} else
				stk.add(num);
		}

		int result = 0;
		while (!stk.isEmpty())
			result += stk.pop();

		System.out.println(result);
	}
}
