import java.io.*;
import java.util.*;

public class Main {

	static char[] p;
	static int n;
	static Deque<Integer> dq;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			String str = br.readLine();
			p = str.toCharArray();

			n = Integer.parseInt(br.readLine());

			dq = new ArrayDeque<>();
			str = br.readLine();

			if (n > 0) {
				str = str.substring(1, str.length() - 1);
				if (!str.isEmpty()) {
					String[] nums = str.split(",");
					for (String num : nums) {
						dq.add(Integer.parseInt(num));
					}
				}
			}

			boolean isReversed = false;
			boolean flag = false;
			for (int i = 0; i < p.length; i++) {
				char ch = p[i];

				if (ch == 'R')
					isReversed = !isReversed;
				else {
					if (dq.isEmpty()) {
						flag = true;
						break;
					}

					if (isReversed)
						dq.removeLast();
					else
						dq.removeFirst();

				}
			}

			if (flag) {
				sb.append("error\n");
			} else {
				sb.append("[");
				if (!dq.isEmpty()) {
					if (isReversed) {
						Iterator<Integer> iter = dq.descendingIterator();

						sb.append(iter.next());
						while (iter.hasNext())
							sb.append(",").append(iter.next());
					} else {
						Iterator<Integer> iter = dq.iterator();

						sb.append(iter.next());
						while (iter.hasNext())
							sb.append(",").append(iter.next());
					}
				}
				sb.append("]\n");
			}
		}

		bw.write(sb.toString());
		bw.flush();

		br.close();
		bw.close();
	}
}
