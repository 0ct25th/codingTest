import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static Queue<Integer> pq;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o2, o1));
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++)
				pq.offer(Integer.parseInt(st.nextToken()));
		}

		while (N > 1) {
			pq.poll();
			N--;
		}

		System.out.println(pq.poll());
	}
}
