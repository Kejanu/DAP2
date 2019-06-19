package Blatt11K.AlexSolution;

import java.util.Random;

public class PriorityQueue {

    private MaxHeap heap;

    public static void main(String[] args){

        if(args.length != 2){
            System.out.println("Number of arguments is not sufficient");
            return;
        }

        int n;
        int k;

        try {
            n = Integer.parseInt(args[0]);
            k = Integer.parseInt(args[1]);
        } catch (NumberFormatException e){
            System.out.println("Arguments are invalid");
            return;
        }

        if(n < 0 || k < 0){
            System.out.println("Arguments are invalid");
        }


        PriorityQueue queue = new PriorityQueue(n + k);
        Random random = new Random();

        for (int i = 1; i <= n; ++i){
            queue.add(random.nextInt(101));
        }

        for (int i = 1; i <= k; ++i){

            queue.print();

            double chance = random.nextInt(4);



            if(chance == 0){
                //neuer Job

                int newJob = random.nextInt(101);
                System.out.println("New Job (priority " + newJob + ") added");
                queue.add(newJob);

            } else {
                //Job fertig

                if(queue.getQueueSize() >= 1) {
                    System.out.println("Next Job: " + queue.remove());
                } else {
                    System.out.println("No elements left to remove");
                }
            }
        }

        queue.print();

    }

    public PriorityQueue(int n){
        heap = new MaxHeap(n);
    }

    public boolean add(int x){
        return heap.insertKey(x);
    }

    public int remove(){
        if(heap.getHeapSize() >= 1) {
            return heap.extractMax();
        } else {
            throw new IllegalStateException();
        }
    }

    public void print(){
        System.out.println();
        heap.printHeap();
    }

    public int getQueueSize(){
        return heap.getHeapSize();
    }
}
