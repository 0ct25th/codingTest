import java.io.*;

public class Main {

	static char c;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		c = br.readLine().charAt(0);
		
		String str = "ILOVEYONSEI";
		int result = 0;
		result = Math.abs(c - str.charAt(0));
		for(int i = 0; i < str.length() - 1; i++)
			result += Math.abs(str.charAt(i+1) - str.charAt(i));
		
		System.out.println(result);
		
		br.close();
	}
}
