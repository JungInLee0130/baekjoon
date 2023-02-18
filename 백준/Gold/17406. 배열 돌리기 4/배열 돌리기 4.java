import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static int N, M, K;
    static int[][] board;
    static int[][] copy;
    static int[][] command;
    static int A = Integer.MAX_VALUE; // 각 행의 합 중 최솟값.
    static int r,c,s;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        // board 완성
        board = new int[N + 1][M + 1];
        copy = new int[N + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 회전 전환
        command = new int[K][3];
        for (int k = 0; k < K; k++) {
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 3; i++) {
                command[k][i] = Integer.parseInt(st.nextToken()); // (r,c) s
            }
        }

        // (r - s, c - s) (r + s, c + s) -> 2s + 1 : 한 변
        // command K!개있음
        visited = new boolean[K];
        arr = new int[K];
        perm(0);

        bw.write(String.valueOf(A));


        bw.flush();
        br.close();
        bw.close();
    }

    private static boolean[] visited;
    private static int[] arr;
    private static void perm(int cnt) { // 순열
        if (cnt == K) {
            // rotate? // arr의 순서에 맞게 로테이트 다 함.
            beforeRotate();
            // 행합 중에 가장 작은값 A 계산 : copy
            minRowSum(copy);
            return;
        }
        for (int i = 0; i < K; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            arr[cnt] = i;
            perm(cnt + 1);
            arr[cnt] = 0;
            visited[i] = false;
        }
    }

    private static void minRowSum(int[][] copy) {
        for (int i = 1; i <= N; i++) {
            int sum = 0;
            for (int j = 1; j <= M; j++) {
                sum += copy[i][j];
            }
            A = Math.min(sum, A);
        }
    }

    // 로테이트 하기전에 필요한 준비들
    private static void beforeRotate() {
        // arr : command 순서
        // 배열 1 ~ K까지 실행
        // 임시배열에 rotate -> scan
        scan(copy, board);
        // K만큼 반복 // arr 순서만큼 회전 변환
        for (int i = 0; i < K; i++) {
            int index = arr[i];
            int r = command[index][0];
            int c = command[index][1];
            int s = command[index][2];
            // 한변의 길이 : 정사각형
            int len = 2 * s + 1;
            // 겹의 개수
            int gap = len / 2; // 겹
            // 갭만큼 반복 : (0,0) (1,1) ...
            // 한변의 길이
            int gapNum = len;
            // 로테이트 반복할때마다 -2씩 줄어듬.
            for (int k = 0; k < gap; k++) {
                int count = 2 * (2 * gapNum - 2); // 2 * ( 4 * s + 2 - 2) = 8 * s
                // rotate에는 시작점이 들어감.
                rotate(r,c,s,k);
                gapNum -= 2;
            }
        }
    }

    private static void scan(int[][] temp, int[][] origin) {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                temp[i][j] = origin[i][j];
            }
        }
    }

    private static int[] dr = {0,1,0,-1}; // 우, 하, 좌, 상
    private static int[] dc = {1,0,-1,0};
    private static void rotate(int r, int c, int s, int k) { // 회전은 어짜피 한번만 함.
        // 갭 반복
        // 시계방향 board[nr][nc] = board[r][c]
        // 음.. 이건 다음거를 저장해놔야한다.
        // 둘레 개수만큼 반복, 근데 한번만 회전하니까 그냥 둠.
        //for (int i = 0; i < count; i++) {
        int up = r - s + k;
        int bottom = r + s - k;
        int left = c - s + k;
        int right = c + s - k;

        int d = 0;
        int curR = up; // 왼쪽 위 좌표 저장
        int curC = left;
        int temp = copy[curR][curC];
        while (d < 4) { // 한바퀴 다돔.
            int nr = curR + dr[d];
            int nc = curC + dc[d];

            // 범위에 속하지않으면 : 방향 바꿈.
            // 체크 로직은 독립적으로.
            if (!check(nr,nc, r, c, s, k)) {
                d += 1;
                continue;
            }

            // 범위에 속함.
            // 넥스트 저장
            int tempNext = copy[nr][nc];
            // 기존꺼 옮기기
            copy[nr][nc] = temp; // 새 좌표로 바꿈.
            temp = tempNext;
            // 기존꺼두기
            curR = nr;
            curC = nc;
        }
        // 마지막에
        //System.out.println();
    }
    // 범위가 계속 줄어든다.
    private static boolean check(int nr, int nc, int r, int c, int s, int k) {
        return r - s + k <= nr && nr <= r + s - k // 1 <= nr <= 5
                && c - s + k <= nc && nc <= c + s - k; // 2 <= nc <= 6
    }
}