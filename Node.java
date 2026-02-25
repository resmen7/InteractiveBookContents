
package projedeneme01;


public class Node {
    
    private String title;
    private Node next1;
    private Node next2;
    
    public Node(String title){
        this.title=title;
        this.next1=null;
        this.next2=null;
    }
    
    public void setTitle(String title){
        this.title=title;
    }
    public String getTitle(){
        return title;
    }
    
    public void setNext1(Node next){
        this.next1=next;
    }
    public Node getNext1(){
        return next1;
    }
    
    public void setNext2(Node next){
        this.next2=next;
    }
    public Node getNext2(){
        return next2;
    }
    
}
