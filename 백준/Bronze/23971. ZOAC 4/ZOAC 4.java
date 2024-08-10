import java.io.*;
import java.util.*;

public class Main {
    static double H, W, N, M;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        H = Double.parseDouble(st.nextToken()); // 행
        W = Double.parseDouble(st.nextToken()); // 열
        N = Double.parseDouble(st.nextToken());
        M = Double.parseDouble(st.nextToken());

        long cnt = (long) (Math.ceil(H / (N + 1)) * Math.ceil(W / (M + 1)));

        System.out.println(cnt);


        bw.flush();
        br.close();
        bw.close();
    }
}