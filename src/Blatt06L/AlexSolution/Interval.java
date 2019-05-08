package Blatt06L.AlexSolution;

public class Interval implements Comparable<Interval> {

    private int start, end;

    public Interval(int start, int end){
        if(start > end || start < 0 || end < 0){
            throw new IllegalArgumentException();
        }else{
            this.end=end;
            this.start=start;
        }
    }

    public int getStart(){
        return start;
    }

    public int getEnd(){
        return end;
    }

    @Override
    public String toString(){
        return "[" + start + ", " + end + "]";
    }

    @Override
    public int compareTo(Interval other){
        int result =  end - other.getEnd();
        if (result == 0){
            result = start - other.getStart();
        }
        return result;
    }
}
