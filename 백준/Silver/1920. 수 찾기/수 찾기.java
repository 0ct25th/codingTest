import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static Map<Integer, Integer> A;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		// 첫째 줄에 자연수 N(1 ≤ N ≤ 100,000)이 주어진다.
		N = Integer.parseInt(br.readLine());

		// 다음 줄에는 N개의 정수 A[1], A[2], …, A[N]이 주어진다.
		A = new HashMap<Integer, Integer>();
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			A.put(Integer.parseInt(st.nextToken()), 1);
		}

		// 다음 줄에는 M(1 ≤ M ≤ 100,000)이 주어진다.
		M = Integer.parseInt(br.readLine());

		// 음 줄에는 M개의 수들이 주어지는데, 이 수들이 A안에 존재하는지 알아내면 된다.
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			if (A.containsKey(Integer.parseInt(st.nextToken())))
				sb.append(1).append("\n");
			else
				sb.append(0).append("\n");

		}

		// 결과 출력
		bw.write(sb.toString());
		bw.flush();

		// close
		br.close();
		bw.close();
	}
}
