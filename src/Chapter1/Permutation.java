package Chapter1;

import java.util.Arrays;

/**
 * @author: yinwenjie
 * @email: 475660997@qq.com
 * @date: 2019/8/5
 * @description:
  * The Permutation class can give the all permutation of a char array.
  * Also it can give the all permutation of sub-char array from i to n.
 * @result:
  * [a, b, c]
  * [a, c, b]
  * [b, a, c]
  * [b, c, a]
  * [c, b, a]
  * [c, a, b]
 */
public class Permutation {

    public static void perm(char[] list, int i, int n){
        if(i == n) System.out.println(Arrays.toString(list));
        for(int j = i; j <= n; j++){
            //swap list[i] and list[j]
            swap(list, i, j);
            perm(list, i+1, n);
            swap(list, i, j);
        }
    }

    public static void swap(char[] list, int i, int j){
        char tmp = list[i];
        list[i] = list[j];
        list[j] = tmp;
    }

    public static void main(String[] args){
        char[] list = new char[]{'a','b','c'};
        perm(list, 0, 2);
    }
}
