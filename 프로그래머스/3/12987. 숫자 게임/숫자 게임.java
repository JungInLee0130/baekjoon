import java.util.*;
class Solution {
    static ArrayList<Integer> AWrapper;
    static ArrayList<Integer> BWrapper;
    public int solution(int[] A, int[] B) {
        int answer = 0;
        
        AWrapper = new ArrayList<>();
        BWrapper = new ArrayList<>();
        
        for(int e : A){
            AWrapper.add(e);
        }
        
        for (int e : B){
            BWrapper.add(e);
        }
        
        Collections.sort(AWrapper, Collections.reverseOrder());
        Collections.sort(BWrapper, Collections.reverseOrder());
        
        int N = AWrapper.size();
        int AIdx = 0;
        int BIdx = 0;
        while (AIdx < N && BIdx < N){
            int a = AWrapper.get(AIdx);
            int b = BWrapper.get(BIdx);
            
            if (a < b){
                AIdx++;
                BIdx++;
                answer++;
            }
            else{
                AIdx++;
            }
        }
        
        return answer;
    }
}