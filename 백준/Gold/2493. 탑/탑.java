import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());

		Stack<Top> tops = new Stack<>();
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			int height = Integer.parseInt(st.nextToken());
			tops.add(new Top(i, height));
		}

		int[] orders = new int[N + 1];
		Queue<Top> waits = new PriorityQueue<>();
		while (tops.size() > 1) {
			Top cur = tops.pop(); // 현재 고려할 탑
			Top nxt = tops.peek(); // 다음 순번 탑

			// 다음 순번 탑에 수신 받을 수 있는지 확인
			while (!waits.isEmpty()) {
				Top wait = waits.peek(); // 수신 받지 못한 탑

				if (nxt.height <= wait.height)
					break;

				wait = waits.poll();
				orders[wait.idx] = nxt.idx;
			}

			// 현재 탑이 다음 탑에 수신할 수 있는 경우
			if (nxt.height > cur.height)
				orders[cur.idx] = nxt.idx;
			else
				waits.offer(cur);
		}

		for (int i = 1; i <= N; i++)
			sb.append(orders[i]).append(" ");

		System.out.println(sb);
	}

	static class Top implements Comparable<Top> {
		int idx; // 순서
		int height; // 높이

		Top(int idx, int height) {
			this.idx = idx;
			this.height = height;
		}

		@Override
		public int compareTo(Top o) {
			return (this.height - o.height);
		}
	}
}
