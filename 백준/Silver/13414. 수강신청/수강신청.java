import java.util.*;
import java.io.*;

public class Main {

	static int K, L;
	static Set<String> set;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		K = Integer.parseInt(st.nextToken()); // 과목의 수강 가능 인원
		L = Integer.parseInt(st.nextToken()); // 학생들이 버튼을 클릭한 순서를 기록한 대기목록의 길이
		
		set = new LinkedHashSet<>();
		for(int i = 0; i < L; i++) {
			String gradeNum = br.readLine();
			
			// 이미 대기 목록에 있는 경우
			if(set.contains(gradeNum))
				set.remove(gradeNum);
			
			set.add(gradeNum);
		}
		
		// 입력받은 데이터에 대해, 수강신청 관리 시스템의 규칙을 적용한 후 수강신청에 성공한 인원의 학번을 한 줄에 1개씩 출력
		int idx = 0;
		for(String num: set) {
			if(idx == K)
				break;
			
			System.out.println(num);
			idx++;
		}
	}
}
