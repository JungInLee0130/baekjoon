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
        for (int i = 0; i < len; i++) {
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

        // 문제는 10000 -> 9999 : 6 이 일어날수도있어서 500000까지 다돌려야할듯
        // 1. 그냥 +, -로 이동하는 경우를 최악의수로 저장
        min = Math.abs(100 - N);

        if (N == 100) {
            System.out.println(0);
            return;
        }

        // 6자리
        arr = new int[6];
        // 시간초과나므로 number을 저장하면서 이동
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
        if (idx == 6) return; // 6자리면 return
        // 그냥 완탐
        for (int i = 0; i < 10; i++) {
            if (!isBroken[i]) {
                int nNumber = number * 10 + i;
                int count = Math.abs(nNumber - N) + String.valueOf(nNumber).length();
                min = Math.min(min, count);
                dfs(idx + 1, nNumber);
            }
        }
    }
}