import java.awt.*;
import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {
    static int T;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            int n = Integer.parseInt(br.readLine()); // 10만

            StringTokenizer st = new StringTokenizer(br.readLine()); // 선택한 번호, 1부터 시작

            selected = new int[n + 1]; // 정
            visited = new boolean[n + 1];
            result = 0;
            isChecked = new boolean[n + 1];

            for (int i = 1; i <= n; i++) {
                selected[i] = Integer.parseInt(st.nextToken());
            }


            for (int i = 1; i <= n; i++) {
                if (!isChecked[i]) {
                    dfs(i);
                }
            }

            System.out.println(n - result);
        }

        bw.flush();
        bw.close();
        br.close();
    }
    static int[] selected;
    static int result;
    private static void dfs(int x) {
        // 이미 체크함.
        if (isChecked[x]) return;

        // visited의 목적은 재방문 찾기. 사이클 찾기임.
        // 이문제의 특징은 무조건 사이클이 탄생한다는것.
        // 이미 방문한 곳을 만나면 -> 사이클임.
        if (visited[x]){ // 이 조건문 덕분.
            isChecked[x] = true;
            result++; // 사이클일때만 다시돌아와서 ++.
        }
        // 방문하지않았으면
        visited[x] = true;
        dfs(selected[x]);
        // 여기다 result++ 넣으면 사이클 아닌것들도 다 걸림.
        isChecked[x] = true; // 사이클 아닌것들도 근데 검사 끝났으니까 ischecked.
        visited[x] = false; // 매번 초기화 해주면 시간초과 난다고함.
    }
    static boolean[] visited;
    static boolean[] isChecked;
}