package Chapter2;

/**
 * @author: yinwenjie
 * @email: 475660997@qq.com
 * @date: 2019/8/10
 * @description:
 * @result:
 */
public class StringPatternMatching {
    //traversal and back tracing
    public static int traverse(String str, String pat){
        int i = 0, j = 0;
        while(i < str.length() && j < pat.length()){
            if(str.charAt(i) == pat.charAt(j)){
                i++;
                j++;
            }else {
                i = i - j + 1;
                j = 0;
            }
        }
        return j < pat.length() ? -1 : i - j;
    }

    public static int kmpMatch(String s, String pat){
        
        return -1;
    }

    public static void main(String[] args){
        String str = "abcabcabcaa";
        String pat = "aa";

        System.out.println("=======>" + traverse(str, pat));
    }
}
