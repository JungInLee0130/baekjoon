import java.util.*;

class Solution {
    static int N;
    static int[] Stones;
    static int K;
    public int solution(int[] stones, int k) {
        N = stones.length;
        Stones = stones;
        K = k;

        // 2억명 가능할듯
        int low = 1;
        int high = 200_000_000;

        int answer = 1;
        while (low <= high){
            int mid = (low + high) / 2;

            boolean isPossible = possibleToGo(mid);

            if (isPossible){
                answer = mid;
                low = mid + 1;
            }
            else {
                high = mid - 1;
            }
        }

        return answer;
    }

    static boolean possibleToGo(int num){
        int[] copyStone = Arrays.copyOf(Stones, Stones.length);

        for (int i = 0; i < copyStone.length; i++){
            copyStone[i] -= num;
        }

        int idx = 0;
        int over = 1;

        while (idx < copyStone.length){
            if (copyStone[idx] < 0){ // 0이거나 음수 : 이동불가, skip++
                over++;
            }
            // 양수 : 이동가능
            else{
                over = 1; // 초기화
            }
            if (over > K){
                return false;
            }
            idx++;
        }

        return true;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int[] stones = {2, 4, 5, 3, 2, 1, 4, 2, 5, 1};
        int k = 3;
        System.out.println(s.solution(stones, k));
    }
}