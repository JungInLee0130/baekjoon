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
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        str = br.readLine();

        int open = 0; // 괄호의 닫힘 여부
        int ans = 0; // 통나무 개수
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(') {
                open++;
            }
            else {
                open--; // 닫음

                if (i > 0) {
                    if (str.charAt(i - 1) == '(') { // 레이저인 경우
                        ans += open;
                    }
                    else{ // 나머지 막대인경우
                        ans++;
                    }
                }
            }
        }

        System.out.println(ans);

        bw.flush();
        bw.close();
        br.close();
    }
}