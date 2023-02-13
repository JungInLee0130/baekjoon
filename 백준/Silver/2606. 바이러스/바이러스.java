import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int M;
	static int[][] map;
	static int[] distance;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int s = Integer.parseInt(st.nextToken()) - 1;
			int e = Integer.parseInt(st.nextToken()) - 1;
			map[s][e] = 1;
			map[e][s] = 1;
		}
		distance = new int[N];
		count = 0;
		distance[0] = 2; // 방문
		min = Integer.MAX_VALUE / 1000;
		dfs(0, 0);
		//bfs();
		result();
		System.out.println(count - 1); // 자기자신 빼야함.
		
		
		bw.flush();
		br.close();
		bw.close();
	}
	private static int min;
	private static void dfs(int start, int cnt) {
		for (int e = 0; e < N; e++) {
			if (map[start][e] == 1 && distance[e] != 2) { // 방문한게 아니고, 1이면 go
				distance[e] = 2;
				dfs(e, cnt + 1);
			}
		}
		min = Math.min(min, cnt);
	}
	private static void result() {
		for (int i = 0; i < N; i++) {
			if (distance[i] == 2) {
				count++;
			}
		}
	}
	static int count;
	private static void bfs() {
		Queue<Integer> que = new LinkedList<Integer>();
		que.add(0);
		distance[0] = 2; // 방문표시
		
		while (!que.isEmpty()) {
			int s = que.poll();
			for (int e = 0; e < N; e++) {
				if (distance[e] != 0) continue; // 방문
				if (map[s][e] == 1) {
					que.add(e);
					distance[e] = 2; // 방문표시
				}
			}
		}
	}
	
}