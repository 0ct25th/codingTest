import java.io.*;
import java.util.*;

public class Main {

	static int N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			Queue<Document> q = new LinkedList<>();
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 문서의 개수
			M = Integer.parseInt(st.nextToken()); // 찾는 문서의 초기 위치

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++)
				q.offer(new Document(i, Integer.parseInt(st.nextToken())));

			int result = 0;
			while (!q.isEmpty()) {
				Document cur = q.poll();
				boolean isMax = true;

				// 현재 문서보다 중요도가 높은 문서가 있는지 확인
				for (Document doc : q) {
					if (cur.importance < doc.importance) {
						isMax = false;
						break;
					}
				}

				if (isMax) {
					result++; // 현재 문서가 가장 중요도가 높음
					if (cur.idx == M)
						break; // 찾는 문서가 출력

				} else
					q.offer(cur);// 더 중요한 문서가 있음

			}

			sb.append(result).append("\n");
		}

		bw.write(sb.toString());
		bw.flush();

		br.close();
		bw.close();
	}

	static class Document {
		int idx, importance;

		Document(int idx, int importance) {
			this.idx = idx;
			this.importance = importance;
		}
	}
}
