 // #------------------------------------------------------------------------------
 // # Aaron Wood
 // # 5/13/2019
 // # Defines all variables and functions for List 
 // #------------------------------------------------------------------------------


public class List {

   // private inner Node class
    private class Node {
        Object value;
        Node next;
        Node prev;

        Node(Object x){
            value = x;
            next = null;
            prev = null;
        }

		//determines if nodes are equal
		//only use of object node contains is primative
		//will return false if not primative, 
        public boolean equals(Object x){
            boolean eq = false;
            Node that;
            if(x instanceof Node){
                that = (Node) x;
                eq = (this.value==that.value);
            }
            return eq;
        }
    }

   // Fields for the List class
    private Node head;
    private Node tail;
    private Node cursor;   //cursor can point to one particular object in list
    private int numItems;  // number of items in this List

   // constructor for the List class
    public List(){
        head = null;
        tail = null;
        cursor = null;
        numItems = 0;
    }


   // private helper function -------------------------------------------------

   // length()
   // pre: none
   // returns the number of entries in this list
    public int length(){
        return(numItems);
    }

	//public functions --------------------------------------------------------

    //index()
    //pre: none
    //returns the index of the current cursor
    //if cursor undefined, returns -1
    public int index(){
        if(cursor==null){
        	return(-1);
        }
        else{
            int i;
            Node N = head;
            for(i=0;N!=cursor;i++){ //step through until Node N==cursor
                if(N.next!=null){
                	N=N.next;
                }
                else{
                	return(-1);
                }
            }
            return(i);
        }
    }

    //front()
    //pre: length()!=0
    //returns value of front item
    public Object front(){
        if(head==null){
        	throw new RuntimeException("List Error: front() called on empty list");
        }
        else{
        	return(head.value);
        }
    }

    //back()
    //pre: length()!=0
    //returns value of back item
    public Object back(){
        if(tail==null){
        	throw new RuntimeException("List Error: back() called on empty list");
        }
        else{
        	return(tail.value);
        }
    }

    //get()
    //pre: lenght()!=0 && index!=-1
    //returns value of item pointed to by cursor
    public Object get(){
        if(head==null){
            throw new RuntimeException("List Error: get() called on empty list");
            }
        else if(cursor==null){
            throw new RuntimeException("List Error: get() called on undefined cursor");
        }
        else{
        	return(cursor.value);
        }
    }

    //equals()
    //pre: none
    //returns true if both lists are identical
    //only use if list nodes contain primatives
    public boolean equals(Object x){
        boolean eq  = false;
        List L;
        Node N, M;

        if(x instanceof List){
            L = (List)x;
            N = this.head;
            M = L.head;
            eq = (this.numItems==L.numItems); //if lists have different lengths, they are not equal
            while( eq && N!=null ){
                eq = N.equals(M);		//uses Node equals() function, returns false if values are not primatives
                N = N.next;
                M = M.next;
                //step through both lists comparing values, quit if false
            }
        }
        return eq;
    }

    //clear()
    //pre: none
    //empties out all contents of list
    public void clear(){
        head =null;
        tail = null;
        cursor = null;
        numItems = 0;
    }


    //moveFront()
    //pre: none
    //moves cursor to front element
    public void moveFront(){
        if(numItems!=0){
            cursor = head;
        }
    }

    //moveBack()
    //pre: none
    //moves cursor to back element
    public void moveBack(){
        if(numItems!=0){
            cursor=tail;
        }
    }

   // append()
   // inserts new item into end of list
   // pre: none
    public void append(Object value){
    if(numItems==0){    //if no nodes in list
        head = new Node(value);
        tail = head; //if only one node exists, node is both head and tail
        numItems++;
    }else{   
        Node N = new Node(value);
        Node E = tail;
        E.next = N;		//previous tail node points to new node
        N.prev = E; 	//place new node at end of list
        tail = N;		//new node is now tail
        numItems++;
        }
    }

