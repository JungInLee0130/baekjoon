import java.awt.*;
import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        int sum = 0;
        for (int i = 0; i < N; i++) {
            if (i == 0) {
                sum += Integer.parseInt(br.readLine());
            }
            else{
                sum += (Integer.parseInt(br.readLine()) - 1);
            }
        }

        System.out.println(sum);
    }
}