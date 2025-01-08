import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine().strip());
		for(int t = 0; t < T; t++) {
			sb.append(chk(br.readLine()));
		} // end of TestCase
		
		bw.write(sb.toString());
		bw.flush();
		
		br.close();
		bw.close();
	}
	
	static String chk(String str) {
		Stack<Integer> stk = new Stack<>();
		
		for (int i = 0; i < str.length(); i++) {
			if(str.charAt(i) == '(')
				stk.add(0);
			else {
				if (stk.isEmpty())
					return "NO\n";
				
				stk.pop();
			}
		}
		
		// stk에 괄호가 남은 경우
		if(!stk.isEmpty())
			return "NO\n";
		
		return "YES\n";
	}
}
