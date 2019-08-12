package Chapter3;

/**
 * @author: yinwenjie
 * @email: 475660997@qq.com
 * @date: 2019/8/12
 * @description:
 * Using the circular queue to realise queue.
 * @result:
 * Now the queue is full! Cannot add anymore!
 * 1======>null  0  1  2  3
 * 2======>null  null  1  2  3
 * 3======>10  null  1  2  3
 * 4======>10  null  null  2  3
 */
public class CircularQueue {
    private int maxSize;
    private int front = 0;
    private int rear = 0;
    private Object[] circularQueue;

    private static final int defaultSize = 10;

    public CircularQueue(int maxSize){
        this.maxSize = maxSize;
        this.circularQueue = new Object[maxSize];
    }

    public CircularQueue(){
        this(defaultSize);
    }

    public boolean isFull(){
        return ((rear + 1) % maxSize) == front;
    }

    public boolean isEmpty(){
        return rear == front;
    }

    public int getSize(){
        return rear < front ? (maxSize - front + rear) : (rear - front);
    }

    public void add(Object obj){
        if (((rear + 1) % maxSize) == front)
            System.out.println("Now the queue is full! Cannot add anymore!");
        else {
            rear = (rear + 1) % maxSize;
            circularQueue[rear] = obj;
        }
    }

    public void delete(){
        if (front == rear)
            System.out.println("Now the queue is empty! Cannot delete anything!");
        front = (front + 1) % maxSize;
        circularQueue[front] = null;
    }

    public String toString(){
        StringBuilder str = new StringBuilder();
        for (Object entry : circularQueue)
            str.append(entry).append("  ");
        return str.toString();
    }

    public static void main(String[] args){
        CircularQueue circularQueue = new CircularQueue(5);
        for(int i = 0; i < 5; i++){
            circularQueue.add(i);
        }
        System.out.println("1======>" + circularQueue);
        circularQueue.delete();
        System.out.println("2======>" + circularQueue);
        circularQueue.add(10);
        System.out.println("3======>" + circularQueue);
        circularQueue.delete();
        System.out.println("4======>" + circularQueue);
    }
}
