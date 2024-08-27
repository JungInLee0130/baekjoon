import java.io.*;
import java.util.*;

public class Main {
    static int N;
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
        // 오른쪽 끝에 도달
        if (idx == N) {
            // 인덱스 모집 완료
            max = Math.max(max, cnt);
            return;
        }

        // idx 계란이 내구도를 다하거나, 모든 계란이 깨짐
        if (eggs[idx][0] <= 0 || cnt == N - 1){
            permu(idx + 1, cnt); // 계속 다음계란으로 옮김 : 최대값을 도출해야하기때문
            return;
        }
        
        for (int i = 0; i < N; i++) {
            // 자기 자신 or 치려는 계란의 내구도가 0
            if (idx == i || eggs[i][0] <= 0) continue;
            
            // 새로운 내구도로 한다.
            int ncnt = cnt;
            // 두계란 부딫힘 : 내구도 깎임
            ncnt += eggFight(idx, i); // idx와 i랑 fight

            // 부딫힌상태를 가지고 계속, 다음 계란 : 한번했으니까 다음
            permu(idx + 1, ncnt);

            // 다음 번을 알아보기위해 내구도를 복구한다. cnt는 자동으로 복구됨. 이전에 ncnt를 넣었기때문에
            eggRecover(idx, i);
        }
    }

    private static void eggRecover(int a, int b) {
        eggs[a][0] = eggs[a][0] + eggs[b][1];
        eggs[b][0] = eggs[b][0] + eggs[a][1];
    }

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