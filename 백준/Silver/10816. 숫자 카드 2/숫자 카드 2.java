import java.io.*;
import java.util.*;

public class Main {

	static int N, M;
	static int[] input;
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
			map.put(num, map.getOrDefault(num, 0) + 1);
		}

		M = Integer.parseInt(br.readLine());
		input = new int[M];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++)
			input[i] = Integer.parseInt(st.nextToken());

		for (int i : input)
			sb.append(search(i)).append(" ");

		System.out.println(sb);
	}

	static int search(int key) {
		if (map.containsKey(key))
			return map.get(key);
		else
			return 0;
	}

}
