package Chapter3;

/**
 * @author: yinwenjie
 * @email: 475660997@qq.com
 * @date: 2019/8/12
 * @description:
 * Finished the queue by no-circular array list. Every time, rear is equal to the end boundary of array,
 * and then continue adding new object, it will put the array in order like 'gc' in JVM.
 * @result:
 * ======>0 1 2 3 4 5 6 7 8 9
 * ======>null null 2 3 4 5 6 7 8 9
 * ======>2 3 4 5 6 7 8 9 10 null
 */
public class Queue {
    private int maxSize;
    private int front = -1;
    private int rear = -1;
    private Object[] queue;

    private static final int defaultSize = 10;

    public Queue(int maxSize){
        this.maxSize = maxSize;
        this.queue = new Object[maxSize];
    }

    public Queue(){
        this(defaultSize);
    }

    public boolean isFull(){
        return rear - front == maxSize;
    }

    public boolean isEmpty(){
        return rear == front;
    }

    public int size(){
        return (rear - front);
    }

    public void add(Object obj){
        int size = rear - front;

        if(rear < maxSize - 1){
            queue[++rear] = obj;
        }else if(rear == maxSize - 1 && size != maxSize){
            transfer();
            queue[++rear] = obj;
        }else {
            System.out.println("Now the queue is full! Cannot add anymore!");
        }
    }

    private void transfer(){
        int size = rear - front;
        for(int i = 0; i < maxSize; i++){
            if(i < size){
                queue[i] = queue[front + 1 + i];
            }else {
                queue[i] = null;
            }
        }
        front = -1;
        rear = front + size;
    }

    public void delete(){
        if(rear == front)
            System.out.println("Now the queue is empty! Cannot delete anything!");
        queue[++front] = null;
    }

    public String toString(){
        StringBuilder str = new StringBuilder();
        for(Object entry : queue)
            str.append(entry).append(" ");
        return str.toString();
    }

    public static void main(String[] args){
        Queue queue = new Queue();
        for(int i = 0; i < 10; i++){
            queue.add(i);
        }
        System.out.println("======>" + queue);
        queue.delete();
        queue.delete();
        System.out.println("======>" + queue);
        queue.add(10);
        System.out.println("======>" + queue);
    }
}
