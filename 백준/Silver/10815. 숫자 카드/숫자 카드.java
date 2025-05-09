import java.io.*;
import java.util.*;

public class Main {

	static int N, M;
	static Map<Integer, Integer> map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());

		map = new HashMap<>();
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());

			if (map.containsKey(num))
				continue;

			map.put(num, 1);
		}

		M = Integer.parseInt(br.readLine());

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			int num = Integer.parseInt(st.nextToken());

			if (map.containsKey(num))
				sb.append("1 ");
			else
				sb.append("0 ");
		}

		System.out.println(sb);
	}
}
