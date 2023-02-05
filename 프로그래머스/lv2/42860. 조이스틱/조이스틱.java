
import java.util.Arrays;
class Solution {
private static int N;
    private static char[] nameArray;
    private static char[] origin;
    private static int min;
    public int solution(String name) {
        N = name.length();

        // 처음 배열 선언
        origin = new char[N];
        Arrays.fill(origin, 'A');

        nameArray = name.toCharArray();
        /*
        * 1. 위 - 다음 알파벳
          2. 아래 - 이전 알파벳
          3. 왼쪽 - 왼쪽으로
          4. 오른쪽 - 오른쪽으로
          * */

        // first
        int move = N - 1; // moveCount
        int change = 0;
        int index = 0;
        for (int i = 0; i < N; i++) {
            // 상하
            change += Math.min(nameArray[i] - 'A', 'Z' - nameArray[i] + 1);

            index = i + 1;

            while (index < N && nameArray[index] == 'A') {
                index++;
            }

            move = Math.min(move, i * 2 + N - index);
            move = Math.min(move, (N - index) * 2 + i); 
        }

        /*origin[0] = nameArray[0];
        // 어느쪽으로든 이동을 안할수도있네?
        int index = check();
        if (index != N) { // 같은지 체크
            return count;
        }
        // 1.
        count++;
        int original = count;
        for (int i = 1; i < index; ++i) { // 2번째부터
            // 위 아래 결정
            if ('A' <= nameArray[i] && nameArray[i] <= 'M') { // 아래쪽 사용
                count += nameArray[i] - 'A';
            } else if ('O' <= nameArray[i] && nameArray[i] <= 'Z') { // 위쪽 사용
                count += 'Z' - nameArray[i] + 1;
            } else { // N의 경우
                count += 13;
            }
            origin[i] = nameArray[i];
            // 같은지 체크
            // 옆으로 이동
            count++;
        }

        int secondIndex = check();
        if (secondIndex != N) {

        }
        
        min = count;

        // 2.
        count = original;
        // 기존 초기화
        Arrays.fill(origin, 'A');
        origin[0] = nameArray[0];

        for (int i = N - 1; i >= 1; i--) {
            // 위 아래 결정
            if ('A' <= nameArray[i] && nameArray[i] <= 'M') { // 아래쪽 사용
                count += nameArray[i] - 'A';
            } else if ('O' <= nameArray[i] && nameArray[i] <= 'Z') {
                count += 'Z' - nameArray[i] + 1;
            } else { // N의 경우
                count += 13;
            }
            origin[i] = nameArray[i];
            if (check()) {
                break;
            }
            // 옆으로 이동
            count++;
        }

        min = Math.min(count, min);*/

        return change + move;
    }
}