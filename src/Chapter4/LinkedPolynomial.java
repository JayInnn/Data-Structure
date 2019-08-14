package Chapter4;

/**
 * @author: yinwenjie
 * @email: 475660997@qq.com
 * @date: 2019/8/14
 * @description:
 * Using linked list to realize the polynomial. And the LinkedPolynomial class can perfectly map to the
 * Objects and functions in the ADT of polynomial.
 * @result:
 * ====> <5.0, 5> <4.0, 4> <3.0, 3> <2.0, 2> <1.0, 1>
 * ====> <8.0, 8> <7.0, 7> <6.0, 6> <5.0, 5> <4.0, 4>
 * ====> <8.0, 8> <7.0, 7> <6.0, 6> <10.0, 5> <8.0, 4> <3.0, 3> <2.0, 2> <1.0, 1>
 */
public class LinkedPolynomial {
    private static class TupleNode{
        public double coef;
        public int expon;
        public TupleNode next;

        public TupleNode(double coef, int expon, TupleNode next){
            this.coef = coef;
            this.expon = expon;
            this.next = next;
        }
    }

    private TupleNode poly;

    public LinkedPolynomial(){

    }

    public TupleNode getPoly(){
        return poly;
    }

    public void insert(double coef, int expon, TupleNode next){
        if(poly == null){
            poly = new TupleNode(coef, expon, next);
        }else {
            TupleNode tmp = poly;
            TupleNode pre = null;
            while (tmp != null){
                if (tmp.expon < expon){
                    break;
                }else {
                    pre = tmp;
                    tmp = tmp.next;
                }
            }
            if (pre == null){
                TupleNode p = new TupleNode(coef, expon, next);
                p.next = tmp;
                poly = p;
            }else if (tmp == null){
                pre.next = new TupleNode(coef, expon, next);
            }else {
                pre.next = new TupleNode(coef, expon, next);
                pre.next.next = tmp;
            }
        }
    }

    public void add(LinkedPolynomial p){
        TupleNode a = poly;
        TupleNode b = p.getPoly();
        TupleNode front = new TupleNode(0, 0, null);
        TupleNode rear = front;

        while(a != null && b != null){
            if (a.expon == b.expon){
                rear.next = new TupleNode(a.coef + b.coef, a.expon, null);
                rear = rear.next;
                a = a.next;
                b = b.next;
            }else if (a.expon < b.expon){
                rear.next = new TupleNode(b.coef, b.expon, null);
                rear = rear.next;
                b = b.next;
            }else {
                rear.next = new TupleNode(a.coef, a.expon, null);
                rear = rear.next;
                a = a.next;
            }
        }
        if (a != null)
            rear.next = a;

        if (b != null)
            rear.next = b;

        poly = front.next;
    }

    public String toString(){
        StringBuilder str = new StringBuilder();
        TupleNode tmp = poly;
        for(;tmp != null; tmp = tmp.next)
            str.append("<").append(tmp.coef).append(", ").append(tmp.expon).append("> ");
        return str.toString();
    }

    public static void main(String[] args){
        LinkedPolynomial poly1 = new LinkedPolynomial();
        LinkedPolynomial poly2 = new LinkedPolynomial();
        for (int i = 5; i > 0; i--){
            poly1.insert(i, i, null);
            poly2.insert(i+3, i+3, null);
        }
        System.out.println("====> " + poly1);
        System.out.println("====> " + poly2);
        poly1.add(poly2);
        System.out.println("====> " + poly1);
    }
}
