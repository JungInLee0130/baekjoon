import java.awt.*;
import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {
    static int N, M;
    static HashMap<String, Double> map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 학점 * 과목평점 합 / 학점 총합

        map = new HashMap<>();

        map.put("A+", 4.5);
        map.put("A0", 4.0);
        map.put("B+", 3.5);
        map.put("B0", 3.0);
        map.put("C+", 2.5);
        map.put("C0", 2.0);
        map.put("D+", 1.5);
        map.put("D0", 1.0);
        map.put("F", 0.0);


        double majorSum = 0.0;
        double scoreSum = 0.0;
        for (int i = 0; i < 20; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            String title = st.nextToken();
            double score = Double.parseDouble(st.nextToken());
            String rank = st.nextToken();

            if (rank.equals("P")) continue;

            // 학점 합
            scoreSum += score;

            majorSum += score * map.get(rank);
        }

        System.out.println(majorSum / scoreSum);

        bw.flush();
        bw.close();
        br.close();
    }
}