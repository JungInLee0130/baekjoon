import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static String str;
    static int[] dp;
    static boolean[][] palindrome;
    static int len;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        str = br.readLine();

        len = str.length();

        dp = new int[len];
        palindrome = new boolean[len][len];

        // dp[i] = 0 ~ i까지 펠린드롬의 최소개수
        // palindrome[j][i] : j ~ i까지 문자열이 펠린드롬이면 true, 아니면 false
        // dp[i] = (dp[j - 1]) + 1

        checkPalindrome();

        for (int i = 0; i < len; i++) {
            dp[i] = Integer.MAX_VALUE;
        }
        //Arrays.fill(dp, Integer.MAX_VALUE);

        findAnswer();

        System.out.println(dp[len - 1]);


        br.close();
    }

    private static void findAnswer() {
        for (int i = 0; i < len; i++) { // 바깥쪽
            for (int j = 0; j <= i; j++){ // 안쪽
                if (palindrome[j][i]) {
                    if (j == 0) {
                        dp[i] = 1;
                    }
                    else{
                        dp[i] = Math.min(dp[i], dp[j - 1] + 1);
                    }
                }
            }
        }
    }

    private static void checkPalindrome() {
        for (int i = 0; i < len; i++) {
            for (int j = i; j < len; j++) {
                int start = i;
                int end = j;

                boolean isPalindrome = true;
                while (start <= end){
                    if (str.charAt(start) != str.charAt(end)) {
                        isPalindrome = false;
                        break;
                    }
                    start++;
                    end--;
                }

                if (isPalindrome) {
                    palindrome[i][j] = true;
                }
                else{
                    palindrome[i][j] = false;
                }
            }
        }
    }
}