/*
Queue Assignment
Amanda Faulconer
 */
package queue_hw;

import java.util.Scanner;

/////////////////////////////////////////////////////////////////////////////////
//Queue Bobs Billylious Bodacious Burgers Restaurant.
/////////////////////////////////////////////////////////////////////////////////

//Class for Big Bobs Billylious Bodacious Burgers Restaurant
class QueueOrder{
    
    //Global Variables
    
    
    //Variables
    public int MaxSize;
    public String[] BodaciousArray;
    public int Front;  //Track the front pointer
    public int Rear;   //track the last pointer 
    public int NumberOfOrders; //track the number of orders in the system
    
    //Constructor
    public QueueOrder(int size){
        
        MaxSize = size;
        BodaciousArray = new String[MaxSize];
        Front = 0;
        Rear = -1;
        NumberOfOrders = 0;
    }

    public QueueOrder() {
        
    }    
    
    //Enqueue - add to the rear of the queue
    public void AddToOrder(){
        String Data = "";
        //Add a wrap around
        if(Rear == MaxSize - 1){           
            Rear = -1;
        }
        //Increment the rear and insert a new item
        BodaciousArray[++Rear] = Data;
        NumberOfOrders++;
    }
   
    //Dequeue - remove one from the array
    public String RemoveFromOrder(){
        //Get the first value and incrament the front
        String temp = BodaciousArray[Front++];
        //Add a wrap around
        if(Front == MaxSize){
            Front = 0;
        }
        //Remove one item
        NumberOfOrders--;
        return temp;                
    }
    
    //Peek at the front of the queue
    public String peekFront(){
        return BodaciousArray[Front];
    }
    
    //Check to is the queue is empty
    public boolean isEmpty(){
        return(NumberOfOrders == 0);
    }
    
    //Check to see if the queue is full
    public boolean isFull(){
        return(NumberOfOrders == MaxSize);
    }
    
    //Check how many items are in the queue
    public int size(){
        return NumberOfOrders;
    }
    
    public void DisplayQueueOrder(){
        //int i;
        if(isEmpty()){
            System.out.println("There are no orders to fill");
            return;
        }
        
        int CurrentPosition = Front;
        for(int i = 0; i < NumberOfOrders; i++){
            System.out.println(BodaciousArray[CurrentPosition] + " ");
            CurrentPosition++;
            if(CurrentPosition == MaxSize){
                CurrentPosition = 0;
            }
        }                
    }
    
    
} // end of class queue array

/////////////////////////////////////////////////////////////////////////////////
/////Queue List 
/////////////////////////////////////////////////////////////////////////////////

class Link{
    
    //People data
    public String fName;
    public String lName;
    
    public int Front;    //tracking at the beginging of the list
    public int NextFree; //tracking at the end of the list
    public int MaxSize;
    
    public Link next;
    public Link prev;
    
    public Link(String fName, String lName){
        this.fName = fName;
        this.lName = lName;       
        this.next = null;
        this.prev = null;
    }

    public Link() {
        
    }
    
    public void DisplayQueue(){
        System.out.println("{" + fName + " " + lName + "}");
    }   
   
}


class QueueList{
       
    static QueueList theList = new QueueList();
    
    public Link first;
    public Link last;
    
    public QueueList(){
        first = null;
        last = null;
    }
    
    //Check to see if there is anyone in line
    public boolean isEmpty() {
        return (first == null);
    }
    
    //Insert a new person into the line
    public void AddPerson(String fName, String lName){
        
        Link newLink = new Link(fName, lName);

        if (isEmpty()) {
            last = newLink;
            first = newLink;
        } else {
            first.prev = newLink;
            newLink.next = first;
        }
        first = newLink;
    }

    //Peek to see who is next in line
    public Link peek(){
        Link current = first;
        return first;
    }
    
    //Remove a person from the list
    public Link RemovePerson (){
          Link temp = first;
          if(first.next == null){
              last = null;
          } else{
              first.next.prev = null;
              first = first.next;
          }
          return temp;          
    }
        
    //Print the people in line
    public void PrintList(){
        System.out.println("\nAll the people in line: \n");
        Link current = first;
        while(current != null){
            current.DisplayQueue();
            current = current.next;
        }
        System.out.println();
    }   
}

public class Queue_hw {
      
