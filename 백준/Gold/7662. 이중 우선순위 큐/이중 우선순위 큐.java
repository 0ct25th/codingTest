import java.io.*;
import java.util.*;

public class Main {

	static int n;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			n = Integer.parseInt(br.readLine());

			TreeMap<Integer, Integer> map = new TreeMap<>();
			for (int i = 0; i < n; i++) {
				String str = br.readLine();
				char op = str.charAt(0);
				int num = Integer.parseInt(str.substring(2));

				// 트리 맵 삽입
				if (op == 'I')
					map.put(num, map.getOrDefault(num, 0) + 1);

				// 트리 맵 삭제
				else {
					if (map.isEmpty())
						continue;

					// 최대값 삭제
					int cur = 0;
					if (num == 1)
						cur = map.lastKey();
					else
						cur = map.firstKey();

					if (map.put(cur, map.get(cur) - 1) == 1)
						map.remove(cur);
				}
			}

			if (map.isEmpty())
				sb.append("EMPTY\n");
			else {
				sb.append(map.lastKey()).append(" ").append(map.firstKey()).append("\n");
			}
		}

		System.out.println(sb);
	}

}
