import java.io.*;
import java.util.*;

public class Main {

	static long A, B;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		A = Long.parseLong(st.nextToken());
		B = Long.parseLong(st.nextToken());

		System.out.println(bfs(A, B));
	}

	static long bfs(long start, long end) {
		Queue<Num> dq = new PriorityQueue<>((o1, o2) -> Long.compare(o1.n, o2.n));
		dq.offer(new Num(start, 0));

		while (!dq.isEmpty()) {
			Num cur = dq.poll();
			long n = cur.n;
			long cnt = cur.cnt;

			if (n == B)
				return cnt + 1;
			
			if(n > B)
				return -1;

			dq.offer(new Num(10 * n + 1, cnt + 1));
			dq.offer(new Num(2 * n, cnt + 1));
		}

		return -1;
	}

	static class Num {
		long n, cnt;

		Num(long n, long cnt) {
			this.n = n;
			this.cnt = cnt;
		}
	}
}
