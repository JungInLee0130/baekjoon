import java.awt.*;
import java.io.*;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {
    static String str;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        str = br.readLine();

        int len = str.length();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            char c = str.charAt(i);
            if ('a' <= c && c <= 'z') {
                sb.append((char)(c - 'a' + 'A'));
            }
            else{
                sb.append((char)(c - 'A' + 'a'));
            }
        }

        System.out.println(sb);


        bw.flush();
        bw.close();
        br.close();
    }
}