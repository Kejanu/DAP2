package Blatt06L.LongSolution;

public class Interval implements Comparable {
    //Warum ist das schon hier
    int start, end;

    public Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public int getStart() {
        return this.start;
    }

    public int getEnd() {
        return this.end;
    }

    public String toString() {
        return "Interval: [" + start + ";" + end + "]";
    }

    @Override
    public int compareTo(Object o) {
        if (((Interval) o).end > this.end) {
            return -1;
        } else if (((Interval) o).end < this.end) {
            return 1;
        } else {
            return 0;
        }
    }
    
}
