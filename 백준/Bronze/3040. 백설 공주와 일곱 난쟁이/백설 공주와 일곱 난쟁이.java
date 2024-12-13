import java.io.*;
import java.util.*;

public class Main {
    static int[] small;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        small = new int[9];

        int sum = 0;
        for (int i = 0; i < 9; i++) {
            small[i] = Integer.parseInt(br.readLine());
            sum += small[i];
        }

        // 9C2

        arr = new int[2];
        dfs(0, sum - 100, 0, 0);

        int idx = 0;
        for (int i = 0; i < 9; i++) {
            if (idx < 2 && arr[idx] == small[i]) {
                idx++;
                continue;
            }
            System.out.println(small[i]);
        }



        bw.flush();
        br.close();
        bw.close();
    }

    // combi
    static int[] arr;
    static boolean isFinish;
    private static void dfs(int startIdx, int leftNumber, int cnt, int sum) {
        if (isFinish) return; // 걍 다 무시하고 탈출
        if (cnt == 2) { // 2개 다고름
            if (sum == leftNumber){
                isFinish = true;
            }
            return;
        }
        for (int i = startIdx; i < 9; i++) {
            if (isFinish) continue; // 걍 다 무시하고 탈출
            arr[cnt] = small[i];
            dfs(i + 1, leftNumber, cnt + 1, sum + small[i]);
        }
    }
}