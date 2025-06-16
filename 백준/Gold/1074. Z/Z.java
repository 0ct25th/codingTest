import java.io.*;
import java.util.*;

public class Main {

	static int N, r, c, cnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());

		z((1 << N), 0, 0);
	}

	static void z(int div, int row, int col) {
		if (div == 2) {
			for (int i = row; i < row + div; i++) {
				for (int j = col; j < col + div; j++) {
					if (i == r && j == c) {
						System.out.println(cnt);
						return;
					}
					cnt++;
				}
			}
			return;
		}

		int half = div / 2;

		// 제 2사분면
		if (r < row + half && c < col + half)
			z(half, row, col);
		else
			cnt += half * half;

		// 제 1사분면
		if (r < row + half && c >= col + half)
			z(half, row, col + half);
		else
			cnt += half * half;

		// 제 3사분면
		if (r >= row + half && c < col + half)
			z(half, row + half, col);
		else
			cnt += half * half;

		// 제 4사분면
		if (r >= row + half && c >= col + half)
			z(half, row + half, col + half);
		else
			cnt += half * half;
	}
}
