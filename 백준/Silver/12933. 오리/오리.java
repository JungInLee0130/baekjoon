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

        int len = sound.length();
        int count = 0;

        boolean[] visited = new boolean[len];
        char[] letters = {'q', 'u', 'a', 'c', 'k'};
        int letterIdx = 0;

        List<Integer> qIndex = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            if (sound.charAt(i) == 'q') {
                qIndex.add(i);
            }
        }

        for (int i = 0; i < qIndex.size(); i++)
        {
            int index = qIndex.get(i);
            letterIdx = 0;
            boolean isDuck = false;
            for (int j = index; j < sound.length(); j++) {
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
            if (isDuck) {
                count += 1;
                isDuck = false;
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