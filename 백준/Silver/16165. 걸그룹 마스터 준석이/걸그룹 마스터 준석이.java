import java.io.*;
import java.util.*;

public class Main {
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        HashMap<String, HashSet<String>> teamsMap = new HashMap<>();
        for (int i = 0; i < N; i++) {
            String teamName = br.readLine(); // 팀이름
            int len = Integer.parseInt(br.readLine());

            HashSet<String> set = new HashSet<>();
            for (int j = 0; j < len; j++) {
                String name = br.readLine();
                set.add(name);
            }

            teamsMap.put(teamName, set); // 이렇게 만듬. 그리고 돌아가면서 contains하면됨. 아니근데 멤버 최대 몇명인지 모르는거아닌가....
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            String str = br.readLine();
            int num = Integer.parseInt(br.readLine());

            if (num == 1) { // 개인
                for (Map.Entry<String,HashSet<String>> e : teamsMap.entrySet()) {
                    HashSet<String> values = e.getValue();

                    if (values.contains(str)) {
                        sb.append(e.getKey() + "\n"); // teamname 저장
                        break;
                    }
                }
            }
            else{
                // 0이면 teanName
                HashSet<String> hashSet = teamsMap.get(str);

                List<String> list = new ArrayList<>();
                for (String s:hashSet) {
                    list.add(s);
                }

                Collections.sort(list);

                for (String s:list) {
                    sb.append(s + "\n");
                }
            }
        }

        bw.write(sb.toString());

        bw.flush();
        bw.close();
        br.close();
    }
}