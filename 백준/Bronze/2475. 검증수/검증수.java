
import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String str = br.readLine();
        String[] sp = str.split("\\s");

        int result = 0;

        for (int i = 0; i < sp.length; i++) {
            int a = Integer.parseInt(sp[i]);
            result += (a * a);
        }

        result = result % 10;

        bw.write(String.valueOf(result));
        bw.flush();
        br.close();
        bw.close();
    }
}
