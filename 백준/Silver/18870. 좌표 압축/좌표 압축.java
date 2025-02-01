import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static int[] arr;
	static Set<Integer> set;
	static Map<Integer, Integer> map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		set = new TreeSet<>();

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());

			arr[i] = num;
			set.add(num);
		}

		int idx = 0;
		map = new HashMap<>();
		Iterator<Integer> iter = set.iterator();
		while (iter.hasNext()) {
			map.put(iter.next().intValue(), idx);
			idx++;
		}

		for (int i : arr)
			sb.append(map.get(i)).append(" ");

		System.out.println(sb);
	}
}
