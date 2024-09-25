import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    static int N, M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        while (true){
            int num = Integer.parseInt(br.readLine());

            if (N <= num){
                System.out.println(N);
                break;
            }
            N += num;
        }

        bw.flush();
        bw.close();
        br.close();
    }
}