import java.io.*;
import java.util.*;

public class Main {
	
	static int N;
	static int[] input, numbers;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st;
		
		while(true) {
			st = new StringTokenizer(br.readLine().strip());
			N = Integer.parseInt(st.nextToken());
			
			if(N == 0)
				break;
			
			input = new int[N];
			for(int i = 0; i < N; i++) 
				input[i] = Integer.parseInt(st.nextToken());
			
			numbers = new int[6];
			combination(0, 0);
			sb.append("\n");
			
		} // end of TestCase
		
		bw.write(sb.toString());
		bw.flush();
		
		br.close();
		bw.close();
	}
	
	static void combination(int depth, int start) {
		// 기저조건: 모든 수를 다 고른 경우
		if(depth == 6) {
			for(int number: numbers)
				sb.append(number).append(" ");
			sb.append("\n");
			
			return;
		}
		
		for(int i = start; i < N; i++) {
			numbers[depth] = input[i];
			combination(depth + 1, i + 1);
		}
	}
}
