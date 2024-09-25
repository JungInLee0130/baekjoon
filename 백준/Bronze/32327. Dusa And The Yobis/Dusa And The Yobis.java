import java.awt.*;
import java.io.*;
import java.math.BigInteger;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    static BigInteger N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = new BigInteger(br.readLine());

        while (true){
            BigInteger num = new BigInteger(br.readLine());

            if (N.compareTo(num) <= 0){
                break;
            }
            N = N.add(num);
        }

        System.out.println(N);

        bw.flush();
        bw.close();
        br.close();
    }
}