import java.io.*;
import java.util.*;

public class Main {

	static int N, K;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		LinkedList<Integer> list = new LinkedList<>();
		for (int i = 1; i <= N; i++)
			list.add(i);

		sb.append("<");
		int idx = K - 1;
		while (list.size() > 1) {
			idx = idx % list.size();
			sb.append(list.remove(idx)).append(", ");
			idx += K - 1;
		}
		sb.append(list.get(0)).append(">");

		bw.write(sb.toString());
		bw.flush();

		br.close();
		bw.close();
	}
}
