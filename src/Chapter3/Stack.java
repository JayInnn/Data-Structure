package Chapter3;

/**
 * @author: yinwenjie
 * @email: 475660997@qq.com
 * @date: 2019/8/12
 * @description:
 * Using the array list to realise stack. The most important thing, the ADT of stack is accomplished
 * by one class.
 * @result:
 * Adding three elements: 1 bb 2.2
 * After deleteing one element: 1 bb
 * After adding one element: 1 bb 2.2
 */
public class Stack {
    private int maxSize;
    private int top = -1;
    private Object[] stack;

    private static final int defaultSize = 10;

    public Stack(int maxSize){
        this.maxSize = maxSize;
        this.stack = new Object[maxSize];
    }

    public Stack(){
        this(defaultSize);
    }

    public boolean isFull(){
        return top >= maxSize;
    }

    public boolean isEmpty(){
        return top == -1;
    }

    public int size(){
        return top + 1;
    }

    public void add(Object obj){
        if(top >= maxSize){
            System.out.println("Now the stack is full! Don't add anymore!");
        }
        stack[++top] = obj;
    }

    public void delete(){
        if(top == -1){
            System.out.println("Now the stack is empty! Cannot delete anything!");
        }
        stack[top--] = null;
    }

    public String toString(){
        String str = "";
        for(Object entry : stack)
            if(entry != null)
                str = str + entry + " ";
        return str;
    }

    public static void main(String[] args){
        Stack stack = new Stack(5);
        int a = 1;
        String b = "bb";
        double c = 2.2;
        stack.add(a);
        stack.add(b);
        stack.add(c);
        System.out.println("Adding three elements: " + stack);
        stack.delete();
        System.out.println("After deleteing one element: " + stack);
        stack.add(c);
        System.out.println("After adding one element: " + stack);
    }
}
