import java.io.*;
import java.util.*;

public class Main {
	
	static int N, K;
	static Queue<Integer> dq;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		// 첫째 줄에 N과 K가 빈 칸을 사이에 두고 순서대로 주어진다. (1 ≤ K ≤ N ≤ 1,000)
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		////////////////////////////////////// input
		
		dq = new ArrayDeque<>();
		for(int i = 1; i <= N; i++) 
			dq.offer(i);
		

		sb.append("<");
		int index = 1;
		while(dq.size() != 1) {
			if (index == K) {
				sb.append(dq.poll()).append(", ");
				index = 1;
			} else {
				dq.offer(dq.poll());
				index++;
			}
		}
		
		sb.append(dq.poll()).append(">");
		
		bw.write(sb.toString());
		bw.flush();
		
		br.close();
		bw.close();
	}
}
