package Chapter1;

import java.util.Arrays;
import java.util.Scanner;

public class SelectionSort {
    public static void sort(int[] list){
        int length = list.length;
        int min;
        for(int i = 0; i < length; i++){
            min = i;
            //check the min from list[i] to list[n-1]
            for(int j = i + 1; j < length; j++){
                if(list[min] > list[j]) min = j;
            }
            //swap list[i] and list[min]
            int tmp = list[i];
            list[i] = list[min];
            list[min] = tmp;
        }
    }

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        System.out.print("please input the length of list:");
        int length = in.nextInt();
        int[] list = new int[length];
        System.out.print("please input the value of list:");
        for(int i = 0; i < length; i++)
            list[i] = in.nextInt();

        System.out.println("before sorting list:" + Arrays.toString(list));
        sort(list);
        System.out.println("after sorting list:" + Arrays.toString(list));
    }
}
