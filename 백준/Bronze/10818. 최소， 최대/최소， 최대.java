import java.io.*;
import java.util.*;

public class Main {
	
	static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		int max = Integer.parseInt(st.nextToken());
		int min = max;
		
		for(int i = 0; i < N - 1; i++) {
			int cur = Integer.parseInt(st.nextToken());
			if(max < cur)
				max = cur;
			if(min > cur)
				min = cur;
		}
		
		sb.append(min).append(" ").append(max);
		
		bw.write(sb.toString());
		bw.flush();
		
		br.close();
		bw.close();
	}
}
