import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    static String N, M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = st.nextToken();
        M = st.nextToken();

        if (N.equals(M)) {
            System.out.println(1);
        }
        else{
            System.out.println(0);
        }

        bw.flush();
        bw.close();
        br.close();
    }
}