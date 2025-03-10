import java.io.*;
import java.util.*;

class Main {
    static int N;
    static class Student implements Comparable<Student>{
        String name;
        int kor;
        int eng;
        int math;

        public Student(String name, int kor, int eng, int math) {
            this.name = name;
            this.kor = kor;
            this.eng = eng;
            this.math = math;
        }

        @Override
        public int compareTo(Student s){
            if (this.kor == s.kor){
                if (this.eng == s.eng){
                    if (this.math == s.math){
                        return this.name.compareTo(s.name);
                    }
                    return s.math - this.math;
                }
                return this.eng - s.eng;
            }
            return s.kor - this.kor;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        PriorityQueue<Student> pq = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            String[] sp = br.readLine().split(" ");

            Student student = new Student(sp[0], Integer.parseInt(sp[1]),
                    Integer.parseInt(sp[2]),
                    Integer.parseInt(sp[3]));

            pq.add(student);
        }

        while (!pq.isEmpty()){
            System.out.println(pq.poll().name);
        }

        br.close();
    }
}