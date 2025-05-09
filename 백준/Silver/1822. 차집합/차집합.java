import java.io.*;
import java.util.*;

public class Main {

	static int a, b;
	static int[] A, B;
	static List<Integer> lst;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		a = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());

		A = new int[a];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < a; i++)
			A[i] = Integer.parseInt(st.nextToken());

		B = new int[b];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < b; i++)
			B[i] = Integer.parseInt(st.nextToken());

		Arrays.sort(B);
		lst = new ArrayList<>();

		for (int i = 0; i < a; i++) {
			if (binarySearch(A[i]))
				lst.add(A[i]);
		}
		
		Collections.sort(lst);

		if (lst.size() > 0) {
			sb.append(lst.size()).append("\n");

			for (int i : lst)
				sb.append(i).append(" ");
		} else
			sb.append(0);
		
		System.out.println(sb);
	}

	static boolean binarySearch(int x) {
		int start = 0;
		int end = b - 1;

		while (start <= end) {
			int mid = (start + end) / 2;

			if (B[mid] == x)
				return false;
			else if (B[mid] < x)
				start = mid + 1;
			else
				end = mid - 1;
		}

		return true;
	}
}
