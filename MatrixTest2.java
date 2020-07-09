public class MatrixTest2{
public static void main(String[] args){
    Matrix A = new Matrix(10);
    Matrix B = new Matrix(15);
    A.changeEntry(1, 1, 1);
    B.changeEntry(1, 1, 1);
    if (A.equals(B)) System.out.println("fail 1");
    B = new Matrix(10);
    A.changeEntry(1, 1, 1);
    A.changeEntry(1, 3, 1);
    B.changeEntry(1, 1, 1);
    B.changeEntry(1, 3, 1);
    if (!A.equals(B)) System.out.println("fail 2");
    A.changeEntry(1, 3, 0);
    if (A.equals(B)) System.out.println("fail 3");
    A.changeEntry(1, 1, 0);
    B.makeZero();
    A.changeEntry(10, 10, 10);
    B.changeEntry(10, 10, 10);
    if (!A.equals(B)){ 
        System.out.println("fail 4");
        System.out.println(A);
        System.out.println(B);
    }
    A = new Matrix(100);
    B = new Matrix(100);
    int valcount = 1;
    for (int j = 1; j <= 10; j++) {
        for (int k = 1; k <= 10; k++) {
    // hint: this is 1-10000 left-to-right row-by-row
                A.changeEntry(j, k, valcount++);
            }
        B.changeEntry(j, j, 1); // hint: this is the identity matrix
    }
    System.out.println(A);
    Matrix C = A.scalarMult(2);
    if (!C.equals(A.add(A))) {
        System.out.println("fail 5");
        //System.out.println(A.add(A));
        //System.out.println(C);
    }
    C = A.scalarMult(-2);
    if (!C.equals(A.sub(A).sub(A).sub(A))) System.out.println("fail 6");
    C = A.mult(B);
    if (!C.equals(A)) System.out.println("fail 7");
    }
}