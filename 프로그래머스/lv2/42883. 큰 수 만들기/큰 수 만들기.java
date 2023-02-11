import java.util.ArrayList;
class Solution {
    public String solution(String number, int k) {
 int len = number.length();
        StringBuilder sb = new StringBuilder();

        int maxIndex = 0;
        int max = 0;
        for (int i = 0; i < len - k; i++) { // 0 ~ 5까지만 뽑음. 최대값만
            max = 0;
            for (int j = maxIndex; j <= k + i; j++) { // len - k의 범위 내에서 최대값만 저장하여 넣는 방식
                if (max < number.charAt(j) - '0') {
                    max = number.charAt(j) - '0';
                    maxIndex = j + 1;
                }
            }
            sb.append(max);
        }

        return sb.toString();
    }
}