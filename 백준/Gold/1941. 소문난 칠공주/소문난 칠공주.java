import java.io.*;
import java.util.*;

public class Main {

	static int result;
	static char[][] map;
	static int[] isSelected = new int[7];
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		map = new char[5][5];
		for (int r = 0; r < 5; r++) {
			String str = br.readLine();
			for (int c = 0; c < 5; c++)
				map[r][c] = str.charAt(c);
		}

		combination(0, 0);
		System.out.println(result);
	}

	static void combination(int depth, int start) {
		if (depth == 7) {
			if (countS() >= 4 && isConnected())
				result++;

			return;
		}

		for (int i = start; i < 25; i++) {
			isSelected[depth] = i;
			combination(depth + 1, i + 1);
		}
	}

	static int countS() {
		int count = 0;

		for (int pos : isSelected) {
			int r = pos / 5;
			int c = pos % 5;
			if (map[r][c] == 'S')
				count++;
		}

		return count;
	}

	static boolean isConnected() {
		Queue<Integer> dq = new ArrayDeque<>();
		boolean[] isVisited = new boolean[7];

		dq.offer(0);
		isVisited[0] = true;
		int count = 1;

		while (!dq.isEmpty()) {
			int idx = dq.poll();
			int r1 = isSelected[idx] / 5;
			int c1 = isSelected[idx] % 5;

			for (int i = 0; i < 7; i++) {
				if (isVisited[i])
					continue;

				int r2 = isSelected[i] / 5;
				int c2 = isSelected[i] % 5;

				if (Math.abs(r1 - r2) + Math.abs(c1 - c2) == 1) {
					isVisited[i] = true;
					dq.offer(i);
					count++;
				}
			}
		}

		return count == 7;
	}
}
