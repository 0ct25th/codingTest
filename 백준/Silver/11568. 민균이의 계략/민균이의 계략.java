import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static int[] cards;
	static List<Integer> lis;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		st = new StringTokenizer(br.readLine());
		cards = new int[N];
		for (int i = 0; i < N; i++)
			cards[i] = Integer.parseInt(st.nextToken());

		/////////// end of Input

		binarySearch();
	}

	static void binarySearch() {
		lis = new ArrayList<>();
		lis.add(cards[0]);

		for (int i = 1; i < N; i++) {
			int key = cards[i];
			
			if(lis.get(lis.size() - 1) < key)
				lis.add(key);
			else {
				int start = 0;
				int end = lis.size() - 1;
				
				while(start < end) {
					int mid = (start + end) / 2;
					
					if(lis.get(mid) < key)
						start = mid + 1;
					else
						end = mid;
				}
				
				lis.set(end, key);
			}
		}

		System.out.println(lis.size());
	}
}
