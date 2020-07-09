// #------------------------------------------------------------------------------
 // # Aaron Wood
 // # aamwood
 // # CS 101 
 // # 5/13/2019
 // # tests List ADT
 // #------------------------------------------------------------------------------
public class ListTest{
      public static void main(String[] args){
      List A = new List();
      List B = new List();

      for(int i=1; i<=20; i++){
            A.append(i);
            B.prepend(i);
      }
      System.out.println(A);
      System.out.println(B);

      for(A.moveFront(); A.index()>=0; A.moveNext()){
          System.out.print(A.get()+" ");
      }
      System.out.println();
      for(B.moveBack(); B.index()>=0; B.movePrev()){
          System.out.print(B.get()+" ");
      }
      System.out.println();

      List C = A.copy();
      System.out.println(A.equals(B));
      System.out.println(B.equals(C));
      System.out.println(C.equals(A));

      System.out.println("List C is:");
      System.out.println(C);
      System.out.println("\n");

      A.moveFront();
      for(int i=0; i<5; i++) A.moveNext(); // at index 5
      A.insertBefore(-1);                  // at index 6
      for(int i=0; i<9; i++) A.moveNext(); // at index 15
      A.insertAfter(-2);
      for(int i=0; i<5; i++) A.movePrev(); // at index 10
      A.delete();
      System.out.println(A);
      System.out.println(A.length());
      A.clear();
      System.out.println(A.length());
      B.clear();

      B = C.copy();
      List D = null;
      B.moveFront();
      B.movePrev();
      C.moveFront();


      /*
      A is empty list
      B is list with undefined cursor
      C is list with defined cursor
      D is null
      */


      //to string test
      System.out.println(A);
      System.out.println(B);
      System.out.println(C);
      System.out.println();
      //length test
      System.out.println(A.length());
      System.out.println(B.length());
      System.out.println(C.length());
      System.out.println();
      //to index test
      System.out.println(A.index());
      System.out.println(B.index());
      System.out.println(C.index());
      System.out.println();
      //front test
      try{
          System.out.println(A.front());
      }
      catch(Exception e){
          System.out.println("Front empty list Exception caught A");
      }
      System.out.println(B.front());
      System.out.println(C.front());
      //try{
      //}
      //catch(Exception e){
      //    System.out.println("Front empty list Exception caught D");
      //}

      //back test

      try{
          System.out.println(A.back());
      }
      catch(Exception e){
          System.out.println("back empty list Exception caught A");
      }
      System.out.println(B.back());
      System.out.println(C.back());
      //try{
      //}
      //catch(Exception e){
      //    System.out.println("back empty list Exception caught D");
      //}

      //get test

      try{
          System.out.println(A.get());
      }
      catch(Exception e){
          System.out.println("Front empty list Exception caught A");
      }
      try{
          System.out.println(B.front());
      }
      catch(Exception e){
          System.out.println("get undefined cursor Exception caught B");
      }
      System.out.println(C.front());
      // try
      // }
      // catch(Exception e){
      //     System.out.println("Front empty list Exception caught D");
      // }

      // equals test
      System.out.println(A.equals(B));
      System.out.println(B.equals(A));
      System.out.println(C.equals(A));
      //System.out.println(D.equals(A));
      System.out.println();
      //append test
      A.append(-69);
      B.append(-69);
      C.append(-69);
      //D.append(-69);
      System.out.println();

      System.out.println(A);
      System.out.println(B);
      System.out.println(C);
      //System.out.println(D);
      System.out.println();
      //deleteBack test
      A.deleteBack();
      B.deleteBack();
      C.deleteBack();
      //D.deleteBack();
      System.out.println();
      //prepend test
      A.prepend(69);
      B.prepend(69);
      C.prepend(69);
      //D.prepend(69);
      System.out.println();

      System.out.println(A);
      System.out.println(B);
      System.out.println(C);
      //System.out.println(D);
      System.out.println();
      //deletefront test
      A.deleteFront();
      try{A.deleteFront();}
      catch(Exception e){System.out.println("deletefront A test caught");}
      B.deleteFront();
      C.deleteFront();
      //D.deleteFront();
      //catch(Exception e){System.out.println("insertBefore D test caught");}
      System.out.println();

      System.out.println(A);
      System.out.println(B);
      System.out.println(C);
      //System.out.println(D);
      System.out.println();
      //insert before test
      try{A.insertBefore(69);}
      catch(Exception e){System.out.println("insertBefore A test caught");}
      try{B.insertBefore(69);}
      catch(Exception e){System.out.println("insertBefore B test caught");}
      C.insertBefore(69);
      //D.insertBefore(69);
      //catch(Exception e){System.out.println("insertBefore D test caught");}
      System.out.println();

      System.out.println(A);
      System.out.println(B);
      System.out.println(C);
      //System.out.println(D);
      System.out.println();
      //movePrev and delete test

      A.movePrev();
      B.movePrev();
      C.movePrev();
      //D.movePrev();
      System.out.println();

      try{A.delete();}
      catch(Exception E){System.out.println("delete A test caught");}
      try{B.delete();}
      catch(Exception E){System.out.println("delete B test caught");}
      System.out.println(C.get());
      C.delete();
      C.moveFront();
      //D.delete();
      System.out.println();

      //get test
      try{System.out.println(A.get());}
      catch(Exception e){System.out.println("get A test caught");}
      try{System.out.println(B.get());}
      catch(Exception e){System.out.println("get B test caught");}
      System.out.println(C.get());
      //System.out.println(D.get());
      //catch(Exception e){System.out.println("get D test caught");}
      System.out.println();
      }
} 