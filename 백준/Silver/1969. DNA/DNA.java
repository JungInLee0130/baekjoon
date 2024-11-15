import org.w3c.dom.Node;

import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    static int N, M;
    static List<String> list = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            String str = br.readLine();

            list.add(str);
        }

        Collections.sort(list);

        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < M; j++) {

            Map<Character, Integer> map = new HashMap<>();

            for (int i = 0; i < N; i++) {
                char c = list.get(i).charAt(j); // 문자하나

                map.put(c, map.getOrDefault(c, 0) + 1);
            }

            char alphabet = 'a';
            int max = 0;
            for (Map.Entry<Character, Integer> entry : map.entrySet()) {
                if (max < entry.getValue()
                        || (max == entry.getValue() && entry.getKey() - alphabet < 0)){
                    max = entry.getValue();
                    alphabet = entry.getKey();
                }
            }

            sb.append(alphabet);
        }

        int count = 0;
        for (int i = 0; i < N; i++) {
            String str = list.get(i);
            for (int j = 0; j < M; j++) {
                if (sb.toString().charAt(j) != str.charAt(j)) {
                    count++;
                }
            }
        }

        System.out.println(sb.toString());
        System.out.println(count);

        bw.flush();
        bw.close();
        br.close();
    }
}