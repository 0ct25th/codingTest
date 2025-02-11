import java.io.*;
import java.util.*;

public class Main {

	static Queue<String> pq;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		String str = br.readLine();

		pq = new PriorityQueue<>((o1, o2) -> o1.compareTo(o2));
		for (int i = 0; i < str.length(); i++) {
			pq.offer(str.substring(i));
		}

		while (!pq.isEmpty())
			sb.append(pq.poll()).append("\n");

		bw.write(sb.toString());
		bw.flush();

		br.close();
		bw.close();
	}
}
