import javax.print.attribute.standard.MediaSize;
import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] stats;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        stats = new int[N][N];
        // 스탯 저장 : 0 ~ 5
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                stats[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 조합 뽑기
        startTeam = new int[N / 2]; // 6 / 2 = 3 : 0 ~ 2
        linkTeam = new int[N / 2];
        permutationVisited = new boolean[N]; // 0 ~ 5
        Permutation(0, 0);

        bw.write(String.valueOf(min));

        bw.flush();
        br.close();
        bw.close();
    }

    static int[] startTeam;
    static int[] linkTeam;
    static boolean[] permutationVisited;
    static int min = 10000;
    private static void Permutation(int count, int start) { // DFS
        // N / 2개 채워지면 link 배열도 채우고 능력치 계산
        if (count == N / 2) { // 조합 한가지 뽑을때마다
            /*linkTeamFill();*/
            summation();
            return;
        }
        for (int i = start; i < N; i++) {
            if (!permutationVisited[i]) {
                permutationVisited[i] = true;
                Permutation(count + 1, i + 1);
                permutationVisited[i] = false;
            }
        }
    }


    private static void summation() {
        int startSum = 0;
        int linkSum = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (permutationVisited[i] && permutationVisited[j]) {
                    startSum += (stats[i][j]);
                } else if (!permutationVisited[i] && !permutationVisited[j]) {
                    linkSum += (stats[i][j]);
                }
            }
        }

        min = Math.min(Math.abs(startSum - linkSum), min);
    }
    
}
