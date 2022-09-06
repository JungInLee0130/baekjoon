import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Set<String> NoHear = new HashSet<>();
        Set<String> NoSee = new HashSet<>();
        for (int i = 0; i < N; i++) {
            NoHear.add(br.readLine());
        }

        for (int i = 0; i < M; i++) {
            NoSee.add(br.readLine());
        }

        int count = 0;

        ArrayList<String> NoHearAndSee = new ArrayList<>();

        for (String name: NoHear) {
            if (NoSee.contains(name)) {
                count++;
                NoHearAndSee.add(name);
            }
        }

        bw.write(String.valueOf(count) + "\n");
        Collections.sort(NoHearAndSee);
        for (String name: NoHearAndSee) {
            bw.write(name + "\n");
        }
        bw.flush();
        br.close();
        bw.close();
    }
}
