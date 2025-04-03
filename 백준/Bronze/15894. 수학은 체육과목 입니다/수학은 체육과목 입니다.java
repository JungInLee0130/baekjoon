import java.io.*;
import java.util.*;

class Main {
    static long N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Long.parseLong(br.readLine());

        System.out.println(4 * N);
        br.close();
    }
}