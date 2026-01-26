import java.io.*;

public class Main {

	static String str;
	static int robot, start, end;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		str = br.readLine();

		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (c == '.')
				continue;
			else if (c == '@')
				robot = i;
			else if (c == '#')
				start = i;
			else if (c == '!')
				end = i;
		}

		if (!((robot < start && start < end) || (end < start && start < robot))) {
			System.out.println(-1);
			return;
		}

		int result = Math.abs(robot - end) - 1;
		System.out.println(result);
	}
}
