import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        HashMap<Integer, String> pokemonMap = new HashMap<>();
        HashMap<String, Integer> pokemonMapName = new HashMap<>();

        // 1. N개의 포켓몬 이름 입력.
        for (int i = 0; i < N; i++) {
            String name = br.readLine();
            pokemonMap.put(i + 1, name);
            pokemonMapName.put(name, i + 1);
        }

        for (int i = 0; i < M; i++) {
            String question = br.readLine();

            // 숫자인지 이름인지 판별
            // 첫번째 문자가 숫자면: key -> value
            if (question.charAt(0) >= '1' && question.charAt(0) <= '9') {
                int numQuestion = Integer.parseInt(question);

                String questionName = pokemonMap.get(numQuestion);

                bw.write(questionName + "\n");
            } else { // 첫번째 문자가 문자: value -> key
                // value가 question인 것 찾기
                int num = pokemonMapName.get(question);

                bw.write(String.valueOf(num) + "\n");
            }
        }

        // 해쉬맵

        bw.flush();
        br.close();
        bw.close();
    }
}
