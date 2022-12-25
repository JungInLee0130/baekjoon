import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());
            int[] voteNum = new int[N + 1]; // 1 ~ N
            int maxVote = 0;
            int maxVoteIndex = 0;
            int totalNum = 0;
            int totalCount = 0;
            for (int i = 1; i <= N; i++) {
                voteNum[i] = Integer.parseInt(br.readLine());
                totalNum += voteNum[i];
                maxVote = Math.max(maxVote, voteNum[i]);
            }
            for (int i = 1; i <= N; i++) {
                if (maxVote == voteNum[i]) {
                    maxVoteIndex = i;
                    totalCount++;
                }
            }
            // 정수 / 정수 = 정수
            // double로 캐스팅
            double rate = (double)maxVote / totalNum;
            if (totalCount >= 2) {
                bw.write(String.valueOf("no winner\n"));
            } else if (rate <= 0.5) {
                bw.write(String.valueOf("minority winner " + String.valueOf(maxVoteIndex) + "\n"));
            } else {
                bw.write(String.valueOf("majority winner " + String.valueOf(maxVoteIndex) + "\n"));
            }
            // 과반수 이상이면 개인투표수 / 모든 투표수 > 0.5 -> count
            // count >= 2 || maxCount >= 2 -> no winner
            // count == 1 || max  -> majority winner voteNum[i](max)
            // count == 0 -> minority winner voteNum[i](max)
        }


        bw.flush();
        br.close();
        bw.close();
    }
}
