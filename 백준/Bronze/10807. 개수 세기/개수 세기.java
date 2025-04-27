import java.io.*;
import java.util.*;

public class Main {

	static int N, v;
	static Map<Integer, Integer> arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		st = new StringTokenizer(br.readLine());
		arr = new HashMap<>();
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());

			arr.put(num, arr.getOrDefault(num, 0) + 1);
		}

		v = Integer.parseInt(br.readLine());
		System.out.println(arr.getOrDefault(v, 0));
	}
}
