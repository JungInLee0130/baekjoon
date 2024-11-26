class Solution {
    static int N;
    public int solution(int[] a) {
        
        N = a.length;
        
        if (N == 1) return 1;
        
        // 1. 최소값 배열 저장(각 지점별)
        // 여기서 안되는 경우는 '작큰작' 이것밖에 없음.
        // 따라서 특정 인덱스 좌우에서 최소값 해당값 최소값이되는 경우가 안되는거
        // 제거하다보면 최소값밖에 안남음.
        int[] leftMins = new int[N];
        int[] rightMins = new int[N];
        
        int minLeft = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++){
            minLeft = Math.min(minLeft, a[i]);
            leftMins[i] = minLeft;
        }
        
        int minRight = Integer.MAX_VALUE;
        for (int i = N - 1; i >= 0; i--){
            minRight = Math.min(minRight, a[i]);
            rightMins[i] = minRight;
        }
        
        
        // 양쪽은 무조건 가능.
        int answer = 2;
        for (int i = 1; i <= N - 2; i++){
            if (a[i] > leftMins[i - 1] && a[i] > rightMins[i + 1]) continue;
            answer++;
        }
        return answer;
    }
}