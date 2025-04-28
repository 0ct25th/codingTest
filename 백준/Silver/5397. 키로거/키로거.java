import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			String L = br.readLine();

			ArrayDeque<Character> front = new ArrayDeque<>();
			ArrayDeque<Character> rear = new ArrayDeque<>();
			for (char ch : L.toCharArray()) {
				switch (ch) {
				case '<':
					if (front.isEmpty())
						continue;
					rear.offerFirst(front.pollLast());
					break;
				case '>':
					if (rear.isEmpty())
						continue;
					front.offerLast(rear.pollFirst());
					break;
				case '-':
					if (front.isEmpty())
						continue;
					front.pollLast();
					break;
				default:
					front.offerLast(ch);
					break;
				}
			}

			while (!front.isEmpty())
				sb.append(front.poll());

			while (!rear.isEmpty())
				sb.append(rear.poll());

			sb.append("\n");
		} // end of TestCase

		bw.write(sb.toString());
		bw.flush();

		br.close();
		bw.close();
	}
}
