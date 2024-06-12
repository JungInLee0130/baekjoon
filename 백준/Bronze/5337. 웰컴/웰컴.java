import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringBuilder sb = new StringBuilder();

        sb.append(".  .   .\n");
        sb.append("|  | _ | _. _ ._ _  _\n");
        sb.append("|/\\|(/.|(_.(_)[ | )(/.\n");


        System.out.println(sb);


        bw.flush();
        br.close();
        bw.close();
    }
}