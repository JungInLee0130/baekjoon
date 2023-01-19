import java.io.*;
import java.util.*;


class Lecture{
    int start;
    int end;
    public Lecture(int start, int end) {
        this.start = start;
        this.end = end;
    }
}
public class Main {
    static int N;
    static PriorityQueue<Lecture> firstQueue;
    static ArrayList<Lecture> lectures;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        lectures = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            lectures.add(new Lecture(start, end));

        }

        // 시작시간 순으로 정렬하되, 끝나는시간은 오름차순으로
        Collections.sort(lectures, new Comparator<Lecture>() {
            @Override
            public int compare(Lecture o1, Lecture o2) {
                if (o1.start == o2.start) { //
                    return o1.end - o2.end;
                }
                return o1.start - o2.start;
            }
        });

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(lectures.get(0).end);
        countLectureRoom(lectures , priorityQueue);


        bw.write(String.valueOf(priorityQueue.size()));

        bw.flush();
        br.close();
        bw.close();
    }
    private static void countLectureRoom(ArrayList<Lecture> lectures, PriorityQueue<Integer> priorityQueue) {
        for (int i = 1; i < lectures.size(); i++) {
            if (lectures.get(i).start < priorityQueue.peek()) {
                // 작으면 우선순위큐에 넣기
                priorityQueue.add(lectures.get(i).end);
            } else {
                priorityQueue.poll();
                priorityQueue.add(lectures.get(i).end);
            }
        }
    }
}
