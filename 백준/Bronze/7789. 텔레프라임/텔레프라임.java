import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int tel = Integer.parseInt(st.nextToken());
        int newT = Integer.parseInt(st.nextToken() + tel);

        for (int i = 2; i < Math.sqrt(tel); i++) {
            if (tel % i == 0) {
                System.out.println("No");
                return;
            }
        }

        for (int i = 2; i < Math.sqrt(newT); i++) {
            if (newT % i == 0) {
                System.out.println("No");
                return;
            }
        }
        
        System.out.println("Yes");
    }
}