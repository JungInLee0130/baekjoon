import java.awt.Point;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] map;
	static int[][] temp;
	static int[] count;
	static int cnt;
	static class Point{
		int x;
		int y;
		int count;
		public Point(int x, int y, int count) {
			super();
			this.x = x;
			this.y = y;
			this.count = count;
		}
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		count = new int[N * N];
		
		int max = 0;
		
		temp = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				temp[i][j] = map[i][j];
				max = Math.max(max, map[i][j]);
			}
		}
		
		int maxSector = 0;
		
		for (int i = 0; i <= max; i++) {
			// 높이 깎기 : temp
			if (i >= 1) {
				rain();
			}
			// 높이가 0인거 방문한거로
			// visited 초기화
			visited = new boolean[N][N];
			// 높이가 0인것들 true
			height0(visited);
			int sectorCount = 0;
			for (int k = 0; k < N; k++) {
				for (int j = 0; j < N; j++) {
					// 방문 x, 0이 아닌 지점 bfs
					if (temp[k][j] != 0 && !visited[k][j]) {
						bfs(k,j);
						sectorCount++;
					}
				}
			}
			maxSector = Math.max(maxSector, sectorCount);
		}
		
		
		bw.write(String.valueOf(maxSector));
		
		bw.flush();
		br.close();
		bw.close();
	}
	private static int[] dr = {-1,1,0,0};
	private static int[] dc = {0,0,1,-1};
	private static boolean[][] visited; 
	private static void bfs(int r, int c) {
		Queue<Point> queue = new LinkedList<Point>();
		queue.add(new Point(r,c, 0));
		visited[r][c] = true;
		
		while (!queue.isEmpty()) {
			Point cur = queue.poll();
			int curX = cur.x;
			int curY = cur.y;
			
			for (int i = 0; i < 4; i++) {
				int nr = cur.x + dr[i];
				int nc = cur.y + dc[i];
				
				// 범위 초과시
				if (!check(nr,nc)) continue;
				// true인경우
				if (visited[nr][nc]) continue;
				// 방문할수있는 지점인경우
				if (temp[nr][nc] != 0) {
					// 방문후
					visited[nr][nc] = true;
					// 큐에 대입
					queue.add(new Point(nr,nc, cur.count + 1));
				}
			}
		}
	}
	private static boolean check(int nr, int nc) {
		return 0 <= nr && nr < N && 0 <= nc && nc < N;
	}
	private static void height0(boolean[][] visited) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				// 높이가 0이면 visited
				if (temp[i][j] == 0) {
					visited[i][j] = true;
				}
			}
		}
	}
	private static void rain() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (temp[i][j] == 0) continue;
				// 0 이 아니면 -1
				temp[i][j] -= 1;
			}
		}
	}

}