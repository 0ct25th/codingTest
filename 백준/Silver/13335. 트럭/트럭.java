import java.io.*;
import java.util.*;

public class Main {

	static int n, w, L, answer;
	static int[] trucks;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken()); // 트럭 개수
		w = Integer.parseInt(st.nextToken()); // 다리 길이
		L = Integer.parseInt(st.nextToken()); // 최대 하중

		trucks = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++)
			trucks[i] = Integer.parseInt(st.nextToken());

		//////////////// end of Input
		
		Queue<Truck> bridge = new ArrayDeque<>();
		for(int i = 0; i < w; i++)
			bridge.add(new Truck(-1, 0));
		int idx = 0; // 트럭 번호
		int bridgeWeight = 0;
		while(!bridge.isEmpty()) {
			answer++; // 시간 증가
			
			// 맨 앞 트럭
			Truck cur = bridge.poll(); // 다리 건넘
			bridgeWeight -= cur.w; // 다리 무게에서 감소
			
			// 다리 앞 트럭
			int nxtWeight = trucks[idx];
			
			// 다리 앞 트럭이 다리에 올라갈 수 있는 경우
			if(bridgeWeight + nxtWeight <= L) {
				bridge.add(new Truck(idx, nxtWeight)); // 다리에 추가
				bridgeWeight += nxtWeight; // 무게 증가
				idx++; // 다음 트럭 고려
			}
			
			// 다리 앞 트럭이 다리에 올라갈 수 없는 경우
			else 
				bridge.add(new Truck(-1, 0));
			
			// 모든 트럭 다 건너 간 경우
			if(idx == n)
				break; // 끝내기
		}
		
		// 마지막 트럭 다리 다 건너기
		answer += w;

		// 모든 트럭이 다리를 건너는 최단시간 출력
		System.out.println(answer);
	}

	static class Truck {
		int idx; // 트럭 순서
		int w; // 트럭 무게

		Truck(int idx, int w) {
			this.idx = idx;
			this.w = w;
		}
	}
}
