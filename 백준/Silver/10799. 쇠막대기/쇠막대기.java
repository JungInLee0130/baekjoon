import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.sql.SQLOutput;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    static int M, N;
    static class Stick{
        int start;
        int end;

        int razerNum;

        public Stick(int start, int end, int razerNum) {
            this.start = start;
            this.end = end;
            this.razerNum = razerNum;
        }
    }
    static String str;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        str = br.readLine();

        StringBuilder sb = new StringBuilder();

        Stack<Integer> indexStack = new Stack<>();

        ArrayList<Stick> stickList = new ArrayList<>();

        sb.append(str.charAt(0));
        for (int i = 1; i < str.length(); i++) {
            char c = str.charAt(i);

            if (str.charAt(i - 1) == '(' && str.charAt(i) == ')') {
                sb.append('.');
            }
            sb.append(c);
        }

        ArrayList<Integer> razerPoint = new ArrayList<>();

        for (int i = 0; i < sb.length() - 1; i++) {
            char c = sb.charAt(i);

            if (sb.charAt(i) == '('
                    && sb.charAt(i + 1) == '.') {
                razerPoint.add(i + 1);
                i = i + 2;
            }
            else if (sb.charAt(i) == '(') {
                indexStack.push(i);
            } else if (sb.charAt(i) == ')') {
                stickList.add(new Stick(indexStack.pop(), i, 0));
            }
        }

        if (sb.charAt(sb.length() - 2) == ')' && sb.charAt(sb.length() - 1) == ')') {
            stickList.add(new Stick(indexStack.pop(), sb.length() - 1, 0));
        }

        // 레이저는 .로 다표시됨.
        //System.out.println(sb);

        int res = 0;
        for (Stick s:stickList) {
            //System.out.println(s.start +" " + s.end + " ");
            for (Integer e: razerPoint) {
                if (s.start <= e && e <= s.end) {
                    s.razerNum++;
                }
            }
            //System.out.println(s.razerNum + 1);
            res += s.razerNum + 1;
        }

        System.out.println(res);

        bw.flush();
        bw.close();
        br.close();
    }
}