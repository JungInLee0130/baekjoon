import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.sql.SQLOutput;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    static String strA;
    static String strB;
    static int[][] strs;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String s1 = br.readLine();
        String s2 = br.readLine();

        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();

        sb1.append(" ");
        sb2.append(" ");

        for (int i = 0; i < s1.length(); i++) {
            sb1.append(s1.charAt(i));
        }

        for (int i = 0; i < s2.length(); i++) {
            sb2.append(s2.charAt(i));
        }


        strA = sb1.toString();
        strB = sb2.toString();

        int len1 = s1.length();
        int len2 = s2.length();

        strs = new int[len1 + 1][len2 + 1]; // 1 ~

        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (strA.charAt(i) == strB.charAt(j)) {
                    strs[i][j] = strs[i - 1][j - 1] + 1;
                }
                else{
                    strs[i][j] = Math.max(strs[i - 1][j], strs[i][j - 1]);
                }
            }
        }

        /*for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                System.out.print(strs[i][j] + " ");
            }
            System.out.println();
        }*/

        System.out.println(strs[len1][len2]);

        bw.flush();
        bw.close();
        br.close();
    }
}