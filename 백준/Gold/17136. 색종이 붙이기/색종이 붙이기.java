import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int answer;
    static int[][] board;
    static int[] paper = {0, 5, 5, 5, 5, 5};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 보드 채우기
        board = new int[10][10];
        for (int i = 0; i < 10; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 10; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        answer = Integer.MAX_VALUE;

        dfs(0, 0, 0);


        if (answer == Integer.MAX_VALUE) {
            bw.write(String.valueOf(-1));
        } else{
            bw.write(String.valueOf(answer));
        }



        bw.flush();
        br.close();
        bw.close();
    }

    private static void dfs(int r, int c, int cnt) {
        if (r >= 9 && c > 9) { // 탈출조건 : (9,10)을 가면 -> (9,9)까지 다돌았으므로 cnt 값 대소비교 후 리턴
            answer = Math.min(answer, cnt);
            return;
        }

        if (answer <= cnt) { // 프루닝 : answer보다 cnt가 크면 탐색 x
            return;
        }

        if (c > 9) { // column 전부 봄 : 아래로 이동.
            dfs(r + 1, 0, cnt);
            return;
        }

        if (board[r][c] == 1){ // 1이면 탐색
            for (int k = 5; k >= 1; k--) {
                // 카운트와 범위, 1 체크
                if (paper[k] > 0 && check(r,c,k)) // 백트래킹
                {
                    attach(r, c, k);
                    paper[k]--; // 횟수 줄임
                    dfs(r, c + 1, cnt + 1);
                    paper[k]++;
                    detach(r, c, k);
                }
            }
        } else{
            dfs(r, c + 1, cnt);
        }
    }


    private static boolean check(int r, int c, int k) {
        // kxk를 만족하는지 범위 체크
        for (int i = r; i < r + k; i++) {
            for (int j = c; j < c + k; j++) {
                if (i < 0 || i >= 10 || j < 0 || j >= 10) { // 범위에 속하지않으면
                    return false;
                }
                if (board[i][j] != 1) { // 1이 아니거나
                    return false;
                }
            }
        }
        return true;
    }

    private static void attach(int r, int c, int num) {
        for (int i = r; i < r + num; i++) {
            for (int j = c; j < c + num; j++) {
                if (board[i][j] == 1) { // 1 -> 0
                    board[i][j] = 0;
                }
            }
        }
    }
    private static void detach(int r, int c, int num) {
        for (int i = r; i < r + num; i++) {
            for (int j = c; j < c + num; j++) {
                if (board[i][j] == 0) { // 0 -> 1
                    board[i][j] = 1;
                }
            }
        }
    }
}