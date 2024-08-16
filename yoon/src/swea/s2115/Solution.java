package swea.s2115;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
 
public class Solution {
 
    static int N, M, C, result;
    static int get1, get2;
    static int[][] map, isVisited;
    static List<Coord> one, two;
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
 
        int T = Integer.parseInt(br.readLine().trim());
        for (int t = 1; t <= T; t++) {
            // 첫 번째 줄에는 벌통들의 크기 N, 선택할 수 있는 벌통의 개수 M, 꿀을 채취할 수 있는 최대 양 C가 차례로 주어진다.
            st = new StringTokenizer(br.readLine().trim());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
 
            // 그 다음 줄부터 N*N 개의 벌통에서 채취할 수 있는 꿀의 양에 대한 정보가 주어진다.
            map = new int[N][N];
            for (int r = 0; r < N; r++) {
                st = new StringTokenizer(br.readLine().trim());
                for (int c = 0; c < N; c++) {
                    map[r][c] = Integer.parseInt(st.nextToken());
                }
            }
            ///////////////////////////////////// end of Input
 
            // 두 일꾼이 꿀을 채취하여 얻을 수 있는 최대 수익이다.
            result = Integer.MIN_VALUE;
 
            main();
 
            sb.append("#").append(t).append(" ").append(result).append("\n");
        } // end of TestCase
 
        bw.write(sb.toString());
        bw.flush();
 
        br.close();
        bw.close();
    } // end of main
 
    static void main() {
        // 1. 일꾼 벌통 고르기
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                isVisited = new int[N][N];
                choice1(r, c);
            }
        }
 
    }
 
    static void choice1(int sr, int sc) {
        // M개의 가로로 연속된 벌통을 고를 수 있는지 판단
        if (sc + M <= N) {
            // 1번 일꾼의 벌통 좌표 저장
            one = new ArrayList<>();
            for (int c = sc; c < sc + M; c++) {
                one.add(new Coord(sr, c)); // 벌통 좌표 저장
                isVisited[sr][c] = 1;
            }
 
            // 2번 일꾼 벌통 고르기
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    // 이미 일꾼1이 선점한 벌통이면 넘기기
                    if (isVisited[r][c] == 1)
                        continue;
 
                    // 2번 일꾼 벌통 고르기
                    choice2(r, c);
                }
            }
 
        }
    }
 
    static void choice2(int sr, int sc) {
        // M개의 가로로 연속된 벌통 고를 수 있는지 판단
        if (sc + M <= N) {
            // 2번 일꾼의 벌통 좌표 저장
            two = new ArrayList<>();
            for (int c = sc; c < sc + M; c++) {
                // 이미 일꾼1이 선점한 벌통이면 끝내기
                if(isVisited[sr][c] == 1)
                    return;
 
                two.add(new Coord(sr, c)); // 벌통 좌표 저장
            }
 
            // 용기에 담기
            get1 = Integer.MIN_VALUE;
            capacity(0, 0, 0, one, 1);
 
            get2 = Integer.MIN_VALUE;
            capacity(0, 0, 0, two, 2);
 
            // 수익의 합 최대 판단
            result = Integer.max(result, get1 + get2);
 
        }
    }
 
    static void capacity(int depth, int sum, int sqrt, List<Coord> tong, int flag) {
        // 기저조건: M개의 벌통의 수를 모두 고려한 경우
        if (depth == M) {
            switch (flag) {
            case 1:
                get1 = Integer.max(get1, sqrt);
                break;
            case 2:
                get2 = Integer.max(get2, sqrt);
                break;
            }
 
            return;
        }
 
        int r = tong.get(depth).r;
        int c = tong.get(depth).c;
 
        // 해당 벌통을 선택한 경우
        if (sum + map[r][c] <= C)
            capacity(depth + 1, sum + map[r][c], sqrt + map[r][c] * map[r][c], tong, flag);
 
        // 해당 벌통을 선택하지 않은 경우
        capacity(depth + 1, sum, sqrt, tong, flag);
    }
 
    static class Coord {
        int r;
        int c;
 
        public Coord(int r, int c) {
            super();
            this.r = r;
            this.c = c;
        }
    }
}