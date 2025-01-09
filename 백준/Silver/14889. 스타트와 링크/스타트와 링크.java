import java.io.*;
import java.util.*;

public class Main {
	
	static int N, M, startScore, linkScore, result;
	static int[][] map;
	static boolean[] isStartTeam;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine().strip());
		M = (int)(N / 2);
		map = new int[N][N];
		for(int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine().strip());
			for(int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		isStartTeam = new boolean[N];
		result = Integer.MAX_VALUE;
		combination(0, 0);
		
		System.out.println(result);
		
		br.close();
	}
	
	static void combination(int depth, int start) {
		// 기저조건: 모든 사람을 2팀으로 나눈 경우
		if(depth == M) {
			// 점수 계산
			calc();
			
			return;
		}
		
		for(int i = start; i < N; i++) {
			isStartTeam[i] = true;	// start 팀으로 선택
			combination(depth + 1, i + 1);
			isStartTeam[i] = false;	// 선택 원복
		}
	}
	
	static void calc() {
		startScore = 0;
		linkScore = 0;
		
		// 각 팀으로 나누기
		for(int i = 0; i < N; i++) {
			// start 팀
			if(isStartTeam[i]) {
				for(int j = 0; j < N; j++)
					if(isStartTeam[j])
						startScore += map[i][j];
			}
			
			// link 팀
			else {
				for(int j = 0; j < N; j++)
					if(!isStartTeam[j])
						linkScore += map[i][j];
			}
		}
		
		// 최소값 계산
		result = Math.min(result, Math.abs(startScore - linkScore));
	}

}