    ////////////////////////////////////////////////////////////////////////////
    //Queue Big Bobs Billylious Bodacious Burgers Restaurant.
    ////////////////////////////////////////////////////////////////////////////
        static void User_Control_BodaciousArray(){
        Scanner orderScan = new Scanner(System.in);
        QueueOrder newOrder = new QueueOrder(60);
        String Response;
        String NameCode;
        int TicketNumber;
        
        System.out.println("Welcome to Big Bobs Billylious Bodacious Burgers Restaurant\n");
        System.out.println("Would you like to add an order to the system? y/n");
        Response = orderScan.nextLine().toLowerCase();
        while(Response.equals("y")){
            System.out.println("Enter your three digit name come: ");
            NameCode = orderScan.nextLine().toUpperCase();
            System.out.println("Enter the ticket number: ");
            TicketNumber = Integer.parseInt(orderScan.nextLine());
            newOrder.AddToOrder();
            newOrder.DisplayQueueOrder();
            
            //add the user input into the array
            
            
            System.out.println("Would you like to add another order to the system? y/n");
            Response = orderScan.nextLine().toLowerCase();
        }
        
        System.out.println("Would you like to see which order is up next? y/n");
        Response = orderScan.nextLine().toLowerCase();
        if(Response.equals("y")){
            newOrder.peekFront();
        } 
        
        String next;
        
        System.out.println("Would you like to remove the next in line? y/n");
        Response = orderScan.nextLine().toLowerCase();
        if(Response.equals("y")){
            next = newOrder.RemoveFromOrder();
            System.out.println("\nNext = " + next);
        }                
    }        
   
    ////////////////////////////////////////////////////////////////////////////
    //Queue people in line
    ////////////////////////////////////////////////////////////////////////////
            
    public static void User_Insert_Person(){
        Scanner personScan = new Scanner(System.in);
        QueueList theList = new QueueList();
        String Response;
        
        //Adding people to the list
        System.out.println("Would you like to add a person to the list? y/n");
        Response = personScan.nextLine().toLowerCase();
        while(Response.equals("y")){
            System.out.println("What is the persons first name?");
            String fName = personScan.nextLine();
            System.out.println("What is the persons last name?");
            String lName = personScan.nextLine();
            theList.AddPerson(fName, lName);
            theList.PrintList();
            
            System.out.println("Would you like to add another person to the list?");
            Response = personScan.nextLine().toLowerCase();
        }
        
        //Removing a person from the list
        System.out.println("Would you like to remove a person from the list? y/n");
        Response = personScan.nextLine().toLowerCase();
        while(Response.equals("y")){
            theList.RemovePerson();
            System.out.println("You removed the first person from the list. Here is your new list\n");
            theList.PrintList();
            
            System.out.println("Would you like to remove another person from the list? y/n");
            Response = personScan.nextLine().toLowerCase();
        }
        
        //check to see who is next in line
        System.out.println("Would you like to see who is next in line? y/n");
        Response = personScan.nextLine().toLowerCase();
        if(Response.equals("y")){
            Link temp = new Link();
            temp = theList.peek();
            temp.DisplayQueue();
        }
        
        //see the entire list of people
        System.out.println("Would you like to see the entire list of people? y/n");
        Response = personScan.nextLine().toLowerCase();
        while(Response.equals("y")){
            theList.PrintList();
            break;
        }
        
        System.out.println("Would you like to go back to the menu? y/n");
        Response = personScan.nextLine().toLowerCase();
        if(Response.equals("y")){
            Menu();
        } else {
            System.out.println("Goodbye");
            System.exit(0);
        }
        
    }
    
    public static void Menu(){
        Scanner menuScan = new Scanner(System.in);
        int choice;
        System.out.println("Menu\n");
        System.out.println("Please choose one of the following options:"
                + "\n1. Go to the List of people"
                + "\n2. Big Bobs Billylious Bodacious Burgers Restaurant"
                + "\n6. Quit the program all together"
                + "\n Please make a choice: ");
        choice = Integer.parseInt(menuScan.nextLine());
        switch(choice){
            case 1 -> User_Insert_Person();
            case 2 -> User_Control_BodaciousArray();
            case 3 -> {
                System.out.println("Goodbye");
                System.exit(0);
            }
        }       
    }
    
    public static void main(String[] args) {
        Menu();
    }
    
}
