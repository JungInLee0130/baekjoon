import java.io.*;
import java.util.*;

class Main {
    static int N;
    static class Student{
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
    }
    static Student[] students;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        students = new Student[N];
        for (int i = 0; i < N; i++) {
            String[] sp = br.readLine().split(" ");

            students[i] = new Student(sp[0], Integer.parseInt(sp[1]),
                    Integer.parseInt(sp[2]),
                    Integer.parseInt(sp[3]));
        }

        Arrays.sort(students, new Comparator<Student>(){
            @Override
            public int compare(Student s1, Student s2){
                if (s1.kor == s2.kor){
                    if (s1.eng == s2.eng){
                        if (s1.math == s2.math){
                            return s1.name.compareTo(s2.name);
                        }
                        return s2.math - s1.math;
                    }
                    return s1.eng - s2.eng;
                }
                return s2.kor - s1.kor;
            }
        });

        for (Student s : students){
            System.out.println(s.name);
        }

        br.close();
    }
}