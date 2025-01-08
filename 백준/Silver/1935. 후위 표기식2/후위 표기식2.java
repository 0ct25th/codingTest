import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine().strip());
		String str = br.readLine().strip();
		
		int[] num = new int[N];
		Stack<Double> stk = new Stack<>();
		
		for (int i = 0; i < N; i++)
			num[i] = Integer.parseInt(br.readLine().strip());

		
		for (int i = 0; i < str.length(); i++) {
			switch (str.charAt(i)) {
			case '+':
				stk.add(stk.pop() + stk.pop());
				break;
				
			case '-':
				double a = stk.pop();
				double b = stk.pop();
				stk.add(b - a);
				break;
				
			case '*':
				stk.add(stk.pop() * stk.pop());
				break;
				
			case '/':
				a = stk.pop();
				b = stk.pop();
				stk.add(b / a);
				break;
			
			default:
				stk.add((double) (num[str.charAt(i) - 'A']));
				break;
			}
		}
		
		System.out.printf("%.2f", stk.pop());
	}
}
