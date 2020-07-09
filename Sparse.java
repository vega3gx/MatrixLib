 // #------------------------------------------------------------------------------
 // # Aaron Wood
 // # aamwood
 // # CS 101 
 // # 5/13/2019
 // # implements Matrix ADT
 // #------------------------------------------------------------------------------
import java.io.*;
import java.util.Scanner;

class Sparse{

    public static void main(String[] args){
        if(args.length != 2){ //if improper number of arguments
            System.err.println("Usage: Sparse Source Target");
            System.exit(1);
        }
        Scanner in = null;
        try{
            in = new Scanner(new File(args[0]));
        }
        catch(Exception e){
            System.err.println("File " +args[0]+ " not found");
            System.exit(1);
        }
        PrintWriter out = null;
        try{
            out = new PrintWriter(new FileWriter(args[1]));
        }
        catch(Exception e){
            System.err.println("File " +args[1]+ " not found");
        }
        in.useDelimiter("\\Z"); // matches the end of file character
        String s = in.next();   // read in whole file as a single String
        in.close();
        String[] words = s.split("\\n");   // split s into individual lines
        int lineCount = words.length;     // extract length of the resulting array
        //for(int i=0;i<lineCount;i++){System.out.println(words[i]);}
        String[] headers = words[0].split(" ");
        int size = Integer.parseInt(headers[0]);
        int anze = Integer.parseInt(headers[1]);
        int bnze = Integer.parseInt(headers[2]);
        //System.out.println("size="+size+" A="+anze+" B="+bnze);
        Matrix A = new Matrix(size);
        Matrix B = new Matrix(size);
        int i;
        for(i=2;i<2+anze;i++){
            String[] entry = words[i].split(" ");
            int row = Integer.parseInt(entry[0]);
            int col = Integer.parseInt(entry[1]);
            double val = Double.parseDouble(entry[2]);
            A.changeEntry(row,col,val);
            //System.out.println(A);
        }
        if(A.getNNZ()==1){out.println("A has 1 non-zero entry:");}
    	else{out.println("A has "+A.getNNZ()+" non-zero entries:");}
        out.println(A);
        //System.out.println(i);
        int j=i;
        for(i++;i<=j+bnze;i++){
            String[] entry = words[i].split(" ");
            int row = Integer.parseInt(entry[0]);
            int col = Integer.parseInt(entry[1]);
            double val = Double.parseDouble(entry[2]);
            B.changeEntry(row,col,val);
            //System.out.println(B);
        }
        if(B.getNNZ()==1){out.println("B has 1 non-zero entry:");}
    	else{out.println("B has "+B.getNNZ()+" non-zero entries:");}
        out.println(B);
        out.println("(1.5)*A = ");
        out.println(A.scalarMult(1.5));
        out.println("A+B = ");
        out.println(A.add(B));
        out.println("A+A = ");
        out.println(A.add(A));
        out.println("B-A = ");
        out.println(B.sub(A));
        out.println("A-A = ");
        out.println(A.sub(A));
        out.println("Transpose(A) = ");
        out.println(A.transpose());
        out.println("A*B = ");
        out.println(A.mult(B));
        out.println("B*B = ");
        out.println(B.mult(B));
        in.close();
        out.close();
    }

}