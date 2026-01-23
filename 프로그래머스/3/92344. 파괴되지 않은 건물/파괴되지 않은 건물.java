/**
1. 수도코드 작성했는가?                  o
2. 데이터의 흐름 그렸는가?             
3. 제약조건을 보고 유형매핑을 했는가?      o
4. 디버그 없이 코딩했는가?               실행횟수 : 1번   
5. 메인로직을 함수화했는가?
6. 자주 틀리는 실수는 카테고리화 했는가?
**/
/**
- 제약 조건 : 2차원 배열 누적합
  1. N, M -> 1000 이하 이차원배열
  2. 데이터가 25만개. 25만개를 다 순회하면서 시뮬레이션값을 찍을수없음.
  3. 시간복잡도 1000 * 1000 = 1_000_000 * 250_000 = 250_000_000_000 (2500억)
  4. 2차원 배열 누적합 : 단, 누적합 지점만 표시하는 유형
  - 끝점에 누적합 끝을 표시한다음. 한번에 더한다.
  - 시간복잡도 : 25만 따로 배열순회 1백만 따로
  5. degree도 최대 5억

0. dp라는 누적합 배열 생성 : int[][] dp = new int[N + 1][M + 1]; // 끝지점 연장해둔다.
1. 250000개의 데이터 순회
1-1. 공격인가 회복인가 ? int degree = skill[i][0] == 1 ? -degree : degree
1-2. 끝점 표시
dp[r1][c1] += degree
dp[r2 + 1][c1] -= degree
dp[r1][c2 + 1] -= degree
dp[r2 + 1][c2 + 1] += degree
2. 열순회
for (int r = 0; r <= N; r++) {
    for (int c = 1; c <= M; c++) {
        dp[r][c] += dp[r][c - 1]; // 이전 열 모두 더함
    }
}
3. 행순회
for (int c = 0; c <= M; c++) {
    for (int r = 1; r <= N; r++) {
        dp[r][c] += dp[r - 1][c]; // 이전 행 모두 더함
    }
}
4. 누적합 배열완성
//누락 1
5. 결과 카운트 : > 0 포탑만 카운트
int count = 0;
for (int r = 0; r <= N - 1; r++) {
    for (int c = 0; c <= M - 1; c++) {
        if (dp[r][c] > 0) {
            count += 1;
        }
    }
}
6. 리턴
**/
class Solution {
    public int solution(int[][] board, int[][] skill) {
        //3. dp라는 누적합 배열 생성 : 선언이라 위에 둔다.
        int N = board.length;
        int M = board[0].length;
        int[][] dp = new int[N + 1][M + 1];     // 끝지점 연장해둔다.
        //1. 250000개의 데이터 순회
        for (int i = 0; i < skill.length; i++) {
            //2. 공격인가 회복인가 ?
            int degree = skill[i][5];
            // 재선언
            degree = skill[i][0] == 1 ? -degree : degree;
            //4. 끝점 표시
            int r1 = skill[i][1];
            int c1 = skill[i][2];
            int r2 = skill[i][3];
            int c2 = skill[i][4];
            
            // 좌표는 (0,0) 부터
            dp[r1][c1] += degree;
            dp[r2 + 1][c1] -= degree;
            dp[r1][c2 + 1] -= degree;
            dp[r2 + 1][c2 + 1] += degree;    
        }  
        
        //5. 열순회
        for (int r = 0; r <= N; r++) {
            for (int c = 1; c <= M; c++) {
                dp[r][c] += dp[r][c - 1]; // 이전 열 모두 더함
            }
        }
        //6. 행순회
        for (int c = 0; c <= M; c++) {
            for (int r = 1; r <= N; r++) {
                dp[r][c] += dp[r - 1][c]; // 이전 행 모두 더함
            }
        }
        //7. 누적합 배열완성
        //누락 1 : 8. 누적합 배열 + board 배열
        //9. 결과 카운트 : > 0 포탑만 카운트
        int count = 0;
        for (int r = 0; r <= N - 1; r ++) {
            // 실수1 : M -> N으로 작성 : R,C 라고 아예 적을 필요성 느낌.
            for (int c = 0; c <= M - 1; c++) {  
                dp[r][c] = dp[r][c] + board[r][c];
                if (dp[r][c] > 0) {
                    count += 1;
                }
            }     
        }
        
        return count;
    }
}