package Chapter2.matrix;

/**
 * @author: yinwenjie
 * @email: 475660997@qq.com
 * @date: 2019/8/6
 * @description:
 * According to the algorithm of the book, the code is finished.
 * @result:
 *       2  0  0  3  0  1           1  0  0           3  0  0
 *       0  2  4  0  0  0           0  0  1           0  4  2
 *   a = 0  0  0  0  0  0      b =  0  1  0    a * b =0  0  0
 *       0  0  0  3  0  0           0  0  0           0  0  0
 *       0  0  0  0  5  0           0  0  0           0  0  0
 *                                  1  0  0
 */
public class SparseMatrix {

    public static Triple[] multiply(Triple[] a, Triple[] b){
        if(a[0].column != b[0].row) return null;
        Triple[] result = {new Triple(a[0].row, b[0].column, 0)};
        Triple[] transpose_b = transpose(b);
        //add a sentinel to deal with the boundary condition
        a = insert(a, a[0].row,0,0);
        transpose_b = insert(transpose_b, b[0].column,0,0);

        int row = a[1].row;
        int row_begin = 1;
        int sum = 0;
        for(int i = 1; i <= a[0].value; ){
            int column = transpose_b[1].row;
            for(int j = 1; j <= b[0].value + 1;){
                if(a[i].row != row){
                    result = insert(result, row, column, sum);
                    sum = 0;
                    i = row_begin;
                    for(; column == transpose_b[j].row; j++)
                        ;
                    column = transpose_b[j].row;
                }else if(transpose_b[j].row != column){
                    result = insert(result, row, column, sum);
                    sum = 0;
                    i =row_begin;
                    column = transpose_b[j].row;
                }else{
                    if(a[i].column == transpose_b[j].column){
                        sum += a[i++].value * transpose_b[j++].value;
                    }else if(a[i].column > transpose_b[j].column){
                        j++;
                    }else {
                        i++;
                    }
                }
            }
            for(; row == a[i].row; i++)
                ;
            row = a[i].row;
            row_begin = i;
        }


        return result;
    }

    public static Triple[] insert(Triple[] result, int row, int column, int value){
        Triple[] tmp = new Triple[result.length+1];
        for (int i = 0; i < result.length; i++)
            tmp[i] = result[i];
        tmp[result.length] = new Triple(row, column, value);
        return tmp;
    }

    public static Triple[] transpose(Triple[] m){
        Triple[] result = new Triple[m.length];
        result[0] = new Triple(m[0].row, m[0].column, m[0].value);

        int n = m[0].value;
        int current = 1;
        for(int i = 0; i < m[0].column; i++)
            for(int j = 1; j <= n; j++){
                if(m[j].column == i){
                    result[current] = new Triple(m[j].column, m[j].row, m[j].value);
                    current++;
                }
            }
        return result;
    }

    public static int getValue(Triple[] m, int row, int column){
        for(int i = 1; i < m.length; i++)
            if(m[i].row == row && m[i].column == column)
                return m[i].value;
        return 0;
    }

    public static String printMatrix(Triple[] m){
        String str = "";
        int row = m[0].row;
        int column = m[0].column;
        for(int i = 0; i < row; i++){
            for(int j = 0; j < column; j++)
                str = str + getValue(m, i, j) + "  ";
            str += "\n";
        }
        return str;
    }


    public static void main(String[] args){
        Triple[] a = {
                new Triple(5, 6, 7), new Triple(0, 0, 2),
                new Triple(0, 3, 3), new Triple(0, 5, 1),
                new Triple(1, 1, 2), new Triple(1, 2, 4),
                new Triple(3, 3, 3), new Triple(4, 4, 5)
        };
        System.out.println(printMatrix(a));

        System.out.println("============>");

        Triple[] b = {
                new Triple(6, 3, 4), new Triple(0, 0, 1),
                new Triple(1, 2, 1), new Triple(2, 1, 1),
                new Triple(5, 0, 1)
        };
        System.out.println(printMatrix(b));

        System.out.println("============>");
        System.out.println(printMatrix(multiply(a, b)));

    }
}
