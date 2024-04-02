import java.awt.*;
import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {
    static String str;
    static int N, M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        str = br.readLine();
        N = str.length();
        M = Integer.parseInt(br.readLine());

        // 자료구조 사용해야할것같은데
        // 특정인덱스에 자유롭게 들어갈수있어야함.
        // linkedlist -> 시간초과
        // 두개의 stack 사용 -> ㅅㅂ 자바
        Stack<Character> cursorLeft = new Stack<>();
        Stack<Character> cursorRight = new Stack<>();

        for (int i = 0; i < N; i++) {
            cursorLeft.push(str.charAt(i));
        }

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            String s = st.nextToken();

            if (s.equals("L")) {
                if (!cursorLeft.isEmpty()) {
                    cursorRight.push(cursorLeft.pop());
                }
            }
            if (s.equals("D")) {
                if (!cursorRight.isEmpty()) {
                    cursorLeft.push(cursorRight.pop());
                }
            }
            if (s.equals("B")) {
                if (!cursorLeft.isEmpty()) {
                    cursorLeft.pop();
                }
            }
            if (s.equals("P")) {
                char letter = st.nextToken().charAt(0);
                cursorLeft.push(letter);
            }
        }

        while (!cursorLeft.isEmpty()) {
            cursorRight.push(cursorLeft.pop());
        }

        while (!cursorRight.isEmpty()) {
            bw.write(cursorRight.pop()); // ㅅㅂ bw.write까지 써야되? sout하면 시간초과남.
        }

        bw.flush();
        bw.close();
        br.close();
    }
}