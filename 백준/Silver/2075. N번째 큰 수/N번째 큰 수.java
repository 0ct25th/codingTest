import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine().strip());
		Queue<Integer> pq = new PriorityQueue<>((o1, o2) -> -(o1 - o2));
		
		for(int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine().strip());
			for(int c = 0; c < N; c++) {
				pq.offer(Integer.parseInt(st.nextToken()));
			}
		}
		
		for(int n = 0; n < N -1; n++) 
			pq.poll();
		
		System.out.print(pq.poll());
		
	}
}
