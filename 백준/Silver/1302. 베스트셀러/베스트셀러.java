import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine().strip());
		Map<String, Integer> map = new HashMap<>();
		Queue<Book> pq = new PriorityQueue<>();
		
		for(int i = 0; i < N; i++) {
			String title = br.readLine().strip();
			
			if(map.containsKey(title))
				map.replace(title, map.get(title) + 1);
			else
				map.put(title, 1);
		}
		
		int answer_count = 0;
		for(String title: map.keySet()) {
			if(answer_count < map.get(title)) {
				pq = new PriorityQueue<>(); // 우선순위 큐 초기화
				pq.offer(new Book(title, map.get(title)));
				answer_count = map.get(title);
			} else if (answer_count == map.get(title)) {
				pq.offer(new Book(title, map.get(title)));
			}
		}
		
		System.out.println(pq.poll().title);
	}
	
	static class Book implements Comparable<Book>{
		String title;
		int count;
		
		Book(String title, int count) {
			this.title = title;
			this.count = count;
		}
		
		@Override
		public int compareTo(Book o) {
			int sort = this.title.compareTo(o.title);
			return sort;
		}
	}
}
