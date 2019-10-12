package Chapter7;

import java.util.Arrays;

/**
 * @author: yinwenjie
 * @email: 475660997@qq.com
 * @date: 2019/10/11
 * @description: insertion sort & quick sort
 * @result:
 */
public class InternalSort {

    public static void insertion_sort(int[] list, int n){
        System.out.println("Before insertion sort: " + Arrays.toString(list));
        int key, j;
        for (int i = 0; i < n; i++){
            key = list[i];
            for (j = i - 1; j >= 0 && list[j] > key; j--)
                //exchange list[j+1] and list[j]
                list[j+1] = list[j];
            list[j+1] = key;
        }
        System.out.println("After insertion sort: " + Arrays.toString(list));
    }

    public static void quick_sort(int[] list, int left, int right){
        if (left < right){
            int n = partition(list, left, right);
            quick_sort(list, left, n-1);
            quick_sort(list, n+1, right);
        }
    }

    private static int partition(int[] list, int left, int right){
        int key = list[right];
        int less = left - 1, tmp;
        for (int i = left; i < right; i++){
            if (list[i] < key){
                //exchange list[less+1] with list[i]
                less++;
                tmp = list[i];
                list[i] = list[less];
                list[less] = tmp;
            }
        }
        list[right] = list[less+1];
        list[less+1] = key;
        return less + 1;
    }

    public static void main(String[] args){
        int[] list = {13,6,21,1,7,4,9,5,8,15,7,23,6,2};
        insertion_sort(list, list.length);

        int[] list1 = {13,6,21,1,7,4,9,5,8,15,7,23,6,2};
        System.out.println("Before quick sort: " + Arrays.toString(list1));
        quick_sort(list1, 0, list.length-1);
        System.out.println("After quick sort: " + Arrays.toString(list1));
    }
}
