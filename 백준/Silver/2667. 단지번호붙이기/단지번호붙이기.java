import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
	static int[][] map;
	static int N;
	static int[] count;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1}; // 상우하좌
	static int cnt;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		count = new int[N * N];
		
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			char[] sc = str.toCharArray();
			for (int j = 0; j < N; j++) {
				map[i][j] = sc[j] - '0';
			}
		}
		cnt = 0; // group
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				// 모든 지점에서 1인곳을 찾아서 상하좌우로 연결시킨다.
				if (map[i][j] == 1) {
					cnt++;
					count[cnt + 1] = 1; // 2~
					dfs(i,j, cnt + 1);
				}
			}
		}
		/*for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				
				System.out.print(map[i][j]);
			}
			System.out.println();
		}*/
		System.out.println(cnt);
		ArrayList<Integer> result = new ArrayList<>();
		for (int i = 0; i < count.length; i++) {
			if (count[i] > 0) {
				result.add(count[i]);
			}
		}
		Collections.sort(result);
		for (Integer integer : result) {
			bw.write(String.valueOf(integer) + "\n");
		}
		
		
		//System.out.println(cnt);
		
		bw.flush();
		br.close();
		bw.close();
	}
	// g으로 1로 상하좌우로 연결된것을 g로 만든다.
	private static void dfs(int r, int c, int g) {
		map[r][c] = g;
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if (!check(nr,nc)) continue;
			if (map[nr][nc] == 1) {
				count[g]++;
				dfs(nr, nc, g);
			}
		}
	}
	private static boolean check(int nr, int nc) {
		return 0 <= nr && nr < N && 0 <= nc && nc < N;
	}

}