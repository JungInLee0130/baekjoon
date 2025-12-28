import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String sound = br.readLine();

        if (sound.length() % 5 != 0) {
            System.out.println(-1);
            return;
        }

        int count = 0;

        boolean[] visited = new boolean[sound.length()];
        char[] letters = {'q', 'u', 'a', 'c', 'k'};
        int letterIdx = 0;

        for (int k = 0; k < sound.length(); k++) {  // 2500
            if (sound.charAt(k) == 'q') {
                letterIdx = 0;
                boolean isDuck = false;
                // q 시작점부터 끝까지
                for (int j = k; j < sound.length(); j++) { // 2500
                    if (sound.charAt(j) == letters[letterIdx]) {
                        if (visited[j]) continue;
                        visited[j] = true;
                        letterIdx += 1;
                        if (letterIdx >= 5) {
                            isDuck = true;
                            letterIdx = letterIdx % 5;
                        }
                    }
                }
                if (isDuck && letterIdx == 0) {
                    count += 1;
                    isDuck = false;
                }
            }
        }

        for (int i = 0; i < sound.length(); i++) {
            if (!visited[i]) {
                count = -1;
                break;
            }
        }

        if (count >= 1) {
            System.out.println(count);
        } else {
            System.out.println(-1);
        }



        br.close();
    }
}