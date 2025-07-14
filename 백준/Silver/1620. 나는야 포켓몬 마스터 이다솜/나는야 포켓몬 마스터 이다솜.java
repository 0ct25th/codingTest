import java.io.*;
import java.util.*;

public class Main {

	static int N, M;
	static Map<String, String> hashName, hashNum;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 포켓몬 개수
		M = Integer.parseInt(st.nextToken()); // 문제의 개수

		hashName = new HashMap<>();
		hashNum = new HashMap<>();
		for (int i = 1; i <= N; i++) {
			String name = br.readLine();

			hashName.put(name, i + "");
			hashNum.put(i + "", name);
		}

		for (int i = 0; i < M; i++) {
			String q = br.readLine();

			if (hashName.containsKey(q))
				System.out.println(hashName.get(q));
			else
				System.out.println(hashNum.get(q));
		}
	}

}
