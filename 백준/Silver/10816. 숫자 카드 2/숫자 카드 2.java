import java.io.*;
import java.util.*;

public class Main {

	static int N, M;
	static Map<Integer, Integer> hash;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());

		hash = new HashMap<>();
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int card = Integer.parseInt(st.nextToken());

			hash.put(card, hash.getOrDefault(card, 0) + 1);
		}

		M = Integer.parseInt(br.readLine());

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			int x = Integer.parseInt(st.nextToken());

			sb.append(hash.containsKey(x) ? hash.get(x) : 0).append(" ");
		}

		System.out.println(sb);
	}
}
