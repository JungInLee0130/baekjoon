import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        int n = Integer.parseInt(st.nextToken());
        long k = Long.parseLong(st.nextToken());
        long[] level = new long[n];
        for(int i=0;i<n;i++){
            level[i] = Long.parseLong(br.readLine());
        }

        Arrays.sort(level);

        System.out.println(binarySearch(k,0,level[n-1]+k,level));

    }
    public static long binarySearch(long m,long min,long max,long[] arr){
        long result = 0;
        while(min<=max){
            long mid = (min+max)/2;
            long count = 0;
            for(long i : arr){
                if(mid-i>0)
                    count += mid-i;
            }
            if(count>m){
                max = mid - 1;
            }
            else{
                result = mid;
                min = mid + 1;
            }
        }

        return result;
    }
}