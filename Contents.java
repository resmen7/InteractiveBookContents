
package projedeneme01;

import java.util.Scanner;


public class Contents {
    
    private Node head1;
    
    public Contents(){
        this.head1=null;
    }
   
    //Scanner'ı Çağırır, Virgüllerden ayırır
    public void bringScanner(){
        Scanner sc=new Scanner(System.in);
        while(true)
        {
            System.out.println();
            System.out.println("Type ? to exit otherwise, Enter input...");
            String input=sc.nextLine();
            if(Length(input)==1 && input.charAt(0)=='?')
            {
                System.out.println("Program finished");
                return;
            }
            else
            {
                int length=Length(input);
                String code="";
                String order="";
                String title="";
                int commaCounter=0;

                for(int i=0;i<length;i++)
                {
                    char c=input.charAt(i);
                    if(c==',')
                    {
                        commaCounter++;
                        continue;
                    }
                    if(c==' ' && commaCounter!=2)
                        continue;
                    if(commaCounter==0)
                        code+=c;
                    else if(commaCounter==1)
                        order+=c;
                    else if(commaCounter==2)
                    {
                        int titleLength=Length(title);
                        if(titleLength==0 && c==' ')
                            continue;
                        title+=c;
                    }
                }
                /*System.out.println(code);
                System.out.println(order);
                System.out.println(title);*/
                
                switch (code){
                    case "Add":
                        addTitle(title,order);
                        break;
                    case "Print":
                        ShowAll2();
                        break;
                    default:
                        System.out.println("Code couldn't find, Add or Print");
                }
            }
        }
    }
    
    
    //String'in uzuluğunu int değer olarak döndürür
    private int Length(String order){
        int count=0;
        try{
            while(true)
            {
                order.charAt(count);
                count++;
            }
        }
        catch(Exception e){
            
        }
        return count;
    }
    
    //Aynı başlık var mı kontrol eder
    private boolean IsItHere(String title,Node temp){
        Node temp2=temp;
        while(temp2!=null)
        {
           if(Length(temp2.getTitle())==Length(title))
           {
               boolean flag=true;
               for(int i=0;i<Length(title);i++)
               {
                   if(temp2.getTitle().charAt(i)!=title.charAt(i))
                   {
                       flag=false;
                       break;
                   }
               }
               if(flag)
               return true;
           }
           
           temp2=temp2.getNext1();
        }
        return false;
    }
    
    public boolean IsPathExist(String order){
        if(head1==null)
            return false;
        Node temp=head1;
        int length=Length(order);
        int num=0;
        
        for(int i=0; i<length;i++)
        {
            char current=order.charAt(i);
            if(current!='.')
                num= num*10+current-'0';
            else
            {
                int move=1;
                
                while(move<num && temp.getNext1()!=null)
                {
                    temp=temp.getNext1();
                    move++;
                }
                if(move<num)
                    return false;
                if(i<length-2){
                    if(temp.getNext2()==null)
                        return false;
                    temp=temp.getNext2();
                }
                    
                    
                num=0;
            }
        }
        return true;
    }
    
    //Başlık ekler, Sıralamayı noktalardan böler
    public void addTitle(String title, String order){
       if (Length(order) > 1) {
        if (!IsPathExist(order)) {
            System.out.println(order + " -> This path does not exist!");
            return;
        }
    }
       
        Node newTitle= new Node(title);
        
        if(Length(order)==1 && order.charAt(0)=='1')
        {
            if(head1==null)
            {
                head1=newTitle;
                return;
            }
            else
            {
                newTitle.setNext1(head1);
                head1=newTitle;
            }
        }
        else
        {
            Node temp=head1;
            int length=Length(order);
            int order2=0;
            
            if(length==1)
            {
                if(IsItHere(title,temp))
                {
                    System.out.println(title+" -This title already exist, it isn't added again!!!");
                    System.out.println();
                    return;
                }
            }
            
            for(int i=0;i<length;i++)
            {
                char current=order.charAt(i);
                if(current!='.')
                {
                    
                    order2=(order2*10)+current-'0';
                    
                    int count=1;
                    Node counterNode=temp;

                    if(i==length-1&& order2==2)
                    {
                        newTitle.setNext1(temp.getNext1());
                        temp.setNext1(newTitle);
                        return;
                    }
                    
                    if(i==length-1)
                    {
                        for(int k=1; k<order2-1 && temp.getNext1()!=null ;k++)
                        {
                            temp=temp.getNext1();
                        }
                    }
                    else
                    {
                        for(int k=1; k<order2 && temp.getNext1()!=null ;k++)
                        {
                            temp=temp.getNext1();
                        }
                    }
                    
                    
                    

                    if(i==length-1){
                        newTitle.setNext1(temp.getNext1());
                        temp.setNext1(newTitle);
                        
                        while(counterNode.getNext1()!=null)
                    {
                        counterNode=counterNode.getNext1();
                        count++;
                    }

                        if(order2>count)
                        {
                                System.out.println("Heading's order does not exist at this level,"
                                        +"\nit has been added to the last position");
                        }
                        return;
                    }
                    
                }

                else
                {
                    if(temp.getNext2()!=null)
                    {
                        if(IsItHere(title,temp.getNext2()))
                        {
                            System.out.println(title+" -This title already exist, it isn't added again!!!");
                            System.out.println();
                            return;
                        }
                            if(i == length - 2 && order.charAt(i + 1) == '1')
                            {
                                newTitle.setNext1(temp.getNext2());
                                temp.setNext2(newTitle); 
                                return;
                            }
                    }
                    if(temp.getNext2()==null)
                    {
                        temp.setNext2(newTitle );
                        if(order.charAt(i+1)>'1')
                        {
                            System.out.println("This level was empty," +
                            "\nit has been inserted in the first position!!!");
                        }
                        return;
                    }
                    else
                    {
                        temp=temp.getNext2();
                    }
                    order2=0;
                }
            }
        } 
    }
    
    //Print'in recursive kısmı
    private void Recursive2(Node temp, int level,String counter){
        int count=1;
        while(temp!=null)
        {
            for(int i=0;i<level;i++)
            {
                System.out.print("    ");
            }
            
            String Sequence;
            if(Length(counter)==0)
                Sequence=count+".";
            else
                Sequence= counter+count+".";
            
            System.out.println(Sequence+" "+temp.getTitle());
            if(temp.getNext2()!=null)
                Recursive2(temp.getNext2(),level+1,Sequence);
            temp=temp.getNext1();
            count++;
        }
    }
    
    //Print kısmı
    public void ShowAll2(){
        if(head1==null)
        {
            System.out.println("Empty List");
            return;
        }
        Recursive2(head1,0,"");
    }
    
    
    
}
