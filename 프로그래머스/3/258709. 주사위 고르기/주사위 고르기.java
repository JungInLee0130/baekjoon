import java.util.*;
class Solution {
    static int n = 0;
    public int[] solution(int[][] dice) {
        int[] answer = {};
        
        n = dice.length; // n개의 주사위를 가지고함. 2의 배수
        
        for (int i = 0; i < n; i++){
            Arrays.sort(dice[i]);
        }
        
        // n / 2개씩 나눔.
        
        // (A의값) > (b의값) 의 확률이 최대가되는 주사위들 고르는 경우의수
        
        selected = new boolean[n];
        combination(0, 0, dice);
        
        Collections.sort(answers);
        
        answer = new int[n / 2];
        
        for (int i = 0; i < n / 2; i++){
            System.out.println(answers.get(0).count);
            answer[i] = answers.get(0).A[i] + 1;
        }
        
        
        return answer;
    }
    
    private static boolean[] selected;
    private static int[] A;
    private static int[] B;
    
    static class Point implements Comparable<Point>{
        int A[];
        int B[];
        
        int count;
        
        public Point(int[] A, int[] B, int count){
            this.A = A;
            this.B = B;
            this.count = count;
        }
        
        @Override
        public int compareTo(Point p){
            return p.count - this.count; // 내림차순
        }
    }
    
    private static List<Point> answers = new ArrayList<>();
    
    private static void combination(int start, int cnt, int[][] dice){
        if (cnt == n / 2){
            int k = 0;
            int s = 0;
            A = new int[n / 2];
            B = new int[n / 2];
            
            for (int i = 0; i < n; i++){
                if (selected[i]) A[k++] = i;
                else B[s++] = i;
            }
            
            // 나누기 끝
            // 확률 구하기
            int count = calPercent(dice);
            
            answers.add(new Point(A, B, count));
            return;
        }
        
        for (int i = start; i < n; i++){
            selected[i] = true;
            combination(i + 1, cnt + 1, dice);
            selected[i] = false;
        }
    }
    
    
    private static List<Integer> ASums;
    private static List<Integer> BSums;
    
    
    private static int calPercent(int[][] dice){
        // B의 합 구하기
        BSums = new ArrayList<>();
        // B : B의 dice가 들어있음
        cal(0, 0, B, BSums, dice);    
        
        
        
        // A의 합 구하기
        ASums = new ArrayList<>();
        cal(0,0, A, ASums, dice);    
        
        
        
        // 정렬
        Collections.sort(BSums);
        Collections.sort(ASums);
        
        // 승리 갯수 구하기
        int count = 0;
        for (int i = 0; i < ASums.size(); i++){
            int idx = binarySearch(BSums, ASums.get(i));
            count += idx + 1;
        }
        
        return count;
    }
    
    private static void cal(int sum, int cnt, int[] arr, List<Integer> list
                            , int[][] dice){
        if (cnt == n / 2){
            list.add(sum);
            return;
        }
        
        // dice 눈금
        for (int i = 0; i < 6; i++){
            cal(sum + dice[arr[cnt]][i], cnt + 1, arr, list, dice);
        }
    }
    
    private static int binarySearch(List<Integer> list, int score){
        int left = 0; // 0 idx
        int right = list.size() - 1; // size() - 1 idx
        int result = -1;
        
        while (left <= right)
        {
            int mid = (left + right) / 2;
            
            if (list.get(mid) >= score){
                right = mid - 1;
            }
            else{
                result = mid;
                left = mid + 1;
            }
        }
        
        return result;
    }
}