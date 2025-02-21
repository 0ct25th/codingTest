import java.io.*;
import java.util.*;

public class Main {

	static int N, C;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		Map<Integer, Integer> hash = new LinkedHashMap<>();
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());
			
			if(hash.containsKey(num))
				hash.replace(num, hash.get(num) + 1);
			else
				hash.put(num, 1);
		}
		
		List<Integer> list = new ArrayList<>(hash.keySet());
		Collections.sort(list, (o1, o2) -> -(hash.get(o1) - hash.get(o2)));
		
		for(int num: list) {
			for(int i = 0; i < hash.get(num); i++)
				System.out.print(num + " ");
		}
	}
}
