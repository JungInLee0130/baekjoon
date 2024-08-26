import java.io.*;
import java.util.*;

public class Main {
    static int K, L;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        K = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        LinkedHashSet<String> linkedSet = new LinkedHashSet<>();
        for (int i = 0; i < L; i++) {
            String str = br.readLine();
            // 계속 갱신됨.
            if (linkedSet.contains(str)) {
                linkedSet.remove(str);
            }
            linkedSet.add(str);
        }

        for (String s:linkedSet) {
            K--;
            System.out.println(s);
            if (K == 0) {
                break;
            }
        }
    }
}