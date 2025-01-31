import java.io.*;
import java.util.*;

public class Main {

	static int N, M;
	static Map<String, List<String>> quizZero;
	static Map<String, String> quizOne;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		// 걸그룹 입력
		quizZero = new HashMap<>();
		quizOne = new HashMap<>();
		for (int n = 0; n < N; n++) {
			String groupName = br.readLine();
			int groupCount = Integer.parseInt(br.readLine());

			for (int c = 0; c < groupCount; c++) {
				String name = br.readLine();

				if (!quizZero.containsKey(groupName))
					quizZero.put(groupName, new ArrayList<>());
				
				quizZero.get(groupName).add(name);
				quizOne.put(name, groupName);
			}
		}

		// 퀴즈 맞추기
		for (int m = 0; m < M; m++) {
			String input = br.readLine();
			int quizType = Integer.parseInt(br.readLine());

			switch (quizType) {
			case 0:
				Collections.sort(quizZero.get(input));
				for (String g : quizZero.get(input))
					System.out.println(g);

				break;

			case 1:
				System.out.println(quizOne.get(input));
				break;
			}
		}
	}

}
