package Chapter1;

/**
 * @author: yinwenjie
 * @email: 475660997@qq.com
 * @date: 2019/8/5
 * @description:
  * The BinarySearch class has two methods. They can all judge whether the given element exists in sorted list.
  * The sortedList array is sorted by ASC
 * @result:
  * -1
  * -1
 */
public class BinarySearch {

    public static int iteratorSearch(int[] sortedList, int searchNum, int left, int right){
        int middle;
        while(left <= right){
            middle = (left + right) / 2;
            if(sortedList[middle] < searchNum){
                left = middle + 1;
            }else if(sortedList[middle] == searchNum){
                return 1;
            }else{
                right = middle - 1;
            }
        }
        return -1;
    }

    public static int recursiveSearch(int[] sortedList, int searchNum, int left, int right){
        if(left <= right){
            int middle = (left + right) / 2;
            if(sortedList[middle] == searchNum){
                return 1;
            }
            if(sortedList[middle] < searchNum) return recursiveSearch(sortedList, searchNum, middle+1, right);
            if(sortedList[middle] > searchNum) return recursiveSearch(sortedList, searchNum, left, middle-1);
        }
        return -1;
    }

    public static void main(String[] args){
        int[] list = new int[]{0,1,2,4,5,7,66,76,86,96};
        System.out.println(iteratorSearch(list, 4, 6, 9));
        System.out.println(recursiveSearch(list, 3, 0, 9));
    }
}
