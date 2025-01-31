import java.io.*;
import java.util.*;

public class Main {

	static int T, N, result;
	static Map<String, Integer> map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// 테스트 케이스
		T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			// 해빈이가 가진 의상의 수
			N = Integer.parseInt(br.readLine());
			map = new HashMap<>();

			// 옷 입력
			for (int n = 0; n < N; n++) {
				st = new StringTokenizer(br.readLine());
				String name = st.nextToken();
				String type = st.nextToken();

				map.put(type, map.getOrDefault(type, 0) + 1);
			}

			// 옷 조합
			Iterator<Integer> iter = map.values().iterator();
			result = 1;
			while (iter.hasNext())
				result *= iter.next().intValue() + 1;

			// 아무것도 입지 않은 경우 1가지 제외
			System.out.println(result - 1);
		} // end of TestCase
	}
}
