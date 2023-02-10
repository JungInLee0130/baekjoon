import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N,M;
	static int[][] map;
	static int[][] visited;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1}; // 상 우 하 좌
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new int[N][M];
		
		
		// 1 : 이동 가능 0 : 이동 x
		// 4방, 8방 검사 -> 4방
		// 1,1 -> N, M 최소칸수 -> BFS
		for (int i = 0; i < N; i++) {
			String s = br.readLine(); //
			char[] cs = s.toCharArray();
			for (int j = 0; j < M; j++) {
				map[i][j] = cs[j] - '0'; // '1' - '0' = 49 - 48
			}
		}
		
		bfs();
		System.out.println(visited[N - 1][M - 1]);
		
		bw.flush();
		br.close();
		bw.close();
	}
	private static void bfs() {
		Queue<int[]> que = new LinkedList<>();
		que.offer(new int[] {0,0});
		visited[0][0] = 1;
		while (!que.isEmpty()) {
			int[] cur = que.poll();
			int r = cur[0];
			int c = cur[1];
			
			if (r == N - 1 && c == M - 1) {
				return;
			}
			
			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				if (!check(nr, nc)) continue;
				if (map[nr][nc] == 1 && visited[nr][nc] == 0) {
					que.offer(new int[] {nr,nc});
					visited[nr][nc] = visited[r][c] + 1;
				}
			}
		}
	}
	private static boolean check(int nr, int nc) {
		return 0 <= nr && nr < N && 0 <= nc && nc < M;
	}
}