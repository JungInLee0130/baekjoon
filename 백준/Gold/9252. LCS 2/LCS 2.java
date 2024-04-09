import java.awt.*;
import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {
    static char[] str1;
    static char[] str2;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String s = br.readLine();

        str1 = new char[s.length() + 1];
        str1[0] = 0;
        for (int i = 1; i <= s.length(); i++) {
            str1[i] = s.charAt(i - 1);
        }
        s = br.readLine();
        str2 = new char[s.length() + 1];
        str2[0] = 0;
        for (int i = 1; i <= s.length(); i++) {
            str2[i] = s.charAt(i - 1);
        }


        int len1 = str1.length;
        int len2 = str2.length;

        dp = new int[len1][len2]; // 1 ~
        // row : len1, column: len2
        // dp : 일치하는것들의 개수
        for (int i = 1; i < len1; i++) { // 첫번째 문자열
            for (int j = 1; j < len2; j++) { // 두번째 문자열
                if (str1[i] == str2[j]) { // 같으면 dp 증가
                    dp[i][j] = dp[i - 1][j - 1] + 1; // 왼쪽 위 대각선 값에 + 1
                }
                else{
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]); // 왼쪽과 위 중에 큰것을 선택
                }
            }
        }

        int resultLen = dp[len1 - 1][len2 - 1];
        System.out.println(resultLen);

        if (resultLen == 0) {
            return;
        }

        // 이제 거슬러 올라가야됨.
        int num = resultLen;
        int x = len1 - 1;
        int y = len2 - 1;
        Stack<Character> stack = new Stack<>();
        while (num != 0) {
            if (str1[x] != str2[y]) {
                if (dp[x - 1][y] > dp[x][y - 1]) {
                    x = x - 1;
                    y = y;
                }
                else{
                    x = x;
                    y = y - 1;
                }
            }
            else{
                stack.push(str1[x]);
                x = x - 1;
                y = y - 1;
                num--;
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        System.out.println(sb);
    }
}