import java.io.*;
import java.util.*;

public class Main {

	static String str;
	static int N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		str = br.readLine();
		ArrayDeque<Character> front = new ArrayDeque<>();
		for (char ch : str.toCharArray())
			front.offer(ch);

		N = Integer.parseInt(br.readLine());
		ArrayDeque<Character> rear = new ArrayDeque<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			char op = st.nextToken().charAt(0);

			switch (op) {
			case 'L': // 왼쪽 한 칸
				if (front.isEmpty())
					continue;
				rear.addFirst(front.pollLast());
				break;
			case 'D': // 오른쪽 한 칸
				if (rear.isEmpty())
					continue;
				front.add(rear.pollFirst());
				break;
			case 'B': // 왼쪽 문자 삭제
				if (front.isEmpty())
					continue;
				front.pollLast();
				break;
			case 'P': // 왼쪽 문자 추가
				front.offer(st.nextToken().charAt(0));
				break;
			}
		}

		while (!front.isEmpty())
			sb.append(front.poll());

		while (!rear.isEmpty())
			sb.append(rear.poll());

		bw.write(sb.toString());
		bw.flush();

		br.close();
		bw.close();
	}
}
