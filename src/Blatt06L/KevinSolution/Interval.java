package Blatt06L.KevinSolution;

public class Interval {

    private int start;
    private int end;

    public Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[" + start + ", " + end + "]";
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }
}
