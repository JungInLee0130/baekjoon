import java.util.*;

class Solution {
    public int solution(String[] words) {
        int answer = 0;
        
        Arrays.sort(words);
        
        int[] counts = new int[words.length];
        
        for (int i = 0; i < words.length - 1; i++){
            String pre = words[i];
            String next = words[i + 1];
            
            int len = Math.min(pre.length(), next.length());
            
            int sameCount = getSameCount(pre, next, len);
            
            if (sameCount == len){
                counts[i] = Math.max(sameCount, counts[i]);
            }
            else{
                counts[i] = Math.max(sameCount + 1, counts[i]);
            }
            counts[i + 1] = Math.max(sameCount + 1, counts[i + 1]);
        }
        
        for (int e : counts){
            answer += e;
        }
        
        
        return answer;
    }
    
    static int getSameCount(String pre, String next, int len){
        int count = 0;
        for (int i = 0; i < len; i++){
            if (pre.charAt(i) == next.charAt(i)){
                count++;
                continue;
            }
            break;
        }
        return count;
    }
}