import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static Set<String> listen, see;
	static Queue<String> answer = new PriorityQueue<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		listen = new HashSet<>();
		for (int i = 0; i < N; i++)
			listen.add(br.readLine());

		see = new HashSet<>();
		for (int i = 0; i < M; i++)
			see.add(br.readLine());

		if (listen.size() < see.size())
			System.out.println(calc(listen, see));
		else
			System.out.println(calc(see, listen));

		while (!answer.isEmpty())
			System.out.println(answer.poll());
	}

	static int calc(Set<String> a, Set<String> b) {
		Iterator<String> iter = a.iterator();
		while (iter.hasNext()) {
			String name = iter.next();
			if (b.contains(name))
				answer.add(name);
		}
		return answer.size();
	}
}
