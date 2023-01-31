
import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String str = br.readLine();

        searchN(str);

        bw.flush();
        br.close();
        bw.close();
    }

    private static void searchN(String str) {
        int num = 1;
        int len = str.length(); // count
        int i = 0;
        while (i < len) {
            String sNum = String.valueOf(num++);
            for (int j = 0; j < sNum.length(); j++) {
                if (sNum.charAt(j) == str.charAt(i)) {
                    i++;
                    if (i == str.length()) {
                        System.out.println(num - 1);
                        return;
                    }
                }
            }
        }
    }
}