    // prepend()
   // inserts new item into beginning of list
   // pre: none
    public void prepend(Object value){
    if(numItems==0){		//if no nodes in list
        head = new Node(value);
        tail = head; //set head if list hasn't been created yet
        numItems++;
    }else{  
        Node N = new Node(value);
        Node E = head;
        N.next = E;		//new next points to previous head
        E.prev = N; //place new node at end of list
        head = N;		//new node is now head
        numItems++;
        }
    }
    //insertBefore
    //pre: lenght()!=0 && index!=-1
    //inserts data into list before cursor
    public void insertBefore(Object data){
        if(head==null){
            throw new RuntimeException("List Error: insertBefore() called on empty list");
            }
        else if(cursor==null){
            throw new RuntimeException("List Error: insertBefore() called on undefined cursor");
        }else if(cursor==head){
        	this.prepend(data);
        }else{
            Node N = new Node(data);
            Node P = cursor.prev;
            cursor.prev=N;		//place Node N between cursor and previous node
            P.next=N;
            N.next=cursor;
            N.prev=P;
            numItems++;
        }
    }

    //insertAfter
    //pre: lenght()!=0 && index!=-1
    //inserts data into list after cursor
    public void insertAfter(Object data){
        if(head==null){
            throw new RuntimeException("List Error: insertAfter() called on empty list");
            }
        else if(cursor==null){
            throw new RuntimeException("List Error: insertAfter() called on undefined cursor");
        }else if(cursor == tail){
            this.append(data);
        }else{
            Node N = new Node(data);
            Node A = cursor.next;
            cursor.next=N;		//place Node N after cursor
            A.prev=N;
            N.prev=cursor;
            N.next=A;
            numItems++;
        }
    }

    //movePrev()
    //pre: none
    //moves cursor to previous element
    public void movePrev(){
        if(cursor!=null){
        	cursor=cursor.prev;
        }
    }

    //moveNext()
    //pre: none
    //moves cursor to next element
    public void moveNext(){
        if(cursor!=null){
        	cursor=cursor.next;
        }
    }
    // delete()
    // deletes element pointed to by cursor
    // pre: index()!=-1
    public void delete(){
        if(head==null){
            throw new RuntimeException("List Error: delete() called on empty list");
        }
        else if(cursor==null){
            throw new RuntimeException("List Error: delete() called on undefined cursor");
        }
        else if(numItems==1){
            this.clear();
        }else if(cursor==head){
            this.deletefront();
        }
        else if(cursor == tail){
            this.deleteback();
        }else{
            Node N = cursor.next;		//remove all pointers to cursor
            Node P = cursor.prev;
            P.next=N;
            N.prev=P;
            cursor=null;
            numItems--;
        }
    }

    //deleteFront()
    //pre: numItems!=0
    //deletes element at front of list
    public void deleteFront(){
        if(head==null){
            throw new RuntimeException("List Error: deleteFront() called on empty list");
        }
        if(head==cursor){
        	cursor=null;
        }
        head=head.next;
        numItems--;
        if(numItems!=0){		//delete head by removing pointer to it
        	head.prev=null;
        }
        else{
            this.clear();
        }
    }

    //deleteBack()
    //pre: numItems!=0
    //deletes element at back of list    
    public void deleteBack(){
        if(head==null){
            throw new RuntimeException("List Error: deleteBack() called on empty list");
        }
        if(tail==cursor){
        	cursor=null;
        }
        tail=tail.prev;
        numItems--;
        if(numItems!=0){
        	tail.next=null;		//delete tail by removing pointer to it
        }
        else{
        	this.clear();
        }
    }

    //copy()
    //pre: none
    //returns refrence to identical list
    //only use on primatives, DOES NOT WORK WITH COMPLEX OBJECTS
    //if used with object, both lists will contain pointers to same object
    List copy(){
        List Copy = new List();
        if(this.numItems==0){
        	return(Copy);		//made a copy of an empty list
        }
        this.moveFront();
        while(this.index()!=-1){
            Copy.append(this.get());		//copy primative
            this.moveNext();
        }
        return(Copy);
    }

    // toString()
    // returns a String representation of this Dictionary
    // overrides Object's toString() method
    // pre: none
    public String toString(){
        if(head!=null){
            Node N=head;
            String returnVal="";
            while(N.next!=null){
                returnVal=returnVal+(N.value+" ");
                N=N.next;
            }
            returnVal=returnVal+(N.value+" ");
            return(returnVal);
        }else{
            return("");
        }
    }
}