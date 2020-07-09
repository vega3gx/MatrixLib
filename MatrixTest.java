 // #------------------------------------------------------------------------------
 // # Aaron Wood
 // # aamwood
 // # CS 101 
 // # 5/13/2019
 // # Tests Matrix ADT
 // #------------------------------------------------------------------------------

public class MatrixTest{
   public static void main(String[] args){
      int i, j, n=100;
      Matrix A = new Matrix(n);
      Matrix B = new Matrix(n);

      A.changeEntry(3,7,18); 
      B.changeEntry(1,10,8);
      A.changeEntry(4,4,6); 
      B.changeEntry(2,6,12);
      A.changeEntry(8,2,24); 
      B.changeEntry(4,1,24);
      A.changeEntry(8,7,12);
      B.changeEntry(6,5,4);
      B.changeEntry(8,5,16);
      B.changeEntry(9,6,20);


      System.out.println(A);
      System.out.println(B);

      A=A.scalarMult(4);
      System.out.println(A);
      A=A.scalarMult(.25);
      System.out.println(A);


      Matrix C = B.add(B);
      System.out.println(C);

      Matrix E = A.sub(A);
      System.out.println(E.getNNZ());
      System.out.println(E);

      Matrix F = B.transpose();
      System.out.println(F);

      Matrix G = B.mult(F);
      System.out.println(G);

      Matrix D = A.mult(B);
      System.out.println(D);

      Matrix H = A.copy();
      System.out.println(H.getNNZ());
      System.out.println(H);
      System.out.println(A.equals(H));
      System.out.println(A.equals(B));
      System.out.println(A.equals(A));

      A.makeZero();
      System.out.println(A.getNNZ());
      System.out.println(A);
   }
}