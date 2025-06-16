import java.io.*;
import java.util.*;

public class Main {

	static int N, result;
	static List<Integer>[] list;
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		sb.append((int) Math.pow(2, N) - 1).append("\n");
		hanoi(N, 1, 2, 3);
		
		bw.write(sb.toString());
		bw.flush();
		
		br.close();
		bw.close();
	}

	static void hanoi(int n, int start, int mid, int to) {
		if (n == 1) {
			sb.append(start).append(" ").append(to).append("\n");
			return;
		}

		// 1에서 3으로 이동하는 경우
		// 1. n-1개를 1에서 2로 이동
		hanoi(n - 1, start, to, mid);

		// 2. 1개를 1에서 3으로 이동
		sb.append(start).append(" ").append(to).append("\n");

		// 3.n-1개를 2에서 3으로 이동
		hanoi(n - 1, mid, start, to);
	}
}