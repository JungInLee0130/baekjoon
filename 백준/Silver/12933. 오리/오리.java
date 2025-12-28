import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String sound = br.readLine();

        // 예외 1. 5로 나누어떨어지지않는경우
        if (sound.length() % 5 != 0) {
            System.out.println(-1);
            return;
        }

        int[] counts = new int[5];  // 오리 상태 배열
        int totalDucks = 0;

        for (char c : sound.toCharArray()) {    // sound 배열 순회
            int idx = "quack".indexOf(c);   // 찾지 못했을경우 -1 반환

            // 'q'
            if (idx == 0) {
                if (counts[4] > 0) counts[4]--; // 이미 완주한 duck이 있을경우 소비
                else totalDucks++; // 완주한다는 가정하에
                counts[0]++;
            } else {
                if (counts[idx - 1] == 0) { // 예외 2. 이전 duck이 없을경우.
                    System.out.println(-1);
                    return;
                }
                counts[idx - 1]--;  // 이전 duck - 1, 현재 duck + 1
                counts[idx]++;
            }
        }

        // 3. 합했을때 모든 이전 duck이 1이 있는 경우 
        if (counts[0] + counts[1] + counts[2] + counts[3] > 0) {
            System.out.println(-1);
        } else {
            System.out.println(totalDucks == 0 ? -1 : totalDucks);
        }


        br.close();
    }
}