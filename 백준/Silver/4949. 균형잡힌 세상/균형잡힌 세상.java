import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		while (true) {
			String str = br.readLine();
			
			if (str.equals("."))
				break;

			sb.append(isBalanced(str) ? "yes\n" : "no\n");
		}

		System.out.print(sb.toString());
	}

	static boolean isBalanced(String str) {
		Stack<Character> stack = new Stack<>();

		for (char c : str.toCharArray()) {
			if (c == '(' || c == '[')
				stack.push(c);
			else if (c == ')' || c == ']') {
				if (stack.isEmpty())
					return false;

				char top = stack.pop();
				if ((c == ')' && top != '(') || (c == ']' && top != '['))
					return false;

			}
		}

		return stack.isEmpty();
	}
}
