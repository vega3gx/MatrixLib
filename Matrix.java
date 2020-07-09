public class Matrix{

	private class Entry {
        int column;
        double value;

        Entry(int col, double val){
            value = val;
            column = col;
        }

        public boolean equals(Object x){
            boolean eq = false;
            Entry that;
            if(x instanceof Entry){
                that = (Entry) x;
                eq = ((this.value==that.value)&&(this.column==that.column));
            }
            return eq;
        }

        public String toString(){
        	String retVal = "("+(column+1)+value+")";
        	return("("+(column+1)+", "+value+")");
        }
    }

    private List[] row;
    private int size;
	// Constructor
	Matrix(int n){ // Makes a new n x n zero Matrix. pre: n>=1
		if(n<=1){throw new RuntimeException("Matrix Error: Matrix() n must be positive integer");}
		else{
			row = new List[n];
			for(int i=0;i<n;i++){
				row[i]=null;
			}
			size=n;
		}
	}

	// Access functions
	int getSize(){ // Returns n, the number of rows and columns of this Matrix
		if(row==null){return(0);}
		else{return(size);}
	}

	int getNNZ(){ // Returns the number of non-zero entries in this Matrix
		int retVal=0;
		for(int i=0;i<size;i++){
			if(row[i]!=null){retVal+=row[i].length();}
		}
		return(retVal);
	}

	public boolean equals(Object x){ // overrides Object's equals() method
		boolean eq  = false;
        Matrix M;
        List N,O;
        int i=0;

        if(x instanceof Matrix){
            M = (Matrix)x;
            eq = (this.getSize()==M.getSize());
            if(!eq){System.out.println("Broke at size");}
            for(i=0;eq && i<this.getSize();i++){
            	N=this.row[i];
            	O=M.row[i];
            	if(O==null || N==null){
            		eq &= (!(O==null ^ N==null));
            		if(!eq){System.out.println("Broke at null");}
            	}else{
            		N.moveFront();
            		O.moveFront();
            		eq &= (N.length()==O.length());
            		if(!eq){System.out.println("Broke at length");}
            		while(eq && N.index()!=-1){
            			Entry E = (Entry)N.get();
            			Entry F = (Entry)O.get();
            			eq &= E.equals(F);
            			if(!eq){
            				System.out.println("Broke at entry");
            				System.out.println(E);
            				System.out.println(F);
            			}
            			N.moveNext();
            			O.moveNext();
            		}
            	}
            }
        }
        return eq;
	}

	// Manipulation procedures
	void makeZero(){ // sets this Matrix to the zero state
		for(int i=0;i<size;i++){
			if(row[i]!=null){
				row[i].clear();
				row[i]=null;
			}
		}
	}

	Matrix copy(){// returns a new Matrix having the same entries as this Matrix
		Matrix Copy = new Matrix(this.getSize());
		if(this.size==0){return(Copy);}
		for(int i=0;i<size;i++){
			if(this.row[i]!=null){
				Copy.row[i]=new List();
				if(this.row[i].length()==0){return(Copy);}
    			this.row[i].moveFront();
        		while(this.row[i].index()!=-1){
        			Entry E=(Entry)this.row[i].get();
            		Copy.row[i].append(new Entry(E.column,E.value));
            		this.row[i].moveNext();
        		}
			}
		}
		return(Copy);
	}

	// changes ith row, jth column of this Matrix to x
	 // pre: 1<=i<=getSize(), 1<=j<=getSize()
	void changeEntry(int i, int j, double x){
		if(i>size||j>size||i<1||j<1){
			throw new RuntimeException("Matrix Error: changeEntry() index out of bounds");
		}
		i--;
		j--;
		if(x==0){
			if(row[i]!=null){
				List R = row[i];
				Entry B = (Entry)R.back();
				Entry F = (Entry)R.front();
				if(B.column==j){R.deleteBack();}
				else if(F.column==j){R.deleteFront();}
				else if((B.column!=j) && (F.column!=j)){
					R.moveFront();
					Entry E = (Entry)R.get();
					while((E.column<x)&&(R.index()!=-1)){
						R.moveNext();
						E=(Entry)R.get();
					}
					if(E.column==j){
						R.delete();
					}
				}
				if(R.length()==0){row[i]=null;}
			}
		}else{
			if(row[i]==null){
				row[i]=new List();
				row[i].prepend(new Entry(j,x));
			}else{
				List R = row[i];
				Entry B= (Entry) row[i].back();
				Entry F= (Entry) row[i].front();
				if(B.column<j){row[i].append(new Entry(j,x));}
				else if(F.column>j){row[i].prepend(new Entry(j,x));}
				else{
					R.moveFront();
					Entry E=(Entry)R.get();
					while((E.column<j)&&(R.index()!=-1)){
						//System.out.println(E);
						E=(Entry)R.get();
						R.moveNext();
						E=(Entry)R.get();
						//System.out.println(E);
					}
					if(E.column==j){
						E.value=x;
					}else{
						R.insertBefore(new Entry(j,x));
					}
				}
			}
			//System.out.println("placed in row"+i);
		}
	}

	// returns a new Matrix that is the scalar product of this Matrix with x
	Matrix scalarMult(double x){
		Matrix P = this.copy();
		for(int i=0;i<P.getSize();i++){
			if(P.row[i]!=null){
				P.row[i].moveFront();
				while(P.row[i].index()!=-1){
					Entry E = (Entry)P.row[i].get();
					E.value=E.value*x;
					P.row[i].moveNext();
				}
			}
		}
		return(P);
	}

	// returns a new Matrix that is the sum of this Matrix with M
	// pre: getSize()==M.getSize()
	Matrix add(Matrix M){
		if(this.getSize()!=M.getSize()){throw new RuntimeException("Matrix Error: add() called on matricies of different sizes");}
		Matrix S = new Matrix(this.getSize());
		if(this==M){S=this.scalarMult(2);}
		else{
			for(int i=0;i<this.getSize();i++){
				List Q = this.row[i];
				List R = M.row[i];
				if(Q==null&&R==null){S.row[i]=null;}
				else if(Q==null){
					S.row[i]=new List();
	    			R.moveFront();
	        		while(R.index()!=-1){
	        			Entry E=(Entry)R.get();
	            		S.row[i].append(new Entry(E.column,E.value));
	            		R.moveNext();
	            	}
				}
				else if(R==null){
					S.row[i]=new List();
	    			Q.moveFront();
	        		while(Q.index()!=-1){
	        			Entry E=(Entry)Q.get();
	            		S.row[i].append(new Entry(E.column,E.value));
	            		Q.moveNext();
	            	}
				}
				else{
					Q.moveFront();
					R.moveFront();
					Entry E = (Entry)Q.get();
					Entry F = (Entry)R.get();
					S.row[i]=new List();
					while((Q.index()!=-1)&&(R.index()!=-1)){
						E = (Entry)Q.get();
						F = (Entry)R.get();
						//System.out.println("E col val:"+E.column+"F col val:"+F.column);
						if(E.column==F.column){
							if((E.value+F.value)!=0){
								S.row[i].append(new Entry(E.column,(E.value+F.value)));
								//System.out.println("E="+E.value);
								//System.out.println("F="+F.value);
								//System.out.println("new="+S.row[i].back());
							}
							Q.moveNext();
							R.moveNext();
						}else if(E.column<F.column){
							S.row[i].append(new Entry(E.column,E.value));
							Q.moveNext();
						}else if(E.column>F.column){
							S.row[i].append(new Entry(F.column,F.value));
							R.moveNext();
						}
					}
					while(Q.index()!=-1){
						E = (Entry)Q.get();
						S.row[i].append(new Entry(E.column,E.value));
						Q.moveNext();
					}
					while(R.index()!=-1){
						F=(Entry)R.get();
						S.row[i].append(new Entry(F.column,F.value));
						R.moveNext();
					}
					if(S.row[i].length()==0){S.row[i]=null;}
				}
			}
		}
		return(S);
	}

	// returns a new Matrix that is the difference of this Matrix with M
	// pre: getSize()==M.getSize()
	Matrix sub(Matrix M){
		if(this.getSize()!=M.getSize()){throw new RuntimeException("Matrix Error: sub() called on matricies of different sizes");}
		if(this==M){M=this.copy();}
		Matrix S = new Matrix(this.getSize());
		for(int i=0;i<this.getSize();i++){
			List Q = this.row[i];
			List R = M.row[i];
			if(Q==null&&R==null){S.row[i]=null;}
			else if(Q==null){
				S.row[i]=new List();
    			R.moveFront();
        		while(R.index()!=-1){
        			Entry E=(Entry)R.get();
            		S.row[i].append(new Entry(E.column,-1*E.value));
            		R.moveNext();
            	}
			}
			else if(R==null){
				S.row[i]=new List();
    			Q.moveFront();
        		while(Q.index()!=-1){
        			Entry E=(Entry)Q.get();
            		S.row[i].append(new Entry(E.column,E.value));
            		Q.moveNext();
            	}
			}
			else{
				Q.moveFront();
				R.moveFront();
				Entry E = (Entry)Q.get();
				Entry F = (Entry)R.get();
				S.row[i]=new List();
				while((Q.index()!=-1)&&(R.index()!=-1)){
					E = (Entry)Q.get();
					F = (Entry)R.get();
					//System.out.println("Subtracting"+E+F);
					if(E.column==F.column){
						if((E.value-F.value)!=0){
							S.row[i].append(new Entry(E.column,(E.value-F.value)));
						}//else{System.out.println("ZEROED at "+i+" "+E.column);}
						Q.moveNext();
						R.moveNext();
					}
					else if(E.column<F.column){
						S.row[i].append(new Entry(E.column,E.value));
						Q.moveNext();
					}else if(E.column>F.column){
						S.row[i].append(new Entry(F.column,-1*F.value));
						R.moveNext();
					}
				}
				while(Q.index()!=-1){
					E = (Entry)Q.get();
					S.row[i].append(new Entry(E.column,E.value));
					Q.moveNext();
				}
				while(R.index()!=-1){
					F = (Entry)R.get();
					S.row[i].append(new Entry(F.column,-1*F.value));
					R.moveNext();
				}
				if(S.row[i].length()==0){S.row[i]=null;}
			}
		}
		return(S);
	}
	
	Matrix transpose(){
		Matrix T = new Matrix(this.getSize());
		for(int i=0;i<this.getSize();i++){
			if(this.row[i]!=null){
				List R=this.row[i];
				for(R.moveFront();R.index()!=-1;R.moveNext()){
					Entry E = (Entry)R.get();
					if(T.row[E.column]==null){
						T.row[E.column]=new List();
					}
					T.row[E.column].append(new Entry(i,E.value));
				}
			}
		}
		return(T);
	}

	// returns a new Matrix that is the product of this Matrix with M
	// pre: getSize()==M.getSize()
	Matrix mult(Matrix M){
		Matrix A = this;
		Matrix B = M.transpose();
		if(A.getSize()!=B.getSize()){throw new RuntimeException("Matrix Error: mult() called on matricies of different sizes");}
		Matrix P = new Matrix(A.getSize());
		for(int k=0;k<P.getSize();k++){P.row[k]=new List();}
		for(int i=0;i<P.getSize();i++){
			if(A.row[i]!=null){
				for(int j=0;j<P.getSize();j++){
					if(B.row[j]!=null){
						P.row[i].append(new Entry(j,dotProd(A.row[i],B.row[j])));
						Entry C = (Entry)P.row[i].back();
						if(C.value==0){P.row[i].deleteBack();}
					}
				}
			}
		}
		for(int k=0;k<P.getSize();k++){
			if(P.row[k].length()==0){P.row[k]=null;}
		}
		return(P);
	}

	// Other functions
	public String toString(){ // overrides Object's toString() method
		String retVal="";
		for(int i=1;i<=size;i++){
			if(row[i-1]!=null){
				retVal=retVal+i+": ";
				for(row[i-1].moveFront();row[i-1].index()!=-1;row[i-1].moveNext()){retVal=retVal+(Entry)row[i-1].get()+" ";}
				retVal=retVal+"\n";
			}
		}
		return(retVal);
	}

	private double dotProd(List I, List J){
		I.moveFront();
		J.moveFront();
		double retVal =0;
		while((I.index()!=-1)&&(J.index()!=-1)){
			Entry E = (Entry)I.get();
			Entry F = (Entry)J.get();
			if(E.column==F.column){
				retVal+=(E.value*F.value);
				I.moveNext();
				J.moveNext();
			}
			else if(E.column<F.column){
				I.moveNext();
			}else if(E.column>F.column){
				J.moveNext();
			}
		}
		return(retVal);
	}
}
