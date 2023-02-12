import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.StringTokenizer;

public class Main {
    static int R, C;
    static char[][] board;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        // 행 열 입력받음
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        // 보드 입력
        // 보드 초기화
        board = new char[R][C];
        for (int i = 0; i < R; i++) {
            String str = br.readLine();
            for (int j = 0; j < C; j++) {
                board[i][j] = str.charAt(j);
            }
        }

        boolean[] visitAlpha = new boolean[26];
        visitAlpha[board[0][0] - 'A'] = true;
        // 처음 발판 포함이니 count = 1부터 시작
        DFS(1,0,0, visitAlpha);

        bw.write(String.valueOf(max));


        bw.flush();
        br.close();
        bw.close();
    }

    // ㄴㄴ 상하좌우 다필요하고 visited 체크해야한다.
    private static int[] dx = {1, 0,-1,0}; // 하, 우
    private static int[] dy = {0, 1,0,-1};

    private static void DFS(int count, int x, int y, boolean[] visitAlpha) {
        // 현재 컬러
        char color = board[x][y];
        for (int i = 0; i < 4; i++) {
            int newX = x + dx[i];
            int newY = y + dy[i];

            // 범위 안에없으면 continue;
            // 여태까지 컬러랑 일치하면 continue;
            // 중복 방문 체크 -> 근데 이거 color로 해결될듯
            if (!check(newX, newY)
                    || isSame(board[newX][newY], visitAlpha)){
                continue;
            }
            // 범위 안에 있고, 컬러가 다르면
            visitAlpha[board[newX][newY] - 'A'] = true;
            DFS(count + 1, newX, newY, visitAlpha);
            visitAlpha[board[newX][newY] - 'A'] = false;
        }
        max = Math.max(count, max);
    }

    private static int max = 0;
    private static boolean isSame(char color, boolean[] visitAlpha) {
        for (int i = 0; i < 26; i++) {
            if (visitAlpha[color - 'A']) {
                return true;
            }
        }
        return false;
    }

    private static boolean check(int newX, int newY) {
        return 0 <= newX && newX < R && 0 <= newY && newY < C;
    }
}