import java.util.*;
class Solution {
    static String[] answer;
    static int sLen;
    static String oneOneZero = "110";
    public String[] solution(String[] s) {
        sLen = s.length;
        answer = new String[sLen];
        
        for (int i = 0; i < sLen; i++){
            String str = s[i];
            
            List<Integer> idxs = new ArrayList<>();
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < str.length(); j++){
                sb.append(str.charAt(j));
            }
            
            // 110 전부 추출
            int oneOneZeroCount = 0;
            int len = sb.length();
            
            int count = 1;
            while (count != 0){
                len = sb.length();
                count = 0;
                for (int j = len - 1; j >= 0; j--){
                    if (sb.length() - 2 <= j) continue;
                    if (sb.charAt(j) == '1' 
                        && sb.charAt(j + 1) == '1' 
                        && sb.charAt(j + 2) == '0'){ // 110일때
                        
                        
                        
                            sb.delete(j, j + 3); // 삭제    
                        
                        count++;
                    }
                }
                oneOneZeroCount += count;
                if (count == 0){
                    break;
                }
            }
            
            //System.out.println(sb);
            // sb : 110 모두뺌

            // 0이 나오는곳 뒤에 모두 대입.
            // 0이 없으면 자동으로 맨앞으로 이동됨.
            int zeroCount = 0;
            for (int j = 0; j < sb.length(); j++){
                if (sb.charAt(j) == '0'){
                    zeroCount++;
                }
            }
            
            if (zeroCount == 0 && oneOneZeroCount > 0){
                oneOneZeroCount--;
                sb.insert(0, "110");
            }
            
            while (oneOneZeroCount > 0){
                len = sb.length();
                for (int j = len - 1; j >= 0; j--){
                    if (sb.charAt(j) == '0'){
                        sb.insert(++j, "110");
                        //System.out.println(sb);
                        oneOneZeroCount--;
                        if (oneOneZeroCount == 0){
                            break;
                        }
                    }
                }
            }
            
            
            
            // System.out.println(e + " ");
            //System.out.println("end:" + sb);
            answer[i] = sb.toString();
        }
        
        
        
        return answer;
    }
}