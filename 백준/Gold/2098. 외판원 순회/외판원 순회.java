import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.List;

public class Main {
    static int N;
    static int[][] W;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        W = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                W[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int len = (int) Math.pow(2, N);

        int[][] dp = new int[len][N]; // 0 ~ N - 1 // 2^N, N

        // 1. 웃긴게 3->2->1 1->2->3 2->1->3 모두 같은지점으로 돌아오기때문에 드는 비용이 모두 같음.
        // 어디서 시작하던지 비용은 같음.
        // 2. 또한 1->2->3->5->4->1 1->3->2->5->4->1 에서 5->4->1의 경로가 중복됨.
        // 이를 memoization해야됨.
        // 3. 방문여부를 비트마스킹으로 저장함
        // N = 5 -> 11111 -> 1,2,3,4,5방문 (오른쪽부터 왼쪽으로 1 2 3 4 5)
        // dp[i][j] : i : 방문한 지점들이 비트마스킹으로 담김. j : 현재있는 도시

        int answer = dfs(dp, 1, 0); // 0에서 시작 001 : 1번도시 방문

        System.out.println(answer);

        br.close();
    }

    static final int INF = 15 * 100_0000;

    private static int dfs(int[][] dp, int start, int x) { // x : 현재 지점
        if (start == (1 << N) - 1) { // 11111 // 모든 도시를 지남
            if (W[x][0] == 0) return INF; // 길없음
            return W[x][0]; // 자기자신한테 되돌아오기까지 값
        }

        // 이미 방문했던 점임.
        if (dp[start][x] > 0) return dp[start][x];
        dp[start][x] = INF;

        for (int i = 0; i < N; i++) {
            // 방문하지않음 && 현재 지점에서 목표지점까지 경로있음
            if ((start & (1 << i)) == 0 && W[x][i] > 0) {
                // 1. 다음도시 방문했다고 가정
                // 2. 현재 x까지의 거리의 합
                // 자기자신에게 돌아오는 최소값이 저장됨.
                //int v1 = dfs(dp, start | (1 << i) + W[x][i], i);
                dp[start][x] = Math.min(dfs(dp, start | (1 << i), i) + W[x][i], dp[start][x]);
            }
        }

        return dp[start][x]; // 리턴
    }
}