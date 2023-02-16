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
	static int N, M;
	static int[][] board;
	static int[][] temp;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		// 배열 초기화
		temp = new int[N][M];
		board = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// scan : 임시 배열 만들기 -> 임시배열에서 삭제
		scan(temp, board);
		
		int cheeseCount = 0;
		while (!allComplete()) {
			// temp : board로 복사후 치즈가 모두 없어질때까지 반복.
			scan(board, temp);
			// temp :바깥쪽 bfs로 2로 채우기
			bfs2(0, 0, temp);
			
			// temp : 1에 해당하는거 4방중 2개 이상이 2이면 1 -> 3으로 바꿈. 
			change3(temp);
			
			// temp : 복사하면서 3 -> 0으로 (다 녹임 : count++)
			change0(temp);
			
			cheeseCount++;
		}

		bw.write(String.valueOf(cheeseCount));
		
		bw.flush();
		br.close();
		bw.close();
	}
	
	private static boolean allComplete() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (temp[i][j] == 0 || temp[i][j] == 2) continue;
				return false;
			}
		}
		return true;
	}

	private static void change0(int[][] temp2) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (temp[i][j] == 3) {
					temp[i][j] = 0;
				}
			}
		}
	}

	private static void change3(int[][] board) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (board[i][j] == 1) {
					// 4방검사
					int count = 0;
					for (int d = 0; d < 4; d++) {
						int nr = i + dr[d];
						int nc = j + dc[d];
						
						if (!check(nr,nc)) continue;
						// 2가있으면 count++
						if (board[nr][nc] == 2) count++;
					}
					// 2이상이면 1 -> 3
					if (count >= 2) board[i][j] = 3;
				}
			}
		}	
	}

	private static int[] dr = {-1,1,0,0};
	private static int[] dc = {0,0,1,-1};
	
	private static void bfs2(int r, int c, int[][] board) {
		Queue<int[]> queue = new LinkedList<int[]>();
		queue.add(new int[] {r,c}); // 시작점 대입
		boolean[][] visited = new boolean[N][M];
		board[r][c] = 2;			
		
		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			int curX = cur[0];
			int curY = cur[1];
			
			// 방문한거면 continue;
			if (visited[curX][curY]) continue;
			visited[curX][curY] = true;
			
			// 4방 탐색
			for (int d = 0; d < 4; d++) {
				int nr = curX + dr[d];
				int nc = curY + dc[d];
				
				// 범위내에 있어야한다.
				if (!check(nr,nc)) continue;
				// 0이여야 함.
				if (board[nr][nc] == 1) continue;

				// 범위내에 있고, 0임.
				board[nr][nc] = 2;
				queue.add(new int[] {nr,nc});
			}
		}
		
	}
	private static boolean check(int nr, int nc) {
		return 0 <= nr && nr < N && 0 <= nc && nc < M;
	}
	private static void scan(int[][] temp, int[][] origin) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				temp[i][j] = origin[i][j]; // temp에 origin을 복사
			}
		}
	}
}