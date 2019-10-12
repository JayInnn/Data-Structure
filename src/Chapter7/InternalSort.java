package Chapter7;

import java.util.Arrays;

/**
 * @author: yinwenjie
 * @email: 475660997@qq.com
 * @date: 2019/10/11
 * @description: insertion sort & quick sort & merge sort
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

    public static void merge_sort(int[] list, int n){
        System.out.println("Before merge sort: " + Arrays.toString(list));
        int length = 1;
        int[] extra = new int[n];
        while (length < n){
            merge_pass(list, extra, n, length);
            length *= 2;
            merge_pass(extra, list, n, length);
            length *= 2;
        }
        System.out.println("After merge sort: " + Arrays.toString(list));
    }

    private static void merge_pass(int[] list, int[] sorted, int n, int length){
        int i;
        for (i = 0; i <= n - 2 * length; i += 2 * length)
            merge(list, sorted, i, i + length - 1, i + 2 * length - 1);
        if (i + length < n)
            merge(list, sorted, i, i + length - 1, n - 1);
        else {
            for (int j = i; j < n; j++)
                sorted[j] = list[j];
        }
    }

    private static void merge(int[] list, int[] sorted, int i, int m, int n){
        int j = m + 1;
        int k = i, t;
        while (i <= m && j <= n){
            if (list[i] <= list[j])
                sorted[k++] = list[i++];
            else
                sorted[k++] = list[j++];
            if (i > m){
                for (t = j; t <= n; t++)
                    sorted[k+t-j] = list[t];
            }else {
                for (t = i; t <= m; t++)
                    sorted[k+t-i] = list[t];
            }
        }
    }

    //how to create bucket is a question, but this algorithm is not hard.
    public static void radix_sort(){}

    public static void main(String[] args){
        int[] list = {13,6,21,1,7,4,9,5,8,15,7,23,6,2};
        insertion_sort(list, list.length);

        int[] list1 = {13,6,21,1,7,4,9,5,8,15,7,23,6,2};
        System.out.println("Before quick sort: " + Arrays.toString(list1));
        quick_sort(list1, 0, list.length-1);
        System.out.println("After quick sort: " + Arrays.toString(list1));

        int[] list2 = {13,6,21,1,7,4,9,5,8,15,7,23,6,2};
        merge_sort(list2, list2.length);
    }
}