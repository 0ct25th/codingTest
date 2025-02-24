import java.io.*;
import java.util.*;

public class Main {
	
	static int N, M, result;
	static boolean[] isVisited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int t = 0; t < T; t++) {
			result = 0;
			
			// 첫 번째 줄에는 국가의 수 N(2 ≤ N ≤ 1 000)과 비행기의 종류 M(1 ≤ M ≤ 10 000) 가 주어진다.
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 정점 수
			M = Integer.parseInt(st.nextToken()); // 간선 수
			
			isVisited = new boolean[N + 1];
			// M개의 줄에 a와 b 쌍들이 입력
			for(int i = 0; i < M; i++) {
				// a와 b를 왕복하는 비행기가 있다는 것을 의미
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
			}
			
			System.out.println(N - 1);
		} // end of TestCase
	}
}
