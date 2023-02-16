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
		// 10의 9승만큼 돌리기 : 겉의 개수만큼 움직이면 같으므로 mod 해줌,
		// 겹을 바깥으로 내보내야 연산횟수가 줄어듬.
		// 한번에 돌리면 R번돌려야되서 시간 초과남.
		// 겉
		// 개수만큼 도니 visited 필요없을듯
		int row = N;
		int column = M;
		visited = new boolean[N][M];
		for (int j = 0; j < K; j++) {
			rotate(j, 2 * (row + column - 2));	
			row -= 2;
			column -= 2;
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
	private static void rotate(int start, int count) {
		// 카운트
		int cir = R % count;
		int r = start;
		int c = start;
		for (int i = 0; i < cir; i++)	 {
			// 회전 : 겉 count 번 -> 다음 겉 count 번
			int temp = map[r][c];
			int d = 0;
			while (d < 4) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				// 범위에 안속하거나 : 
				// 이미 방문했으면 : 위치 바꾸지않고 방향 바꾸기
				if (!check(nr,nc, start)) {
					d += 1;
					continue;
				}
				
				map[r][c] = map[nr][nc];
				r = nr;
				c = nc;
			}
			map[start + 1][start] = temp; // 마지막에 저장해놨던값 대입.
		}
	}
	private static void swap(int r, int c, int nr, int nc) {
		int temp = map[r][c];
		map[r][c] = map[nr][nc];
		map[nr][nc] = temp;
	}
	private static boolean check(int nr, int nc, int start) {
		return start <= nr && nr < N - start && start <= nc && nc < M - start;
	}
}