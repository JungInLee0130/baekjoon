import java.io.*;
import java.util.*;

public class Main {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        HashSet<String> hashSet = new HashSet<>();
        for (int i = 0; i < N; i++) {
            String str = br.readLine();

            String[] sp = str.split(" ");

            if (sp[1].equals("enter")) {
                hashSet.add(sp[0]);
            } else if (sp[1].equals("leave")) {
                if (hashSet.contains(sp[0])) {
                    hashSet.remove(sp[0]);
                }
            }
        }

        List<String> list = new ArrayList<>();
        for (String str:hashSet) {
            list.add(str);
        }
        Collections.sort(list, Collections.reverseOrder());

        for (String s : list) {
            System.out.println(s);
        }
    }
}