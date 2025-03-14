import java.io.*;
import java.util.*;

public class Main {

	static int n, d, c, result, time;
	static int[] minDist;
	static List<Node>[] nodeList;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken()); // 컴퓨터 개수(정점)
			d = Integer.parseInt(st.nextToken()); // 의존성 개수(간선)
			c = Integer.parseInt(st.nextToken()); // 해킹 당한 컴퓨터 번호

			nodeList = new ArrayList[n + 1];
			for (int i = 1; i <= n; i++)
				nodeList[i] = new ArrayList<>();

			for (int i = 0; i < d; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken()); // 컴퓨터 번호
				int b = Integer.parseInt(st.nextToken()); // 컴퓨터 번호
				int s = Integer.parseInt(st.nextToken()); // b가 감염되면 s초 후 a도 감염

				nodeList[b].add(new Node(a, s));
			}

			dijkstra(c);

			sb.append(result).append(" ").append(time).append("\n");
		}

		System.out.println(sb);
	}

	static void dijkstra(int start) {
		Queue<Node> pq = new PriorityQueue<>((o1, o2) -> (o1.sec - o2.sec));
		minDist = new int[n + 1];
		Arrays.fill(minDist, Integer.MAX_VALUE);
		
		minDist[start] = 0;
		pq.offer(new Node(start, minDist[start]));
		
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			
			for(Node nxt: nodeList[cur.num]) {
				if(minDist[nxt.num] <= minDist[cur.num] + nxt.sec)
					continue;
				
				minDist[nxt.num] = minDist[cur.num] + nxt.sec;
				pq.offer(new Node(nxt.num, minDist[nxt.num]));
			}
		}
		
		result = 0;
		time = 0;
		for(int i: minDist) {
			if(i != Integer.MAX_VALUE) {
				result++;
				time = Math.max(time, i);
			}
		}
	}

	static class Node {
		int num, sec;

		Node(int num, int sec) {
			this.num = num;
			this.sec = sec;
		}
	}
}
