package Blatt03K.AlexSolution;

import java.util.Random;

public class Main {

    public  Main(){}


    public static void main(String[] args) {

        long tStart, tEnd;
        final String MESSAGE= "No boundary provided";
        float boundary, result;
        int arraySize;
        int[] array;
        Random random= new Random();


        //3.1

        /*

        int[] testArray = new int[5000];
        fillDescending(testArray);
        tStart=System.currentTimeMillis();
        bubbleSort(testArray);
        tEnd=System.currentTimeMillis();
        System.out.println(tEnd-tStart);
        System.out.println(isSorted(testArray));
        //Result 82ms = 0,082sec

        */

        //3.2

        //todo Zeitmessung funktioniert nicht richtig

        if(args.length!= 1){
            System.out.println(MESSAGE);
            return;
        }

        try{
            boundary=Float.parseFloat(args[0]);
        }catch (Exception e){
            System.out.println(MESSAGE);
            return;
        }

        arraySize=500;
        result=0;


        while(result<= boundary) {

            arraySize = arraySize*2;
            array = new int[arraySize];
            fillDescending(array);

            tStart=System.currentTimeMillis();
            bubbleSort(array);
            tEnd=System.currentTimeMillis();

            result= (float) (tEnd-tStart)/1000;

            System.out.println(" ");
            System.out.println("BubbleSort");
            System.out.println("Sorted: " + isSorted(array));
            System.out.print("Size: " + arraySize + ", Time: " + result + "sec");
            if(!(result<=boundary)){
                System.out.println(" (Termination)");
            }
            System.out.println(" ");


            //BinarySearch

            int search;
            long s, e;

            if(arraySize==1000){
                search= random.nextInt(1000);
            }else{
                search= random.nextInt(arraySize/2)+arraySize/2-1;
            }

            System.out.println("BinarySearch");
            System.out.println("Array Size: " + arraySize);
            System.out.println("Searching for: " + search);



            s= System.currentTimeMillis();
            if(arraySize==1000){
                binarySearchExtended(array,search,0,arraySize-1, boundary);
            }else{
                binarySearchExtended(array,search, arraySize/2-1,arraySize-1, boundary);
            }
            e=System.currentTimeMillis();

            System.out.println("--------------------------------------------------");
            System.out.println("Total Time: " + (e-s)/1000.0);
            System.out.println("--------------------------------------------------");




        }












    }

    public static void bubbleSort(int[] arr){
        int n=arr.length;
        for(int i=0; i<n; i++){
            for(int j=n-1; j>i; j--){
               if(arr[j-1]> arr[j]){
                   int tmp= arr[j];
                   arr[j]= arr[j-1];
                   arr[j-1]=tmp;
               }
            }
        }
    }

    public static void fillDescending(int[] arr){
        for(int i=0; i<arr.length; i++){
            arr[i]=arr.length-1-i;
        }
    }

    public static boolean isSorted(int[] arr){

        for(int i=1; i<arr.length; i++){
            if(arr[i]<arr[i-1]){
                return false;
            }
        }
        return true;
    }

    public static int binarySearchExtended(int[] arr, int search, int left, int right, float maxTime){



        long tStart, tEnd;
        float difference;
        int result;

        tStart=System.currentTimeMillis();


        if(left==right){
            result= left;
        }else{
            int middle=(left+right)/2;
            if(search<=arr[middle]){
                result= binarySearchExtended(arr,search,left,middle, maxTime);
            }else{
                result= binarySearchExtended(arr,search,middle+1,right, maxTime);
            }
        }

        tEnd=System.currentTimeMillis();

        difference=(float) (tEnd-tStart)/1000;

        if(result== -1){
            return -1;
        }

        //todo Abweichen oder Überschreiten?
        if(difference  >maxTime-0.1 //&& ((tEnd-tStart)/1000.0 < maxTime+0.1)
                 ){
            System.out.println("-----------------------------------------");
            System.out.println("Terminated. Left: " + left + ", Right: " + right);
            return -1;
        }

        System.out.println("-----------------------------------------");
        System.out.println("Start: " + tStart);
        System.out.println("End: " + tEnd);
        System.out.println("Left: " + left + ", Right: " + right);
        System.out.println("Time: " + difference);
        return result;

    }
}
