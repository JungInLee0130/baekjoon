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

        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < L; i++) {
            String str = br.readLine();
            // 계속 갱신됨.
            map.put(str, i);
        }

        // value를 오름차순으로 정렬
        List<String> keySet = new ArrayList<>(map.keySet());

        keySet.sort(new Comparator<String>() {
            @Override
            public int compare(String ia, String ib) {
                return map.get(ia).compareTo(map.get(ib));
            }
        });

        /*for (Integer e:keySet) {
            System.out.println(e + " : " + map.get(e));
        }*/

        for (String s:keySet) {
            K--;
            System.out.println(s);
            if (K == 0) {
                break;
            }
        }
    }
}