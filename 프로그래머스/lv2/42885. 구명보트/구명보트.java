import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
class Solution {
    public int solution(int[] people, int limit) {
          int len = people.length;
         Arrays.sort(people);


        int count = 0;
        int sum = 0;
        int start = 0;
        int end = len - 1;
        while (start <= end){
            sum = people[end] + people[start];
            if (sum > limit) {
                end--;
                count++;
            } else if (sum <= limit) {
                start++;
                end--;
                count++;
            }
        }
        
        return count;
    }
}