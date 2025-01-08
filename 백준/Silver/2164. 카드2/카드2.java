import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		Scanner scan = new Scanner(System.in);
		
		int N = Integer.parseInt(scan.next());
		Queue<Integer> dq = new ArrayDeque<>();
		
		// 1번 카드가 제일 위에, N번 카드가 제일 아래인 상태로 순서대로 카드가 놓여 있다.
		for(int i = 1; i <= N; i++) 
			dq.offer(i);
		
		
		while(dq.size() != 1) {
			dq.poll();
			dq.offer(dq.poll());
		}
		
		System.out.println(dq.poll());
		
		scan.close();
	}
}
