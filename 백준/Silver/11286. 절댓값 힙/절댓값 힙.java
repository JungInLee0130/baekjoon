import java.io.*;

import static java.lang.Math.*;

public class Main {
    public static int[] swap(int[] priorityQueue, int root, int index){
        int temp = priorityQueue[root];
        priorityQueue[root] = priorityQueue[index];
        priorityQueue[index] = temp;

        return priorityQueue;
    }

    public static int[] AbsSort(int[] priorityQueue, int index) { // 삽입시 쓰는 sort.
        int rootNodeIndex = index / 2;
        int absRoot = abs(priorityQueue[rootNodeIndex]);
        int absChild = abs(priorityQueue[index]);

        // 아예 다른 수면 그냥 sort. 절댓값이 같은 수면 작은것을 위로.
        // 루트노드이면 리턴.
        if (index == 1) {
            return priorityQueue;
        }

        // 절댓값 힙은 기본적으로 최소힙.
        if (absRoot > absChild) { // -3 < 4 -> 아예 다른 수
            swap(priorityQueue, rootNodeIndex, index);
            AbsSort(priorityQueue, rootNodeIndex);
        } else if (absRoot == absChild) { // 절댓값이 같은 수
            if (priorityQueue[rootNodeIndex] > priorityQueue[index]) { // 루트노드가 더 큰수임: swap
                swap(priorityQueue, rootNodeIndex, index);
                AbsSort(priorityQueue, rootNodeIndex);
            }
        }

        return priorityQueue;
    }

    public static int peek(int[] priorityQueue, int index) {
        return priorityQueue[index];
    }

    public static int pop(int[] priorityQueue, int index, int len) {
        int num = priorityQueue[1];
        priorityQueue[1] = priorityQueue[index];
        popAndSort(priorityQueue, 1, len);

        return num;
    }
    public static int[] popAndSort(int[] priorityQueue, int index, int len) {
        int leftNodeIndex = index * 2;
        int rightNodeIndex = index * 2 + 1;
        int AbsSmallerIndex;

        // 여기서도 절댓값 소트 해야됨. 근데, 같은수는 실제 크기로 함.
        if (rightNodeIndex <= len) { // 가장 마지막 트리의 노드가 len값 이하임
            int AbsLeftNode = abs(priorityQueue[leftNodeIndex]);
            int AbsRightNode = abs(priorityQueue[rightNodeIndex]);
            int AbsCurrentNode = abs(priorityQueue[index]);

            // 더 작은 노드 정하기
            if (AbsLeftNode < AbsRightNode) // 최소힙이니까 더 작은거랑 교환
            {
                AbsSmallerIndex = leftNodeIndex;
            } else if (AbsLeftNode == AbsRightNode) { // 둘이 절댓값이 같음
                if (priorityQueue[leftNodeIndex] < priorityQueue[rightNodeIndex]) // 절댓값이 같은데 실제로는 왼쪽이 더 작음.
                {
                    AbsSmallerIndex = leftNodeIndex;
                }
                else{
                    AbsSmallerIndex = rightNodeIndex;
                }
            }
            else{
                AbsSmallerIndex = rightNodeIndex;
            }

            int AbsSmallerNode = abs(priorityQueue[AbsSmallerIndex]);
            
            if (AbsSmallerNode < AbsCurrentNode) { // 현재 노드의 절댓값이 더 큼: 교환
                swap(priorityQueue, AbsSmallerIndex, index);
                popAndSort(priorityQueue, AbsSmallerIndex, len);
            } else if (AbsSmallerNode == AbsCurrentNode) { // 둘의 절댓값이 같음: 실제값 비교
                if (priorityQueue[AbsSmallerIndex] < priorityQueue[index]) { // 실제로도 자식노드가 더작음.
                    swap(priorityQueue, AbsSmallerIndex, index);
                    popAndSort(priorityQueue, AbsSmallerIndex, len);
                }
            }
        } else if (leftNodeIndex <= len && rightNodeIndex > len) { // leftNode만 있을때
            int AbsLeftNode = abs(priorityQueue[leftNodeIndex]);
            int AbsCurrentNode = abs(priorityQueue[index]);

            if (AbsLeftNode < AbsCurrentNode) // 현재 노드가 더 큼.
            {
                swap(priorityQueue, leftNodeIndex, index);
                // 마지막이라 swap하고 끝
            } else if (AbsLeftNode == AbsCurrentNode) { // 절댓값이 같음: 실제 값 비교
                if (priorityQueue[leftNodeIndex] < priorityQueue[index]) { // 실제로도 자식노드가 더 작음.
                    swap(priorityQueue, leftNodeIndex, index);
                }
            }
        }
        return priorityQueue;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        int[] priorityQueue = new int[N + 1]; // 1 ~ N
        int index = 0; // 배열의 마지막 원소를 가리킴.
        int len = 0; // 배열의 길이
        for (int i = 0; i < N; i++) {
            int X = Integer.parseInt(br.readLine());

            if (X != 0) {
                index++;
                len++;
                priorityQueue[index] = X;
                AbsSort(priorityQueue, index);
            } else {
                if (len == 0) {
                    bw.write(String.valueOf(0) + "\n");
                }
                else{
                    // pop과 동시에 peek도 만들어야할듯. -> 절댓값이 같은수를 작은 것을 위로 올리면 굳이 peek할 필요 없음.
                    len--;
                    bw.write(String.valueOf(pop(priorityQueue, index, len)) + "\n");
                    index--;
                }
            }
        }

        bw.flush();
        br.close();
        bw.close();
    }
}
