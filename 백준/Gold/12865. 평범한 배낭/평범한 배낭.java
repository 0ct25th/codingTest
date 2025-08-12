import java.io.*;
import java.util.*;

public class Main {

	static int N, K, dp[][];
	static List<Item> items;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 물품의 수
		K = Integer.parseInt(st.nextToken()); // 버틸 수 있는 무게

		items = new ArrayList<>();
		items.add(new Item(0, 0));
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int w = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());

			items.add(new Item(w, v));
		}

		dp = new int[N + 1][K + 1];
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= K; j++) {
				if (j < items.get(i).w)
					dp[i][j] = dp[i - 1][j];
				else
					dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - items.get(i).w] + items.get(i).v);
			}
		}

		System.out.println(dp[N][K]);
	}

	static class Item {
		int w, v;

		Item(int w, int v) {
			this.w = w;
			this.v = v;
		}
	}
}
