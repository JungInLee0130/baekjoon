import java.util.*;

class Solution
{
    static int N;
    public int solution(String s)
    {
        N = s.length();
        
        // 한자릿수
        if (N == 1) return 1;
        
        // 두자릿수
        if (N == 2){
            if (s.charAt(0) == s.charAt(1)){
                return 2; // 같으면 2
            }
            return 1; // 다르면 1... 테케 18번임
        }
        
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < N; i++){
            sb.append(s.charAt(i));
        }
        
        
        int max = 1;
        
        // 1. aba <- 이런 케이스
        for (int i = 1; i <= N - 2; i++){
            int leftIdx = i - 1;
            int rightIdx = i + 1;

            int len = 1;

            while (true){
                if (sb.charAt(leftIdx) == sb.charAt(rightIdx)){
                    leftIdx--;
                    rightIdx++;
                    len += 2;
                    if (leftIdx < 0 || rightIdx > N - 1){
                        break;
                    }
                }
                else{
                    break;
                }
            }
            if (len > 1){
                max = Math.max(len, max);
            }
        }

        // 2. 1번케이스를 두번결합 abaaba or abba 같은 경우
        for (int i = 1; i <= N - 2; i++){
            int leftIdx = i;
            int rightIdx = i + 1;
            int len = 0;


            while (true){
                if (sb.charAt(leftIdx) == sb.charAt(rightIdx)){
                    len += 2;
                    leftIdx--;
                    rightIdx++;
                    if (leftIdx < 0 || rightIdx > N - 1){
                        break;
                    }
                }
                else{
                    break;
                }
            }
            if (len > 1){
                max = Math.max(max, len);
            }
        }
        
        
        // 양쪽끝에 존재하는 경우
        if (sb.charAt(0) == sb.charAt(1)){
            max = Math.max(max, 2);
        }
        
        if (sb.charAt(N - 2) == sb.charAt(N - 1)){
            max = Math.max(max, 2);
        }
        
        return max;
    }
}