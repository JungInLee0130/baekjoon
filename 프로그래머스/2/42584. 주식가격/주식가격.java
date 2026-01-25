import java.util.*;

/**
1. 수도코드 작성했는가?
(현재 시간) - (시점 시간)
초 단위로 기록된 주식가격 prices, 가격이 떨어지지 않은 기간 몇 초?
이전 가격들이 다 자신보다 작거나 같으면 +1
else 그대로
int[] time = new int[N];
stack.push(0); // 시간 push
for (int i = 1; i < N; i++) {
    // 가격이 스택에 있는게 크면
    while (prices[stack.peek()] > prices[i] && !stack.isEmpty()) {
        // 현재 시간 - 스택 시간
        int stackTime = stack.pop();
        time[stackTime] = i - stackTime; // 스택 시간 기록
    }
    stack.push(i); // 시간 push
}

int totalTime = N - 1; // 전체 시간 (0~N-1까지니 그냥 시간하고 같다)
while (!stack.isEmpty()) {
    int stackTime = stack.pop();
    time[stackTime] = totalTime - stackTime;
}

return time;

stack.push
1,2,3 -> push push push
1,2,3, 2 -> pop 1 and push
1,2,2,3 -> [4,3,2,1]


2. 데이터의 흐름 그렸는가?
[1,2,3,2,3]
[0,1,0,0,0]
[1,0,0,0,0]
[2,1,0,0,0]
[3,2,1,0,0]
[4,3,1,1,0]
// 0 ~ n - 1

[3,4,2,1,6]
[1,0,0,0,0]
[2,1,0,0,0]
[2,1,1,0,0]
[2,1,1,1,0]
3. 제약조건을 보고 유형매핑을 했는가?
1 <= prices <= 10000, 2 <= prices.lengnth <= 100000
2 -> 1
3 -> 1
100_000 -> 99_999
-> 1~ 99_999 까지의 합 : 1 + N / 2
-> 100000 / 2 = 50000
-> 9_999_900_000 / 2 = 약 48억

10_000_000_000 = 100억 : 이중반복 x

그리디?

4. 디버그 없이 코딩했는가?

5. 메인로직을 함수화했는가?

6. 자주 틀리는 실수는 카테고리화 했는가?
몰랐던것 : (전체 시간) - (현재 인덱스)
전체 시간을 기준으로 빼면된다는걸 몰랐음.
- 답봄.
**/

class Solution {
    public int[] solution(int[] prices) {
        int N = prices.length;
        int[] time = new int[N];
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            // 가격이 스택에 있는게 크면
            while (!stack.isEmpty() && prices[stack.peek()] > prices[i]) {
                // 현재 시간 - 스택 시간
                recordStackTime(stack, time, i);  // 스택 시간 기록
            }
            stack.push(i); // 시간 push
        }

        while (!stack.isEmpty()) {
            // 전체 시간 (0~N-1까지니 그냥 시간하고 같다)
            recordStackTime(stack, time, N - 1);
        }

        return time;
    }
    private void recordStackTime(Deque<Integer> stack, int[] time, int curTime) {
        int curIndex = stack.pop();
        time[curIndex] = curTime - curIndex;
    }
}