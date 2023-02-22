import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int R,C;
	static char[][] map;
	static Queue<int[]> ratQueue = new LinkedList<>();
	static Queue<int[]> waterQueue = new LinkedList<>();
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		
		
		for (int i = 0; i < R; i++) {
			String str = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = str.charAt(j);
				if (map[i][j] == 'S') {
					ratQueue.add(new int[] {i,j,0});
				}
				if (map[i][j] == '*') {
					waterQueue.add(new int[] {i, j});
				}
			}
		}
		
		bfs();
		
		bw.write(String.valueOf(min == Integer.MAX_VALUE ? "KAKTUS" : min));
		
		
		// S -> D
		// S -> 이동
		// * -> 이동
	
		
		
		
		
		bw.flush();
		br.close();
		bw.close();
	}
	private static int[] dr = {-1,1,0,0};
	private static int[] dc = {0,0,1,-1};
	private static int min = Integer.MAX_VALUE;
	private static void bfs() {
		while (!ratQueue.isEmpty()) {
			// 물 퍼뜨림.
			int len = waterQueue.size();
			
			for (int i = 0; i < len; i++) {
				int[] water = waterQueue.poll();
				int waterX = water[0];
				int waterY = water[1];
				
				for (int d = 0; d < 4; d++) {
					int newWaterX = water[0] + dr[d];
					int newWaterY = water[1] + dc[d];
					
					if (check(newWaterX, newWaterY) && map[newWaterX][newWaterY] == '.') {
						map[newWaterX][newWaterY] = '*'; // 물로 수정.
						waterQueue.add(new int[] {newWaterX, newWaterY});
					}
				}
			}
			
			/*for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					System.out.print(map[i][j] + " ");
				}
				System.out.println();
			}
			System.out.println();*/
			len = ratQueue.size();
			for (int i = 0; i < len; i++) {
				int[] rat = ratQueue.poll();
				int ratX = rat[0];
				int ratY = rat[1];
				int time = rat[2];
				
				for (int d = 0; d < 4; d++) {
					int newRatX = rat[0] + dr[d];
					int newRatY = rat[1] + dc[d];
					
					if (check(newRatX, newRatY)) {
						 if (map[newRatX][newRatY] == 'D') {
							min = Math.min(min, time + 1);
							return;
						 }
						 else if (map[newRatX][newRatY] == '.' ){
								map[newRatX][newRatY] = 'S'; // 물로 수정.
								ratQueue.add(new int[] {newRatX, newRatY, time + 1});
						}
					}
				}
			}
			
			/*for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					System.out.print(map[i][j] + " ");
				}
				System.out.println();
			}
			System.out.println();*/
		}
	}
	private static boolean check(int r, int c) {
		return 0 <= r && r < R && 0 <= c && c < C;
	}
	
}
/*
 * 고슴도치
 * (r,c)
 * . -> 비어있는곳
 * * -> 물차있는 곳
 * X -> 돌
 * D, S -> 비버굴, 고슴도치 위치
 * 매 분마다 고슴도치는 현재 있는 칸과 인접한 네 칸 중 하나로 이동
 * 물도 확장
 * 물과 고슴도치는 돌을 통과 할 수없다.
 * 물도 비버의 소굴로 이동할수없다.
 * */