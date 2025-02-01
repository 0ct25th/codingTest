import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static HashMap<Integer, Integer> cards;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		// 첫째 줄에 상근이가 가지고 있는 숫자 카드의 개수 N(1 ≤ N ≤ 500,000)이 주어진다.
		N = Integer.parseInt(br.readLine());

		// 둘째 줄에는 숫자 카드에 적혀있는 정수가 주어진다.
		st = new StringTokenizer(br.readLine());
		cards = new HashMap<Integer, Integer>();
		for (int i = 0; i < N; i++) {
			cards.put(Integer.parseInt(st.nextToken()), 1);
		}

		// 셋째 줄에는 M(1 ≤ M ≤ 500,000)이 주어진다.
		M = Integer.parseInt(br.readLine());

		// 넷째 줄에는 상근이가 가지고 있는 숫자 카드인지 아닌지를 구해야 할 M개의 정수가 주어지며, 이 수는 공백으로 구분되어져 있다.
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			if (cards.containsKey(Integer.parseInt(st.nextToken())))
				sb.append(1).append(" ");
			else
				sb.append(0).append(" ");
		}

		// 결과 출력
		bw.write(sb.toString());
		bw.flush();

		// close
		br.close();
		bw.close();
	}
}
