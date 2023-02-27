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
    static int[][] board;
    static int[][] copyMap;
    static int[][] black;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        black = new int[N][2];
        // 검정 스카프 왼쪽 아래 좌표 구함.
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            // 양쪽 +2 추가 하므로 좌표 늘린다.
            black[i][0] = Integer.parseInt(st.nextToken()) + 1;
            black[i][1] = Integer.parseInt(st.nextToken()) + 1;
        }

        // 양쪽에 +2를 추가해야할듯
        board = new int[102][102];
        copyMap = new int[102][102];

        for (int i = 0; i < N; i++) {
            int c = black[i][0];
            int r = black[i][1];
            for (int row = r; row < r + 10; row++) {
                for (int column = c; column < c + 10; column++) {
                    board[row][column] = 1; // 검은색 스카프 부분 1로 초기화
                }
            }
        }

        // board 복사 -> copyMap
        for (int i = 0; i < 102; i++) {
            copyMap[i] = Arrays.copyOf(board[i], 102);
        }

        // 1 -> 3 : 모든 둘레에 대해서
        for (int r = 0; r < 102; r++) {
            for (int c = 0; c < 102; c++) {
                if (copyMap[r][c] == 0 && !visited[r][c]) {
                    bfs(r,c);
                }

            }
        }
        // 예외적으로 끝에있는 1에 대하여



        // 3인 원소 세기
        total = 0;
        for (int i = 1; i <= 100; i++) {
            for (int j = 1; j <= 100; j++) {
                if (copyMap[i][j] == 3){
                    for (int d = 0; d < 4; d++) {
                        int nr = i + dr[d];
                        int nc = j + dc[d];
                        
                        if (copyMap[nr][nc] == 0){
                            total++;
                        }
                    }
                }
            }
        }
        /*for (int r = 1; r <= 100; r++) {
            for (int c = 1; c <= 100; c++) {
                if (copyMap[r][c - 1] == 3 && copyMap[r][c] == 3
                        || copyMap[r][c] == 3 && copyMap[r][c + 1] == 3) {
                    total++;
                }
            }
        }*/

        /*for (int c = 1; c <= 100; c++) {
            for (int r = 1; r <= 100; r++) {
                if (copyMap[r - 1][c] == 3 && copyMap[r][c] == 3
                        || copyMap[r][c] == 3 && copyMap[r + 1][c] == 3) {
                    total++;
                }
            }
        }*/
		
		
		
		/*System.out.println();
		for (int i = 0; i <= 101; i++) {
			for (int j = 0; j <= 101; j++) {
				System.out.print(copyMap[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();*/

        System.out.println(total);

        bw.flush();
        br.close();
        bw.close();
    }


    static int total;
    // copyMap
    // 0이 있는 바깥쪽 모두 bfs로 훑으면서 만나는 1 -> 3
    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};
    static boolean[][] visited = new boolean[102][102];
    private static void bfs(int r, int c) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {r,c});
        visited[r][c] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int curR = cur[0];
            int curC = cur[1];


            for (int d = 0; d < 4; d++) {
                int nr = curR + dr[d];
                int nc = curC + dc[d];

                // 범위 안
                if (!check(nr,nc)) continue;
                // 중복방문 방지
                if (visited[nr][nc]) continue;
                // 이미 바꾼것은 continue;
                if (copyMap[nr][nc] == 3) continue;
                // 1이면 3으로 바꾸고 continue;
                if (copyMap[nr][nc] == 1) {
                    copyMap[nr][nc] = 3; // 겉에 3으로 바꾸기
                    continue;
                }
                // 0인 곳은 visited 하고 큐에 넣음
                visited[nr][nc] = true;
                queue.add(new int[] {nr,nc});
            }
        }
    }
    private static boolean check(int nr, int nc) {
        return 1 <= nr && nr < 101 && 1 <= nc && nc < 101;
    }

}
