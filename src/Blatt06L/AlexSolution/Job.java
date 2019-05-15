package Blatt06L.AlexSolution;

public class Job implements Comparable<Job> {

    private int duration, deadline;

    public Job(int duration, int deadline){

        if(duration < 0 || deadline < 0 || deadline < duration){
            throw new IllegalArgumentException();
        }

        this.deadline = deadline;
        this.duration = duration;
    }

    public int getDuration(){
        return duration;
    }

    public int getDeadline(){
        return deadline;
    }

    @Override
    public String toString(){
        //return "Duration: " + duration + ", Deadline: " + deadline;
        return "[" + duration + ", " + deadline + "]";
    }

    @Override
    public int compareTo(Job other){

        /*
        int result = deadline - other.getDeadline();
        if(result == 0){
            result = duration - other.getDuration();
        }
        return result;

        */

        return  deadline - other.deadline;
    }
}
