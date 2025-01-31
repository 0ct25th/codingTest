import java.io.*;
import java.util.*;

public class Main {

	static int N, M;
	static Map<String, String> map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		// 저장된 사이트 주소의 수 N
		N = Integer.parseInt(st.nextToken());
		// 비밀번호를 찾으려는 사이트 주소의 수 M
		M = Integer.parseInt(st.nextToken());

		map = new HashMap<>();
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			String url = st.nextToken();
			String pwd = st.nextToken();

			map.put(url, pwd);
		}

		for (int m = 0; m < M; m++) {
			String url = br.readLine();

			System.out.println(map.get(url));
		}
	}
}
