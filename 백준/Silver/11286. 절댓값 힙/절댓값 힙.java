
import java.io.*;
import java.util.*;

public class Main {

	static Queue<Num> pq = new PriorityQueue<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine().strip());
		for(int t = 0; t < T; t++) {
			int x = Integer.parseInt(br.readLine());
			
			if(x == 0) {
				if(!pq.isEmpty())
					sb.append(pq.poll().origin).append("\n");
				else
					sb.append("0\n");
			}
			else
				pq.offer(new Num(x, Math.abs(x)));
			
		} // end of TestCase
		
		bw.write(sb.toString());
		bw.flush();
		
		br.close();
		bw.close();
	} // end of main
	
	static class Num implements Comparable<Num> {
		int origin;
		int abs;
		
		Num(int origin, int abs) {
			this.origin = origin;
			this.abs = abs;
		}
		
		@Override
		public int compareTo(Num o) {
			if (this.abs == o.abs)
				return (this.origin - o.origin);
			
			return (this.abs - o.abs);
		}
	}
}
