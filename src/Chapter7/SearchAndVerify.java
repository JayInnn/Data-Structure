package Chapter7;

/**
 * @author: yinwenjie
 * @email: 475660997@qq.com
 * @date: 2019/10/11
 * @description: this class is mainly to illustrate the importance of sorting.
 * @result:
 */
public class SearchAndVerify {
    //searching: search random list and search sorted list
    public static int seqSearch(int[] list, int searchNum, int n){
        int i = 0;
        for (; i < n && list[i] != searchNum; i++)
            ;
        return i < n ? i : -1;
    }

    public static int binSearch(int[] list, int searchNum, int n){
        int left = 0, right = n;
        while (left <= right){
            int middle = (left + right) / 2;
            if (list[middle] == searchNum){
                return middle;
            }else if (list[middle] > searchNum){
                left = middle + 1;
            }else {
                right = middle -1;
            }
        }
        return -1;
    }

    //verifying: verify random list and verify sorted list
    public static void verifyRandom(int[] list1, int[] list2, int n, int m){
        boolean[] marked = new boolean[m];
        for (int i = 0; i < n; i++){
            int j = seqSearch(list2, list1[i], m);
            if (j < 0)
                System.out.println("list1-" + list1[i] + " is not in list2.");
            else marked[j] = true;
        }
        for (int i = 0; i < m; i++)
            if (!marked[i])
                System.out.println("list2-" + list2[i] + " is not in list1.");
    }

    public static void verifySorted(int[] list1, int[] list2, int n, int m){
        int i = 0, j = 0;
        while (i < n && j < m){
            if (list1[i] == list2[j]){
                i++;
                j++;
            }else if (list1[i] > list2[j]){
                System.out.println("list2-" + list2[j] + " is not in list1.");
                j++;
            }else {
                System.out.println("list1-" + list1[i] + " is not in list2.");
                i++;
            }
        }
        for (; i < n; i++)
            System.out.println("list1-" + list1[i] + " is not in list2.");
        for (; j < m; j++)
            System.out.println("list2-" + list2[j] + " is not in list1.");
    }

    public static void main(String[] args){
        int[] randomList1 = {3,6,2,1,7,4,9,5,8};
        int[] randomList2 = {12,11,4,7,9,6,8,5,10};
        int[] sortedList1 = {1,2,3,4,5,6,7,8,9};
        int[] sortedList2 = {5,6,7,8,9,10,11};
        System.out.println("seqSearch: " + seqSearch(randomList1, 5, 9));
        System.out.println("binSearch: " + binSearch(sortedList1, 5, 9));
        System.out.println("=====================================");
        verifyRandom(randomList1, randomList2, 9, 9);
        System.out.println("=====================================");
        verifySorted(sortedList1, sortedList2, 9, 7);
    }
}