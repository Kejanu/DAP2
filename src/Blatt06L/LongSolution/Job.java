package Blatt06L.LongSolution;

public class Job implements Comparable {
    //Warum ist das schon hier
    int dauer, deadline;

    public Job(int dauer, int deadline) {
        this.dauer = dauer;
        this.deadline = deadline;
    }

    public int getDauer() {
        return this.dauer;
    }

    public int getDeadline() {
        return this.deadline;
    }

    public String toString() {
        return "[" + dauer + "," + deadline + "]";
    }

    @Override
    public int compareTo(Object o) {
        if (((Job) o).deadline > this.deadline) {
            return -1;
        } else if (((Job) o).deadline < this.deadline) {
            return 1;
        } else {
            return 0;
        }
    }
}
