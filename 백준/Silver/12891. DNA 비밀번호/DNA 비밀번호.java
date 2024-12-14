import java.io.*;
import java.util.*;

public class Main {
    static int S, P;
    static String str;
    static int A, C, G, T;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        S = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());

        str = br.readLine();

        st = new StringTokenizer(br.readLine());

        Map<Character, Integer> originalMap = new HashMap<>();

        originalMap.put('A', Integer.parseInt(st.nextToken())); // ACGT
        originalMap.put('C', Integer.parseInt(st.nextToken()));
        originalMap.put('G', Integer.parseInt(st.nextToken()));
        originalMap.put('T', Integer.parseInt(st.nextToken()));


        int answer = 0;

        Map<Character, Integer> curMap = new HashMap<>();
        curMap.put('A', 0); // ACGT
        curMap.put('C', 0);
        curMap.put('G', 0);
        curMap.put('T', 0);
        // S-P까지만
        int len = 0;
        for (int i = 0; i < S; i++) {
            if (str.charAt(i) == 'A' || str.charAt(i) == 'C' || str.charAt(i) == 'G'
                    || str.charAt(i) == 'T') {
                curMap.put(str.charAt(i), curMap.get(str.charAt(i)) + 1);
                len++;
            }

            if (len == P) {
                boolean isOk = true;
                for (Map.Entry<Character, Integer> entry : curMap.entrySet()) {
                    char c = entry.getKey();
                    if (originalMap.get(c) > entry.getValue()) {
                        isOk = false;
                        break;
                    }
                }
                if (isOk) answer++;

                len -= 1;
                int idx = i - (P - 1);
                curMap.put(str.charAt(idx), curMap.get(str.charAt(idx)) - 1);
            }
        }

        System.out.println(answer);

        bw.flush();
        br.close();
        bw.close();
    }
}