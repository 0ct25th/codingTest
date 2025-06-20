import java.io.*;
import java.util.*;

public class Main {

	static int L, C;
	static char[] arr, order;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		arr = new char[C];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < C; i++)
			arr[i] = st.nextToken().charAt(0);

		Arrays.sort(arr);
		order = new char[L];
		recursion(0, 0);
		bw.write(sb.toString());
		bw.flush();

		br.close();
		bw.close();
	}

	static void recursion(int depth, int start) {
		if (depth == L) {
			if (isValid()) {
				for (int i = 0; i < L; i++)
					sb.append(order[i]);
				sb.append("\n");
			}
			return;
		}

		for (int i = start; i < C; i++) {
			order[depth] = arr[i];
			recursion(depth + 1, i + 1);
		}
	}

	static boolean isValid() {
		int vowel = 0, consonant = 0;

		for (int i = 0; i < L; i++) {
			if (order[i] == 'a' || order[i] == 'e' || order[i] == 'i' || order[i] == 'o' || order[i] == 'u')
				vowel++;
			else
				consonant++;

		}

		return vowel >= 1 && consonant >= 2;
	}
}
