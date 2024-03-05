import org.w3c.dom.ls.LSOutput;

import javax.naming.LimitExceededException;
import java.io.*;
import java.util.*;

public class Main {
    static int N, kim, lim;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        kim = Integer.parseInt(st.nextToken());
        lim = Integer.parseInt(st.nextToken());

        int round = 1;

        while (kim != lim) {
            kim = (int) Math.round(kim / 2.0);
            lim = (int) Math.round(lim / 2.0);

            //System.out.println(kim);
            //System.out.println(lim);

            if (kim == lim) {
                break;
            }
            round = round + 1;
        }

        System.out.println(round);
    }
}