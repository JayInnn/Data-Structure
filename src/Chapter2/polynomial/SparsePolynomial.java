package Chapter2.polynomial;
/**
 * @author: yinwenjie
 * @email: 475660997@qq.com
 * @date: 2019/8/6
 * @description:
 * I tried to realise the ADT of polynomial in one class. But I found that it's difficult to do that.
 * In this SparsePolynomial class, I use array list to construct the data structure, and just realize
 * the method of 'add(poly1 , poly2)'. From the Internet, linked list is given as the data structure and
 * I will finish it later.
 *
 * @result:
 * <coef, expon>: <8.8, 8> <4.4, 4> <3.3, 3> <2.2, 2> <1.1, 1>
 * <coef, expon>: <7.7, 7> <6.6, 6> <1.1, 1>
 * <coef, expon>: <8.8, 8> <7.7, 7> <6.6, 6> <4.4, 4> <3.3, 3> <2.2, 2> <2.2, 1>
 */
public class SparsePolynomial {
    public static Tuple[] add(Tuple[] poly1, Tuple[] poly2){
        Tuple[] tmp1 = poly1;
        Tuple[] tmp2 = poly2;
        Tuple[] result = null;
        while(! isZero(tmp1) && !isZero(tmp2)){
            if(maxExponent(tmp1) > maxExponent(tmp2)){
                result = insert(result, coefficient(tmp1, maxExponent(tmp1)) , maxExponent(tmp1));
                tmp1 = remove(tmp1, maxExponent(tmp1));
            }else if (maxExponent(tmp1) == maxExponent(tmp2)){
                double tmp_coef = coefficient(tmp1, maxExponent(tmp1)) + coefficient(tmp2, maxExponent(tmp2));
                result = insert(result, tmp_coef, maxExponent(tmp1));
                tmp1 = remove(tmp1, maxExponent(tmp1));
                tmp2 = remove(tmp2, maxExponent(tmp2));
            }else {
                result = insert(result, coefficient(tmp2, maxExponent(tmp2)) , maxExponent(tmp2));
                tmp2 = remove(tmp2, maxExponent(tmp2));
            }
        }
        while (! isZero(tmp1)){
            result = insert(result, coefficient(tmp1, maxExponent(tmp1)) , maxExponent(tmp1));
            tmp1 = remove(tmp1, maxExponent(tmp1));
        }
        while (! isZero(tmp2)){
            result = insert(result, coefficient(tmp2, maxExponent(tmp2)) , maxExponent(tmp2));
            tmp2 = remove(tmp2, maxExponent(tmp2));
        }
        return result;
    }

    public static void sortedDec(Tuple[] poly){
        Tuple tmp = null;
        int min = 0;
        for(int i = 0; i < poly.length; i++){
            min = i;
            for(int j = i; j < poly.length; j++)
                if(poly[min].expon < poly[j].expon)
                    min = j;
            tmp = poly[i];
            poly[i] = poly[min];
            poly[min] = tmp;
        }
    }

    public static boolean isZero(Tuple[] p){
        return p.length == 0;
    };

    public static double coefficient(Tuple[] p, int e){
        if(!isZero(p))
            for(Tuple entry : p)
                if(entry.expon == e)
                    return entry.coef;
        return 0;
    };

    public static int maxExponent(Tuple[] p){
        return p[0] == null ? 0 : p[0].expon;
    };

    public static Tuple[] insert(Tuple[] p, double c, int e){
        if(p == null) {
            Tuple[] tmp = new Tuple[1];
            tmp[0] =  new Tuple(c,e);
            return tmp;
        }

        Tuple[] tmp = new Tuple[p.length + 1];
        int j = 0;
        if(e >= p[0].expon){
            tmp[0] = new Tuple(c, e) ;
            for(int i = 0; i < p.length; i++){
                tmp[i+1] = new Tuple(p[i].coef, p[i].expon) ;
            }
        }else if (e <= p[p.length - 1].expon){
            for(int i = 0; i < p.length; i++){
                tmp[i] = new Tuple(p[i].coef, p[i].expon) ;
            }
            tmp[p.length] = new Tuple(c, e) ;
        }else {
            for(int i = 0; i < p.length; i++){
                if( e > p[i].expon && e < p[i-1].expon){
                    tmp[i+j] = new Tuple(c, e) ;
                    j += 1;
                }
                tmp[i+j] = new Tuple(p[i].coef, p[i].expon);
            }
        }
        return tmp;
    };

    public static Tuple[] remove(Tuple[] p, int e){
        Tuple[] tmp = new Tuple[p.length - 1];
        int j = 0;
        for(int i = 0; i < p.length; i++){
            if(p[i].expon != e){
                tmp[j] = new Tuple(p[i].coef, p[i].expon) ;
                j++;
            }
        }
        return tmp;
    };

    public static String polyString(Tuple[] poly){
        String str = "<coef, expon>: ";
        for(int i = 0; i < poly.length; i++){
            if(poly[i] != null)
                str += "<"+ poly[i].coef +", "+ poly[i].expon +"> ";
        }
        return str;
    }

    public static void main(String[] args){
        Tuple[] poly1 = {new Tuple(1.1,1),new Tuple(4.4,4),
                new Tuple(3.3,3), new Tuple(2.2,2),
                new Tuple(8.8,8)};
        sortedDec(poly1);
        System.out.println(polyString(poly1));

        Tuple[] poly2 = {new Tuple(6.6,6), new Tuple(1.1,1), new Tuple(7.7,7)};
        sortedDec(poly2);
        System.out.println(polyString(poly2));

        Tuple[] result = add(poly1, poly2);
        System.out.println(polyString(result));
    }
}
