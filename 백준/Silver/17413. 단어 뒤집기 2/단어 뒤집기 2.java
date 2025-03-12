import java.io.*;
import java.util.*;

class Main {
    static String S;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        S = br.readLine();

        // tag는 그대로 단어는 반대로

        String[] sp = S.split(" ");

        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();

        StringBuilder answer = new StringBuilder();

        final int TAG = 0;
        final int WORD = 1;
        final int SPACE = 2;

        int state = -1;

        for (int i = 0; i < S.length(); i++) {
            if (S.charAt(i) == '<' || state == TAG){
                if (sb2.length() > 0) {
                    answer.append(sb2.reverse());
                    sb2 = new StringBuilder();
                }
                if (S.charAt(i) == '>') {
                    sb.append(S.charAt(i));
                    answer.append(sb);
                    sb = new StringBuilder();
                    state = -1;
                    continue;
                }
                state = TAG;
                sb.append(S.charAt(i));
                continue;
            }
            if (S.charAt(i) == ' '){
                if (sb2.length() > 0) {
                    answer.append(sb2.reverse());
                    sb2 = new StringBuilder();
                }
                answer.append(" ");
                continue;
            }
            state = WORD;
            sb2.append(S.charAt(i));
        }

        if (sb2.length() > 0) {
            answer.append(sb2.reverse());
        }

        System.out.println(answer);

        br.close();
    }
}