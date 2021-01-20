package Leson1HW;

import java.util.Arrays;

public class Ls1Task1 {

    public static void main(String[] args) {

        Integer arr1[] = {1, 2, 3, 4, 5, 6, 7};
        String arr2[] = {"A", "B", "C"};
        swap(arr1, 1, 4);
        swap(arr2, 0, 2);

    }

    public static void swap(Object[] arr, int n1, int n2){
        System.out.println("Task1: "+ Arrays.toString(arr));
        Object sw = arr[n1];
        arr[n1]=arr[n2];
        arr[n2]=sw;
        System.out.println("The result of the replacement: "+Arrays.toString(arr)+"\n____________________________");
    }

}
