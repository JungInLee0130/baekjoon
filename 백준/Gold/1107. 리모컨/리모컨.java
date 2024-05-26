import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.sql.SQLOutput;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    static String str;
    static int N,M;
    static int[] num;
    static boolean[] isBroken;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        str = br.readLine();
        N = Integer.parseInt(str);
        M = Integer.parseInt(br.readLine());

        len = str.length();
        
        num = new int[len];
        for (int i = 0; i < str.length(); i++) {
            num[i] = str.charAt(i) - '0'; // N의 자릿수
        }
        
        isBroken = new boolean[10];
        if (M != 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                int brokenNum = Integer.parseInt(st.nextToken());
                isBroken[brokenNum] = true;
            }
        }

        // 100 -> N 이동 : 최소 몇번 눌러야함?
        // +, - 도 있음
        // 있는 버튼 중에서 가장 가까운 곳으로 이동 -> - 또는 +로 이동
        min = Math.abs(100 - N);

        if (N == 100) {
            System.out.println(0);
            return;
        }

        arr = new int[6];
        dfs(0,0);

        System.out.println(min);


        bw.flush();
        bw.close();
        br.close();
    }

    static int[] arr;
    static int len;
    static int min;
    private static void dfs(int idx, int number) {
        // 랜덤 돌림
        for (int i = 0; i < 10; i++) {
            if (!isBroken[i]) {
                int nNumber = number * 10 + i;

                int count = Math.abs(nNumber - N) + String.valueOf(nNumber).length();

                // 최소횟수 구하기
                min = Math.min(min, count);

                if (idx < 6) {
                    dfs(idx + 1, nNumber);
                }
            }
        }
    }
}