import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int R, C;
	// 복사본
	static int[][] temp;
	static int[][] cheese;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		cheese = new int[R][C];
		temp = new int[R][C];
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				cheese[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 임시 배열 채우기
		scan(temp, cheese);
		
		int count = 0;
		while (!allComplete()) { // 다 녹였으면 break
			scan(cheese, temp);
			input2(0,0); // 겉에 배열 2로 채우기
			searchMelt(); // 녹이는 부분 3으로 바꾸기(cheese)
			scan(temp, cheese); // cheese temp로 옮기기
			melt(); // 녹이기
			count++;
		}
		int cheeseCount = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (cheese[i][j] == 3) {
					cheeseCount++;			
				}
			}
		}
		
		bw.write(String.valueOf(count) + "\n");
		bw.write(String.valueOf(cheeseCount));
		
		/*for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				bw.write(String.valueOf(temp[i][j]) + " ");
			}
			bw.write("\n");
		}*/
		
		bw.flush();
		br.close();
		bw.close();
	}
	
	private static void scan(int[][] a, int[][] b) {
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				a[i][j] = b[i][j];
			}
		}
	}

	private static boolean allComplete() {
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (temp[i][j] == 1) return false;
			}
		}
		return true;
	}

	private static void melt() {
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (temp[i][j] == 3) {
					temp[i][j] = 0;
				}
			}
		}
	}

	private static void searchMelt() {
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (cheese[r][c] != 1) continue;
				
				for (int d = 0; d < 4; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					
					if (!check(nr,nc)) continue;
					// 2가 한개라도 있으면
					if (cheese[nr][nc] == 2) cheese[r][c] = 3;
				}
			}
		}
		
	}

	private static int[] dr = {-1,1,0,0};
	private static int[] dc = {0,0,1,-1};
	private static void input2(int r, int c) {
		Queue<int[]> queue = new LinkedList<int[]>();
		queue.add(new int[] {r,c});
		cheese[r][c] = 2;
		boolean[][] visited = new boolean[R][C];
		
		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			int tempR = cur[0];
			int tempC = cur[1];
			
			if (visited[tempR][tempC]) continue;
			visited[tempR][tempC] = true;
			
			for (int i = 0; i < 4; i++) {
				int nr = tempR + dr[i];
				int nc = tempC + dc[i];
				
				// 범위안에 속하지않거나
				if (!check(nr,nc)) continue;
				// 1이면 지나지않음
				if(cheese[nr][nc] == 1) continue;
				// 또는 방문한거면 continue;
				if (visited[nr][nc]) continue;
				// 겉에 0은 다 2로 바꿈.
				cheese[nr][nc] = 2;
				queue.add(new int[] {nr,nc});
			}
		}
	}
	private static boolean check(int nr, int nc) {
		return 0 <= nr && nr < R && 0 <= nc && nc < C;
	}

}