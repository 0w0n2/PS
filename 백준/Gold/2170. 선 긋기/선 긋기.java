import java.util.*;
import java.io.*;

public class Main {

    private static class Point implements Comparable<Point>{
        int start, end;

        public Point(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Point o) {
            return (this.start!=o.start) ? Integer.compare(this.start, o.start)
                    : Integer.compare(this.end, o.end);
        }
    }

    public static void main(String[] args) throws IOException {
        int N = readInt();
        PriorityQueue<Point> q = new PriorityQueue<>();

        for (int i=0;i<N;i++){
            q.offer(new Point(readInt(), readInt()));
        }

        Point last = q.poll();
        int result = 0;

        while(!q.isEmpty()){
            Point cur = q.poll();
            if (last.start <= cur.start && cur.start <= last.end){
                last.end = Math.max(last.end, cur.end);
            } else {
                result += Math.abs(last.end - last.start);
                last.start = cur.start;
                last.end = cur.end;
            }
        }

        result += Math.abs(last.end - last.start);

        System.out.print(result);

    }

    private static final StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    private static int readInt() throws IOException{
        st.nextToken();
        return (int) st.nval;
    }
}
