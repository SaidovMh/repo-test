package Leson1HW;

import java.util.ArrayList;
import java.util.Arrays;

public class Ls1Task2 {

    public static void main(String[] args) {

        String[] arrayOfStrings = {"A", "B", "C", "D"};
        asList(arrayOfStrings);

    }

    public static <T> void asList(T[]arr){

        ArrayList<T> alt = new ArrayList<>(Arrays.asList(arr));
        System.out.println("Task2 result : "+alt+"\n____________________________");
    }

}
