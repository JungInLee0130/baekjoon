import java.awt.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    static int N, K;
    static String[] strs;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        // dfs로 그냥 모든 케이스 비교
        // 어짜피 알파벳은 26개이고 N도 50이라 시간있음.

        strs = new String[N];
        for (int i = 0; i < N; i++) { // 50이하 // 갈갈의 길이는 15보다 작거나 같음
            String str = br.readLine();
            strs[i] = str.replaceAll("anta|tica", ""); // 정규표현식 | 사용
        }

        max = Integer.MIN_VALUE;

        // 우선 5개는 알아야함
        if (K < 5) {
            max = 0;
        } else if (K == 26) {
            max = N; // N개 다 읽을수있음.
        } else {
            visited = new boolean[26]; // 0 ~ 25
            visited['a' - 'a'] = true;
            visited['n' - 'a'] = true;
            visited['t' - 'a'] = true;
            visited['i' - 'a'] = true;
            visited['c' - 'a'] = true;

            dfs(0,0);
        }

        bw.write(String.valueOf(max));

        bw.flush();
        bw.close();
        br.close();
    }

    static boolean[] visited;
    static int max;
    private static void dfs(int start, int count) { // combi
        if (count == K - 5) {
            int wordCount = 0;

            for (int i = 0; i < N; i++) {
                int len = strs[i].length();
                boolean isRead = true;
                for (int j = 0; j < len; j++) {
                    char c = strs[i].charAt(j);
                    if (!visited[c - 'a']) { // 방문하지 않은것이 있을때 읽을 수 없음.
                        isRead = false;
                        break;
                    }
                }
                if (isRead) {
                    wordCount++;
                }
            }

            max = Math.max(max, wordCount);
            return;
        }

        for (int i = start; i < 26; i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(i + 1, count + 1);
                visited[i] = false; // 완탐인듯
            }
        }
    }
}