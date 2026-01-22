import java.io.*;
import java.util.*;

public class Main {

	static int x, y;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		
		if(x > y) {
			System.out.println(x + y);
		} else {
			System.out.println(y - x);
		}
		
		br.close();
	}
}
