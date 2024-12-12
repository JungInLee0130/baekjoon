import java.io.*;
import java.util.*;

public class Main {
    static String T, P;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        sb = new StringBuilder();

        T = br.readLine();
        P = br.readLine();

        makeArray(P);

        KMP(T, P);

        System.out.println(answer);
        System.out.print(sb.toString());

        bw.flush();
        br.close();
        bw.close();
    }
    static int[] pi;

    static void makeArray(String pattern){
        // j = 접두사, i 접미사
        int j = 0;
        pi = new int[pattern.length()];

        // 패턴을 돌면서 체크
        for(int i = 1; i < pattern.length(); i++) {
            // 처음 접두사가 아니면서 일치하지 않으면, 반복되는 패턴의 앞부분으로 변경
            // 만약 반복되는 패턴이 없으면, j = 0까지 돌아갈거임
            while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
                j = pi[j - 1];
            }
            // 일치할때, 접두사의 길이(경계) 저장
            // 현재 맞은 idx의 +1은 길이이자, 다음 체크할 idx가 됨
            if (pattern.charAt(i) == pattern.charAt(j)) {
                pi[i] = ++j;
            }
        }
    }

    static StringBuilder sb;
    static int answer;

    static void KMP(String all, String pattern){
        int j = 0;
        // 전체 문자열 돌기
        for (int i = 0; i < all.length(); i++) {
            while (j > 0 && all.charAt(i) != pattern.charAt(j)) {
                j = pi[j - 1];
            }
            if (all.charAt(i) == pattern.charAt(j)) {
                if (j == pattern.length() - 1) {
                    answer++;
                    sb.append(i + 1 - pattern.length() + 1 + " ");
                    j = pi[j];
                }
                else{
                    j++;
                }
            }
        }
    }
}