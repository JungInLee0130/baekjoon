import java.util.*;

class Solution {
    static Map<String, String> parent = new HashMap<>();
    static Map<String, Integer> money = new HashMap<>();
    
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        for (int i = 0; i < enroll.length; i++){
            parent.put(enroll[i], referral[i]); // 자식, 부모
        }
        
        for (int i = 0; i < seller.length; i++){
            share(seller[i], amount[i] * 100);
        }
        
        int[] result = new int[enroll.length];
        for (int i = 0; i < enroll.length; i++){
            result[i] = money.getOrDefault(enroll[i], 0);
        }
        
        return result;
    }
    
    void share(String node, int sales){
        int nextSale = sales/10;
        
        // 초기값 : amounts[i] 의 90%
        money.put(node, money.getOrDefault(node, 0) + sales - nextSale);
        
        // 부모가 있고, nextSale이 남았으면
        /* 
        트리를 사용하는 줄 알았는데, 사용하지않고, parent에 node를 따로 저장하면서
        재귀함수를 사용한다.
        */
        if (nextSale > 0 && parent.containsKey(node)){
            share(parent.get(node), nextSale);
        }
    }
}