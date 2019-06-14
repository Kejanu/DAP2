package Blatt11K.AlexSolution;

public class Heap {

    private int[] values;
    private int heapSize;
    private int lastElement;

    public Heap(int n){

        if(n < 0){
            throw new IllegalArgumentException();
        }

        heapSize = n;
        values = new int[n + 1];
        lastElement = 0;
    }


    public int left(int i){
        if(i > 0 && i <= heapSize/2){
            return 2 * i;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public int right(int i){
        if(i > 0 && i <= heapSize/2 && 2 * i + 1 <= heapSize){
            return 2 * i + 1;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public int parent(int i){
        if(i > 1 && i <= heapSize){
            return i/2;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void heapify(int i){
        int left = left(i);
        int right = right(i);
        int largest = i;
        if(left <= heapSize && values[left] > values[largest]){
            largest = left;
        }
        if(right <= heapSize && values[right] > values[largest]){
            largest = right;
        }
        if(largest != i) {
            swap(i, largest);
            heapify(largest);
        }
    }


    private void swap(int a, int b){
        int tmp = values[a];
        values[a] = values[b];
        values[b] = tmp;
    }

    public boolean insertKey(int key){
        if(lastElement < heapSize){
            ++lastElement;
            int i = lastElement;
            while (i > 1 && values[parent(i)] < key) {
                values[i] = values[parent(i)];
                i = parent(i);
            }
            values[i] = key;
            return true;
        } else {
            return false;
        }
    }

    public int extractMax(){
        if(lastElement < 1){
            throw new IllegalStateException();
        } else {
            int max = values[1];
            values[1] = values[lastElement];
            --lastElement;
            heapify(1);
            return max;
        }
    }

    public void printHeap(){
        String output = "";
        int bound = 1;
        int current =1;
        while (current <= lastElement){
            while (current <= bound && current <= lastElement){
                output = output + values[current] + " ";
                current++;
            }
            output = output + "\n";
            bound = bound * 2;
        }
        System.out.println(output);
    }
}
