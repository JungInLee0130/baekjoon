import java.io.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static int[] priorityQueue;

    public static void swap(int index1, int index2) {
        int temp = priorityQueue[index1];

        priorityQueue[index1] = priorityQueue[index2];
        priorityQueue[index2] = temp;;
    }

    private static void prioritySort(int pointer) {
        int rootNodeIndex = pointer / 2;

        if (pointer == 1) { // 포인터가 루트노드 일때
            return;
        }

        if (priorityQueue[rootNodeIndex] > priorityQueue[pointer]) { // 부모노드가 더크면 스왑
            swap(rootNodeIndex, pointer);
            prioritySort(rootNodeIndex);
        }
    }

    public static void pop(int pointer, int len) {
        int leftNodeIndex = pointer * 2;
        int rightNodeIndex = pointer * 2 + 1;

        if (pointer > len) { // 배열 마지막 원소를 가리키는 것이 길이보다 크면
            return;
        }

        if (rightNodeIndex <= len) { // 오른쪽 노드가 길이보다 작음: 둘다 있음
            int smallerIndex = priorityQueue[leftNodeIndex] < priorityQueue[rightNodeIndex] ? leftNodeIndex : rightNodeIndex;
            if (priorityQueue[smallerIndex] < priorityQueue[pointer]) { // 부모노드가 더 크면: 작은 쪽이랑 서로 교환
                swap(smallerIndex, pointer);
                pop(smallerIndex, len);
            }
        } else if (leftNodeIndex <= len && rightNodeIndex > len) { // 왼쪽 노드만 있음
            if (priorityQueue[leftNodeIndex] < priorityQueue[pointer]) { // 부모노드가 더 크면: 작은 쪽이랑 서로 교환
                swap(leftNodeIndex, pointer);
            }
        } else{
            return;
        }
    }

    public static void main(String[] args) throws IOException {

        int N = Integer.parseInt(br.readLine());

        priorityQueue = new int[N + 1]; // 1 ~ N 
        int pointer = 0;
        int len = 0;

        for (int i = 1; i <= N; i++) {
            int X = Integer.parseInt(br.readLine());

            if (X > 0) {
                pointer++;
                len++;
                priorityQueue[pointer] = X;
                prioritySort(pointer);
            } else { // X == 0
                if (len == 0) {
                    bw.write(String.valueOf(0) + "\n");
                }
                else{ // 배열 안빔.
                    bw.write(priorityQueue[1] + "\n");
                    priorityQueue[1] = priorityQueue[pointer];
                    pointer--;
                    len--;
                    pop(1, len);
                }
            }
        }


        bw.flush();
        br.close();
        bw.close();
    }

}
