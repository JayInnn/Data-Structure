package Chapter2;

/**
 * @author: yinwenjie
 * @email: 475660997@qq.com
 * @date: 2019/8/10
 * @description:
 * It provides with two methods of pattern matching for String. On the one hand, you can match by
 * traversal and back tracing; on the other hand, you can use KMP pattern matching algorithm. Of
 * course, KMP is better, but confuse you with the failure function.
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
        int i = 0, j = 0;
        int[] failure = new int[pat.length()];
        //compute the pattern's failure function
        fail(failure, pat);
        while(i < s.length() && j < pat.length()){
            if(s.charAt(i) == pat.charAt(j)){
                i++;
                j++;
            }else if(j == 0){
                i++;
            }else {
                j = failure[j-1] + 1;
            }
        }
        return j == pat.length() ? i - j : -1;
    }

    public static void fail(int[] failure, String pat){
        failure[0] = -1;
        int i;
        for(int j = 1; j < pat.length(); j++){
            i = failure[j-1];
            while(pat.charAt(j) != pat.charAt(i+1) && i >= 0){
                i = failure[i];
            }
            if(pat.charAt(j) == pat.charAt(i+1)){
                failure[j] = i + 1;
            }else {
                failure[j] = -1;
            }
        }
    }

    public static void main(String[] args){
        String str = "abcabcabcaa";
        String pat = "aa";
        System.out.println("=======>" + traverse(str, pat));
        System.out.println("=======>" + kmpMatch(str, pat));
    }
}
