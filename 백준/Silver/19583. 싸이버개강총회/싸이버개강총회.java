import java.io.*;
import java.util.*;

public class Main {

	static String S, E, Q;
	static int sh, sm, eh, em, qh, qm, result;
	static Set<String> set;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// 첫번째 줄에는 개강총회를 시작한 시간 S, 개강총회를 끝낸 시간 E, 개강총회 스트리밍을 끝낸 시간 Q가 주어진다.
		st = new StringTokenizer(br.readLine());
		S = st.nextToken();
		E = st.nextToken();
		Q = st.nextToken();

		// 채팅 기록들이 시간순으로 주어지는데, (시간) (학회원 닉네임)의 형태로 주어진다.
		String input;
		set = new HashSet<>();
		while ((input = br.readLine()) != null) {
			String[] str = input.split(" ");

			// 종료 조건
			if (str.length < 2)
				break;

			String chatTime = str[0]; // 채팅 기록 시간
			String nickName = str[1]; // 학회원 닉네임

			// 입장 확인
			if (chatTime.compareTo(S) <= 0) {
				set.add(nickName); // 개강총회 시작 전에 입장 확인
			}
			// 퇴장 확인
			else if (chatTime.compareTo(E) >= 0 && chatTime.compareTo(Q) <= 0) {
				if (set.contains(nickName)) {
					result++;
					set.remove(nickName); // 퇴장 처리
				}
			}
		}
		System.out.println(result);
	}

}
