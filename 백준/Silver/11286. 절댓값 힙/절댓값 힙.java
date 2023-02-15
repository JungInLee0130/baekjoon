import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;

public class Main {
	static int N;
	static class Point implements Comparable<Point>{
		int x;

		public Point(int x) {
			super();
			this.x = x;
		}

		@Override
		public int compareTo(Point p) {
			if (Math.abs(this.x) == Math.abs(p.x)) { // 근데 절댓값이 같으면
				return this.x - p.x; // 오름차순
			}
			return Math.abs(this.x) - Math.abs(p.x); // 절댓값 오름차순
		}
		
		
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		PriorityQueue<Point> priorityQueue = new PriorityQueue<>();
		N = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < N; i++) {
			int x = Integer.parseInt(br.readLine());
			
			if (x == 0) {
				// 가장 작은값 출력 및 poll
				if (priorityQueue.isEmpty()) {
					bw.write(String.valueOf(0) + "\n");
				}
				else {
					bw.write(String.valueOf(priorityQueue.poll().x) + "\n");
				}
			}
			else {
				// x가 아니면 추가
				priorityQueue.add(new Point(x));
			}
		}
		
		
		bw.flush();
		br.close();
		bw.close();
	}
}