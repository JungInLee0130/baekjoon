import java.awt.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    static int R1, R2, S;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        R1 = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        R2 = S * 2 - R1;
        System.out.println(R2);

        bw.flush();
        bw.close();
        br.close();
    }
}