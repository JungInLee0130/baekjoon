import org.w3c.dom.ls.LSOutput;

import javax.naming.LimitExceededException;
import java.io.*;
import java.util.*;

public class Main {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        // 가능한 성의 첫글자 사전순으로 공백없이 출력
        int[] alphabet = new int[26]; // 0 ~ 25
        for (int i = 0; i < N; i++) {
            String str = br.readLine();

            char c = str.charAt(0);

            // 소문자만 들어감
            int index = c - 'a';

            alphabet[index]++; // count추가
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            if (alphabet[i] >= 5) {
                sb.append((char)(i + 'a'));
            }
        }

        if (sb.toString().length() == 0) {
            System.out.println("PREDAJA");
        }
        else{
            System.out.println(sb.toString());
        }
    }
}