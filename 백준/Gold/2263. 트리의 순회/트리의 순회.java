import java.io.*;
import java.util.*;

public class Main {
	
	static int n;
	static int[] inOrder, postOrder, preOrder, inOrderIdx;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		inOrder = new int[n]; // 중위 순회
		postOrder = new int[n]; // 후위 순회
		preOrder = new int[n]; // 전위 순회
		inOrderIdx = new int[n + 1]; // 전위 순회 값의 위치를 저장할 배열
		
		// 중위 순회 입력
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			inOrder[i] = Integer.parseInt(st.nextToken());
			inOrderIdx[inOrder[i]] = i; 
		}
		
		// 후위 순회 입력
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			postOrder[i] = Integer.parseInt(st.nextToken());
		}
		
		// 분할 정복 수행
		dnc(0, n-1, 0, n-1);
		
		bw.write(sb.toString());
		bw.flush();
		
		br.close();
		bw.close();
		
	}
	
	static void dnc(int inStart, int inEnd, int postStart, int postEnd) {
		// 기저조건: 범위를 벗어나면 종료
		if(inStart > inEnd || postStart > postEnd)
			return;
		
		// postOrder의 가장 마지막 원소는 현재 트리의 루트
		int root = postOrder[postEnd];
		sb.append(root).append(" ");
		
		// inOrder의 루트 인덱스 가져옴
		int rootIdx = inOrderIdx[root];
		
		// 루트 기준으로 왼쪽 서브 트리에 있는 노드 개수 구함
		int leftSize = rootIdx - inStart;
		
		// 왼쪽 서브 트리 순회
		// inOrder: 시작 ~ 루트 이전
		// postOrder: 시작 ~ 시작 + 왼쪽 노드 개수 - 1
		dnc(inStart, rootIdx - 1, postStart, postStart + leftSize - 1);
		
		// 오른쪽 서브 트리 순회
		// inOrder: 루트 다음 ~ 끝
		// postOrder: 왼쪽 순회 끝난 다음 ~ 루트 이전
		dnc(rootIdx + 1, inEnd, postStart + leftSize, postEnd - 1);
	}
}
