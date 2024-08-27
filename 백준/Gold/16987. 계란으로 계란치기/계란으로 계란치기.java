import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static class Egg{
        int d;
        int w;

        public Egg(int d, int w) {
            this.d = d;
            this.w = w;
        }
    }
    static int[][] eggs;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        eggs = new int[N][2];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 2; j++) {
                eggs[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        max = 0;
        permu(0,0);

        System.out.println(max);
    }

    private static int max;
    private static void permu(int idx, int cnt) {
        if (idx == N) {
            // 인덱스 모집 완료
            max = Math.max(max, cnt);
            return;
        }

        if (eggs[idx][0] <= 0 || cnt == N - 1){
            permu(idx + 1, cnt);
            return;
        }

        // nC2 해서 계란 부딫히기
        int ncnt = cnt;
        for (int i = 0; i < N; i++) {
            // 자기 자신
            if (idx == i) continue;
            // 내구도 0
            if (eggs[i][0] <= 0) continue;

            cnt += eggFight(idx, i); // idx와 i랑 fight

            // 다음 계란 : 한번했으니까 다음
            permu(idx + 1, cnt);

            eggRecover(idx, i);
            cnt = ncnt;
        }
    }

    private static void recover() {

    }

    private static void eggRecover(int a, int b) {
        eggs[a][0] = eggs[a][0] + eggs[b][1];
        eggs[b][0] = eggs[b][0] + eggs[a][1];
    }

    private static boolean[] isBroken;
    private static int eggFight(int a, int b) {
        int cnt = 0;

        eggs[a][0] = eggs[a][0] - eggs[b][1];
        eggs[b][0] = eggs[b][0] - eggs[a][1];

        if (eggs[a][0] <= 0) {
            cnt++;
        }
        if (eggs[b][0] <= 0) {
            cnt++;
        }

        return cnt;
    }
}