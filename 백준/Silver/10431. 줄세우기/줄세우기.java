import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int P = Integer.parseInt(br.readLine());
		while (P-- > 0) {
			st = new StringTokenizer(br.readLine());
			int T = Integer.parseInt(st.nextToken());

			int[] height = new int[20];
			for (int i = 0; i < 20; i++)
				height[i] = Integer.parseInt(st.nextToken());

			int result = 0;
			for (int i = 0; i < 20; i++) 
				for (int j = 0; j < i; j++) 
					if (height[j] > height[i])
						result++;

			sb.append(T).append(" ").append(result).append("\n");
		}

		System.out.println(sb);
	}
}
