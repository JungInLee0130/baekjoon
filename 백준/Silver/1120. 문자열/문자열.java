import org.w3c.dom.ls.LSOutput;

import javax.naming.LimitExceededException;
import java.io.*;
import java.util.*;

public class Main {
    static String a, b;
    static int alen, blen;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        a = st.nextToken();
        b = st.nextToken();

        alen = a.length();
        blen = b.length();

        if (alen < blen) {
            for (int i = 0; i <= blen - alen; i++) {
                compare(i);
            }
            // 그냥 min 출력하면됨. 어짜피 나머지 다 같게 만들면 되니까.
        }
        else{
            // 그냥 비교
            compare(0);
        }

        System.out.println(min);
    }

    static int min = Integer.MAX_VALUE;
    static int index = 0;
    private static void compare(int start) {
        int count = 0;
        for (int i = 0; i < alen; i++) {
            if (a.charAt(i) != b.charAt(start + i)) {
                count++;
            }
        }

        if (count < min) {
            min = count;
            index = start;
        }
    }
}