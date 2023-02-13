
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class Main {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        // 단어 입력 받기
        String[] word = new String[N];
        for (int i = 0; i < N; i++) {
            word[i] = br.readLine();
        }

        // 어떤 알파벳이 들어갔는지 체크하기
        boolean[] alpha = new boolean[26];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < word[i].length(); j++) {
                int index = word[i].charAt(j) - 'A';
                alpha[index] = true;
            }
        }
        // 속한 알파벳 모두 true

        int[] intalpha = new int[26];
        for (int i = 0; i < 26; i++) {
            if (alpha[i]) {
                for (int j = 0; j < N; j++) {
                    for (int k = 0; k < word[j].length(); k++) {
                        if (i == word[j].charAt(k) - 'A') { // i와 알파벳이 같으면
                            intalpha[i] += Math.pow(10, (word[j].length() - 1) - k);
                        }
                    }
                }
            }
        }

        Arrays.sort(intalpha);

        int k = 9;
        int sum = 0;
        for (int i = intalpha.length - 1; i >= 0; i--) {
            if (intalpha[i] == 0) break;
            intalpha[i] *= k--;
            sum += intalpha[i];
        }

        bw.write(String.valueOf(sum));


        bw.flush();
        br.close();
        bw.close();
    }
}
