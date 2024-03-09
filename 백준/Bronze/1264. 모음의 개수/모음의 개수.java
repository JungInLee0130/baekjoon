import org.w3c.dom.ls.LSOutput;

import javax.naming.LimitExceededException;
import java.io.*;
import java.util.*;

public class Main {
    static String str;
    static char[] upper = {'A', 'E', 'I', 'O', 'U'};
    static char[] lower = {'a', 'e', 'i', 'o', 'u'};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            str = br.readLine();

            if (str.charAt(0) == '#' && str.length() == 1) {
                break;
            }

            int len = str.length();
            int count = 0;
            for (int i = 0; i < len; i++) {
                for (int j = 0; j < 5; j++) {
                    if (str.charAt(i) == upper[j] || str.charAt(i) == lower[j]) {
                        count++;
                    }
                }
            }
            System.out.println(count);
        }

    }
}