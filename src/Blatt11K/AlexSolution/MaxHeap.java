package Blatt11K.AlexSolution;

public class MaxHeap {

    private int[] values;
    private int maxSize;
    private int heapSize;

    public MaxHeap(int n){

        if(n < 0){
            throw new IllegalArgumentException();
        }

        maxSize = n;
        values = new int[maxSize + 1]; //Weil ab 0
        heapSize = 0;
    }

    public int getHeapSize(){
        return heapSize;
    }

    private int left(int i){
        return 2 * i;
    }

    private int right(int i){
        return 2 * i + 1;
    }

    private int parent(int i){
        return i/2;
    }

    private void heapify(int i){
        int left = left(i);
        int right = right(i);
        int largest = i;
        if(left <= maxSize && values[left] > values[largest]){
            largest = left;
        }
        if(right <= maxSize && values[right] > values[largest]){
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
        if(heapSize < maxSize){
            ++heapSize;
            int i = heapSize;
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
        if(heapSize < 1){
            throw new IllegalStateException();
        } else {
            int max = values[1];
            values[1] = values[heapSize];
            --heapSize;
            heapify(1);
            return max;
        }
    }

    public void printHeap(){
        String output = "";
        int bound = 1;
        int current = 1;
        while (current <= heapSize){
            while (current <= bound && current <= heapSize){
                output = output + values[current] + " ";
                current++;
            }
            output = output + "\n";
            bound = bound * 2 + 1; //Anzahl der Knoten auf jeder Ebene verdoppelt sich, da 2 Kinder
        }
        System.out.println(output);
    }
}
