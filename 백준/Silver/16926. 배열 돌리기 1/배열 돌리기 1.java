import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int N, M, R;
	static int[][] map;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		// 지도 완성
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine()); 
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken()); 
			}
		}
		
		// R번 돌리기
		int K = Math.min(N, M) / 2;
		for (int i = 0; i < R; i++) {
			visited = new boolean[N][M];
			for (int j = 0; j < K; j++) {
				rotate(j,j);	
			}
		}
		
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				bw.write(String.valueOf(map[i][j]) + " ");
			}
			bw.write("\n");
		}
		
		
		
		bw.flush();
		br.close();
		bw.close();
	}
	// direction, dr, dc 4방
	// N, M
	// 범위에 속하지않거나 방문한것이면 방향 바꿈.
	// 4방향 : (direction + 1) % 4
	private static int[] dr = {0,1,0,-1};
	private static int[] dc = {1,0,-1,0}; // 이동방향 : 오, 아, 왼, 위
	private static boolean[][] visited;
	private static void rotate(int r, int c) {
		int d = 0;
		visited[r][c] = true;
		while (d < 4) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			// 범위에 안속하거나 : 
			// 이미 방문했으면 : 위치 바꾸지않고 방향 바꾸기
			if (!check(nr,nc) || visited[nr][nc]) {
				d += 1;
				continue;
			}
			
			visited[nr][nc] = true;
			// 전꺼랑 swap
			swap(r,c,nr,nc);
			r = r + dr[d];
			c = c + dc[d];	
		}
	}
	private static void swap(int r, int c, int nr, int nc) {
		int temp = map[r][c];
		map[r][c] = map[nr][nc];
		map[nr][nc] = temp;
	}
	private static boolean check(int nr, int nc) {
		return 0 <= nr && nr < N && 0 <= nc && nc < M;
	}
}