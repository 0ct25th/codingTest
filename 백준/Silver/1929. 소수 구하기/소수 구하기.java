import java.util.*;
import java.lang.*;
import java.io.*;

class Main {

    static boolean[] isNotPrime;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());


        isNotPrime = new boolean[N + 1];
        eratosthenes(2, N);

        for(int i = Math.max(M, 2); i <= N; i++) {
            if(isNotPrime[i])
                continue;
             
            System.out.println(i);
        }
    }

    static void eratosthenes(int start, int end) {
        isNotPrime[0] = isNotPrime[1] = true;

        for(int i = 2; i <= Math.sqrt(end); i++) {
            if(isNotPrime[i])
                continue;

            for(int j = i * i; j <= end; j += i) 
                isNotPrime[j] = true;
        }
    }
}