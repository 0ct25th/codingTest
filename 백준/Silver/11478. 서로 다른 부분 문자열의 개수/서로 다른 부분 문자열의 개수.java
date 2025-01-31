import java.io.*;
import java.util.*;

public class Main {
	
	static Set<String> set;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String str = br.readLine();
		int strLength = str.length();
		set = new HashSet<>();
		
		for(int i = 0; i < strLength; i++) {
			for(int j = i + 1; j <= strLength; j++)
				set.add(str.substring(i, j));
		}
		
		System.out.println(set.size());
	}
}
