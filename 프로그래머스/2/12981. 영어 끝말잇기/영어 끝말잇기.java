import java.util.*;
class Solution {
    static int len;
    public int[] solution(int n, String[] words) {
        int[] answer = new int[2];
        len = words.length;
        
        Set<String> set = new HashSet<>();
        set.add(words[0]);
        
        for (int i = 1; i < len; i++){
            String str = words[i];
            // 같은 단어를 말하면 탈락 // 끝말잇기가 안됨.
            if (set.contains(str) || words[i - 1].charAt(words[i - 1].length() - 1) !=
                    words[i].charAt(0)){
                answer[0] = i % n + 1; // 1 ~ n
                answer[1] = i / n + 1; // 1 ~ times
                return answer;
            }
            // 일단 set에 넣어야함. 이거 왜 안써줌; 아니 테케 어케통과한거임;
            set.add(str);
        }
        return answer;
    }
}