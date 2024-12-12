import java.util.*;
class Solution {
    public int solution(int N, int number) {
        
        if (N == number) return 1;
        // 통만들기
        List<HashSet<Integer>> list = new ArrayList<>();
        // 만들수있는 모든 수를 다담는거임.
        // 8보다 크면 -1을 반환하므로 통을 8개만 만들면됨.
        
        for (int i = 0; i <= 8; i++){
            list.add(new HashSet<>());
        }
        
        list.get(1).add(N); // 1개로는 N밖에 못만듬.
        
        for (int i = 2; i <= 8; i++){
            HashSet<Integer> numbers = list.get(i);
            
            // 2 = 1 + 1, 2 * N
            // 3 = 1 + 2, 2 + 1, 3 * N
            // ...
            // 8 = 1 + 7, 2 + 6, 3 + 5, 4 + 4 ..., 7 + 1, 8 * N
            for (int j = 1; j < i; j++){
                HashSet<Integer> setA = list.get(j);
                HashSet<Integer> setB = list.get(i - j);
                
                // n
                for(Integer a :setA){
                    for (Integer b : setB){
                        
                        int plus = a + b;
                        int minus = a - b;
                        int mul = a * b;
                        
                        if (plus <= 32000){
                            numbers.add(plus);    
                        }
                        
                        numbers.add(minus);
                        
                        if (mul <= 32000){
                            numbers.add(mul);
                        }
                        
                        if (a != 0 && b != 0){
                          numbers.add(a / b);  
                        } 
                    }
                }
                
                // i개 이어붙힌거
                StringBuilder sb = new StringBuilder();
                
                for (int k = 0; k < i; k++){
                    sb.append(N);
                }
                
                int num = Integer.parseInt(sb.toString());
                if (num <= 32000) numbers.add(num);
            }
            
            if (numbers.contains(number)){
                return i;
            }
        }
        
        
        return -1;
    }
}